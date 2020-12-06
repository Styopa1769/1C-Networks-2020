package csma;

import java.util.Arrays;

/**
 * Имитирует работу Ethernet
 */
public class Ethernet {
    private volatile Frame currentFrame;

    public synchronized Frame currentFrame(){
        return currentFrame;
    }

    public void send(Frame frame) {
        currentFrame = frame;
        System.out.printf("%nComputer with mac %s starting send frame at %s%n%n", frame.getSenderMac(), System.nanoTime());
        System.out.printf("%nHash for message: %s. Received from mac: %s%n%n",
                Arrays.hashCode(frame.getData()), frame.getSenderMac());
        currentFrame = null;
    }
}
