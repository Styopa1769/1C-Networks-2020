package csma;

/**
 * Имитирует работу устройства, отправляющего в {@link Ethernet} данные
 */
public class Computer {
    private final MacAddress macAddress;
    private final Ethernet ethernet;

    public Computer(Ethernet ethernet) {
        this.macAddress = MacAddress.random();
        this.ethernet = ethernet;
    }

    public void attempt(Frame frame) {
        Thread t = new Thread(new Attempt(this, frame));
        t.start();
    }

    public Ethernet getEthernet() {
        return ethernet;
    }

    public MacAddress getMacAddress() {
        return macAddress;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "macAddress='" + macAddress + '\'' +
                '}';
    }
}
