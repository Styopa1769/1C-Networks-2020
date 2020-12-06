package csma;

/**
 * Фрейм. Содержит мак-адрес и передаваемые данные
 */
public class Frame {
    private final MacAddress senderMac;
    private final byte[] data;

    public Frame(MacAddress senderMac, byte[] data) {
        this.senderMac = senderMac;
        this.data = data;
    }

    public MacAddress getSenderMac() {
        return senderMac;
    }

    public byte[] getData() {
        return data;
    }
}
