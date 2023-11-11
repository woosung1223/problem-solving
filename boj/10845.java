import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        Queue<Integer> queue = new LinkedList<>();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < size; i++) {
            String command = reader.readLine();

            if (command.contains("push")) {
                int number = Integer.parseInt(command.split(" ")[1]);
                queue.add(number);
            }

            if (command.equals("pop")) {
                if (queue.isEmpty()) {
                    writer.write("-1\n");
                    continue;
                }
                int removed = queue.remove();
                writer.write(removed + "\n");
            }

            if (command.equals("size")) {
                writer.write(queue.size() + "\n");
            }

            if (command.equals("empty")) {
                writer.write(queue.isEmpty() ? "1\n" : "0\n");
            }

            if (command.equals("front")) {
                if (queue.isEmpty()) {
                    writer.write("-1\n");
                    continue;
                }
                writer.write(queue.peek() + "\n");
            }

            if (command.equals("back")) {
                if (queue.isEmpty()) {
                    writer.write("-1\n");
                    continue;
                }
                List<Integer> list = new LinkedList<>(queue);
                writer.write(list.get(list.size() - 1) + "\n");
            }
        }

        writer.flush();

        reader.close();
        writer.close();
    }
}
