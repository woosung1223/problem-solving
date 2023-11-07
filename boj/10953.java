import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < size; i++) {
            String line = scanner.nextLine();
            String[] split = line.split(",");
            System.out.println(Integer.parseInt(split[0]) + Integer.parseInt(split[1]));
        }
    }
}
