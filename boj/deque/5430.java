import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());

        while (testCase-- > 0) {
            String command = reader.readLine();
            int arrSize = Integer.parseInt(reader.readLine());

            String line = reader.readLine();
            StringTokenizer stz = new StringTokenizer(
                    line.substring(1, line.length() - 1), ",");

            Deque<Integer> deque = new LinkedList<>();
            for (int i = 0; i < arrSize; i++) {
                deque.addLast(Integer.parseInt(stz.nextToken()));
            }

            boolean isError = false;
            boolean isRear = false;
            for (char each : command.toCharArray()) {
                if (each == 'R') {
                    isRear = !isRear;
                }

                if (each == 'D') {
                    if (deque.isEmpty()) {
                        isError = true;
                        break;
                    }
                    if (isRear) {
                        deque.pollLast();
                    } else {
                        deque.pollFirst();
                    }
                }
            }

            if (isError) {
                System.out.println("error");
            } else {
                List<Integer> result = new ArrayList<>(deque);
                if (isRear) {
                    Collections.reverse(result);
                }
                StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
                for (Integer integer : result) {
                    stringJoiner.add(String.valueOf(integer));
                }
                System.out.println(stringJoiner);
            }
        }
    }
}

