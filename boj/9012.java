import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        for (int i = 0; i < size; i++) {
            String line = reader.readLine();
            boolean isVPS = true;

            Stack<Character> stack = new Stack<>();
            for (int j = 0; j < line.length(); j++) {
                char each = line.charAt(j);

                if (each == '(') {
                    stack.push(each);
                } else {
                    if (stack.isEmpty()) {
                        isVPS = false;
                        break;
                    }
                    char top = stack.peek();
                    if (top != '(') {
                        isVPS = false;
                        break;
                    }
                    stack.pop();
                }
            }
            if (!stack.isEmpty()) {
                isVPS =false;
            }

            if (isVPS) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
