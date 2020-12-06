package csma;

import java.util.Arrays;
import java.util.Random;

/**
 * Вспомогательный класс для работы с мак-адресом
 */
public class MacAddress {
    private final static Random RANDOM = new Random();

    private final byte[] address;

    private MacAddress(byte[] address) {
        this.address = address;
    }

    public static MacAddress random() {
        byte[] addr = new byte[6];
        RANDOM.nextBytes(addr);
        return new MacAddress(addr);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MacAddress that = (MacAddress) o;
        return Arrays.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(address);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < address.length; i++) {
            sb.append(String.format("%02X%s", address[i], (i < address.length - 1) ? "-" : ""));
        }
        return sb.toString();
    }
}
