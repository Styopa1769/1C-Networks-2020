package csma;

/**
 * Вспомогательный класс для асинхронной попытки передачи данных
 */
public class Attempt implements Runnable {
    private final Computer owner;
    private final Frame frame;
    private int attemptNumber = 0;

    public Attempt(Computer owner, Frame frame) {
        this.owner = owner;
        this.frame = frame;
    }

    @Override
    public void run() {
        boolean success = false;
        while (attemptNumber < 16 && !success) {
            Frame buffer = frame;
            if (owner.getEthernet().currentFrame() == null){
                owner.getEthernet().send(buffer);
                success = true;
            }
            else {
                // по идее csma чистит буффер в случае неудачи
                buffer = null;
                try {
                    waitToAttempt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!success)
            System.out.printf("Attempt for %s failed because attempt number is %d%n", owner, attemptNumber);
    }

    private void waitToAttempt() throws InterruptedException {
        attemptNumber++;
        int n = Math.min(attemptNumber, 10);
        //время ожидания в мкс
        long waitTime = Math.round(Math.random() * Math.pow(2, n) * 51.2);
        System.out.printf("%s will wait %d mcs.%n", owner, waitTime);
        int nanosInMillis = 100000;
        long millisToWait = waitTime / nanosInMillis;
        int nanosToWait = (int) (waitTime % nanosInMillis);
        Thread.sleep(millisToWait, nanosToWait);
        System.out.printf("%s waited %d mcs.%n", owner, waitTime);
    }
}
