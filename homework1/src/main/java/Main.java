import csma.Computer;
import csma.Ethernet;
import csma.Frame;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Ethernet ethernet = new Ethernet();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число компьютеров:");

        List<Computer> computers = IntStream.range(0, scanner.nextInt())
                .mapToObj(i -> new Computer(ethernet))
                .collect(Collectors.toList());

        //Сообщение максимальной длины - 1500 байт
        byte[] data = new byte[1500];
        Random random = new Random();
        random.nextBytes(data);

        for (Computer computer : computers){
            Frame frame = new Frame(computer.getMacAddress(), data);
            computer.attempt(frame);
        }
    }
}
