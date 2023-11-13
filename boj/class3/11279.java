import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        Queue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < size; i++) {
            int number = Integer.parseInt(reader.readLine(Collections.reverseOrder()));
            if (number == 0) {
                if (queue.isEmpty()) {
                    System.out.println(0);
                    continue;
                }
                System.out.println(queue.poll());
            } else {
                queue.add(number);
            }
        }
    }
}
