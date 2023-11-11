import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        Deque<Integer> deque = new ArrayDeque<>();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < size; i++) {
            String command = reader.readLine();

            if (command.contains("push_front")) {
                int number = Integer.parseInt(command.split(" ")[1]);
                deque.addFirst(number);
            } else if (command.contains("push_back")) {
                int number = Integer.parseInt(command.split(" ")[1]);
                deque.addLast(number);
            } else if (command.equals("pop_front")) {
                if (deque.isEmpty()) {
                    writer.write("-1\n");
                    continue;
                }
                writer.write(deque.removeFirst() + "\n");
            } else if (command.equals("pop_back")) {
                if (deque.isEmpty()) {
                    writer.write("-1\n");
                    continue;
                }
                writer.write(deque.removeLast() + "\n");
            } else if (command.equals("size")) {
                writer.write(deque.size() + "\n");
            } else if (command.equals("empty")) {
                writer.write(deque.isEmpty() ? "1\n" : "0\n");
            } else if (command.equals("front")) {
                if (deque.isEmpty()) {
                    writer.write("-1\n");
                    continue;
                }
                writer.write(deque.getFirst() + "\n");
            } else if (command.equals("back")) {
                if (deque.isEmpty()) {
                    writer.write("-1\n");
                    continue;
                }
                writer.write(deque.getLast() + "\n");
            }
        }
        writer.flush();

        writer.close();
        reader.close();
    }
}
