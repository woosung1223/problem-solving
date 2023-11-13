import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < size; i++) {
            String[] values = scanner.nextLine().split(" ");
            System.out.println(Integer.parseInt(values[0]) + Integer.parseInt(values[1]));
        }
    }
}
