import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while (!(line = reader.readLine()).equals(".")) {
            String result = isBalanced(line);
            System.out.println(result);
        }
    }

    public static String isBalanced(String line) {
        Stack<Character> stack = new Stack<>();

        boolean isBalanced = true;
        for (int i = 0; i < line.length(); i++) {
            char target = line.charAt(i);

            if (target == '(' || target == '[') {
                stack.push(target);
            }

            if (target == ')') {
                if (stack.isEmpty()) {
                    isBalanced = false;
                    break;
                }
                char removed = stack.pop();

                if (removed != '(') {
                    isBalanced = false;
                    break;
                }
            } else if (target == ']') {
                if (stack.isEmpty()) {
                    isBalanced = false;
                    break;
                }
                char removed = stack.pop();

                if (removed != '[') {
                    isBalanced = false;
                    break;
                }
            }
        }

        if (!stack.isEmpty()) {
            isBalanced = false;
        }

        return isBalanced ? "yes" : "no";
    }
}
