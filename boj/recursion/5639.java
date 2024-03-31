import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        /*
            시간 제한: 1초
            노드의 최대 개수는 10_000

            전위 순회한 결과가 주어짐(루트 - 왼쪽 - 오른쪽)
            후위 순회한 결과는? (왼쪽 - 오른쪽 - 루트)
         */

        // 전위 순회는 삽입한 순서와 똑같음
        Node root = new Node(Integer.parseInt(reader.readLine()), null, null);

        String line;
        while ((line = reader.readLine()) != null) {
            int number = Integer.parseInt(line);

            // 삽입
            Node index = root;
            Node target = new Node(number, null, null);
            // target과 index 비교
            while (true) {
                if (target.number < index.number) {
                    if (index.left == null) {
                        index.left = target;
                        break;
                    } else {
                        index = index.left;
                    }
                } else if (target.number > index.number) {
                    if (index.right == null) {
                        index.right = target;
                        break;
                    } else {
                        index = index.right;
                    }
                }
            }
        }

        recur(root);
    }

    static void recur(Node current) {
        if (current == null) {
            return;
        }

        recur(current.left);
        recur(current.right);
        System.out.println(current.number);
    }

    static class Node {
        int number;
        Node left;
        Node right;

        public Node(int number, Node left, Node right) {
            this.number = number;
            this.left = left;
            this.right = right;
        }
    }
}

