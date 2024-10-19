import java.io.*;
import java.util.*;

public class Main {

    /*
        오큰수 => Ai보다 우측에 있으면서 큰 수 중에 가장 왼쪽에 있는 수
        배열의 크기 N <= 1_000_000

        한번에 순회해서 문제를 해결하는 방법은 없을까?
        - 스택
        순회하면서 스택에 요소를 넣는데, Peek한 값보다 작다면 넣음
        Peek한 값보다 크다면, 스택의 요소를 빼고 오큰수를 결정

        최악의 경우 매번 오큰수를 결정하거나, 오큰수가 하나인 경우므로
        시간복잡도는 O(2n) = 배열 순회 + 스택 POP
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = new int[N];
        Stack<Element> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            if (stack.isEmpty()) {
                stack.push(new Element(arr[i], i));
                continue;
            }
            int peek = stack.peek().value;
            if (peek < arr[i]) {
                while (!stack.isEmpty() && stack.peek().value < arr[i]) {
                    Element pop = stack.pop();
                    result[pop.index] = arr[i];
                }
            }
            stack.push(new Element(arr[i], i));
        }

        while (!stack.isEmpty()) {
            Element pop = stack.pop();
            result[pop.index] = -1;
        }

        StringJoiner sj = new StringJoiner(" ");
        for (int i : result) {
            sj.add(String.valueOf(i));
        }
        System.out.println(sj);
    }

    static class Element {
        int value;
        int index;

        public Element(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}

