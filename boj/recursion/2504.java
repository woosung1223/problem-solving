import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.w3c.dom.Node;

public class Main {

    /*
        재귀적으로 풀어나가는게 가장 간단해보임

        매 재귀 단계마다 '가장 밖에 있는 괄호'를 식별하고,
        그 괄호들을 기준으로 다시 재귀
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            Node node = new Node(i, line.charAt(i));
            list.add(node);
        }
        int result = recur(list, 0, line.length() - 1);
        System.out.println(result);
    }

    static int recur(List<Node> list, int start, int end) {
        if (start == end + 1) {
            return 1;
        }

        int result = 0;
        Stack<Node> stack = new Stack<>();
        for (int i = start; i <= end; i++) {
            if (list.get(i).value == '(' || list.get(i).value == '[') {
                stack.push(list.get(i));
                continue;
            } else if (stack.isEmpty()) {
                return 0;
            }

            if (stack.peek().value == '(' && list.get(i).value == ')' ||
                    stack.peek().value == '[' && list.get(i).value == ']') {
                Node startNode = stack.pop();
                if (stack.isEmpty()) {
                    Node endNode = list.get(i);
                    if (startNode.value == '(' && endNode.value == ')') {
                        result += 2 * recur(list, startNode.index + 1, endNode.index - 1);
                    } else if (startNode.value == '[' && endNode.value == ']') {
                        result += 3 * recur(list, startNode.index + 1, endNode.index - 1);
                    }
                }
            } else {
                return 0;
            }
        }

        return result;
    }

    static class Node {
        int index;
        char value;

        public Node(int index, char value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return index + "," + value;
        }
    }
}
