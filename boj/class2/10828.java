import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            String command = reader.readLine();

            if (command.contains("push")) {
                int toPush = Integer.parseInt(command.split(" ")[1]);
                stack.push(toPush);
            }

            if (command.equals("pop")) {
                if (stack.isEmpty()) {
                    System.out.println(-1);
                    continue;
                }
                int popped = stack.pop();
                System.out.println(popped);
            }

            if (command.equals("size")) {
                System.out.println(stack.size());
            }

            if (command.equals("empty")) {
                System.out.println(stack.isEmpty() ? 1 : 0);
            }

            if (command.equals("top")) {
                if (stack.isEmpty()) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(stack.peek());
            }
        }
    }
}
