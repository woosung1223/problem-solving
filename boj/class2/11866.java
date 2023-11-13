import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // (7, 3)
        // 1, 2, 3, 4, 5, 6, 7
        // 1, 2, 4, 5, 6, 7 (3)
        // 1, 2, 4, 5, 7 (3, 6)
        // 1, 4, 5, 7 (3, 6, 2)
        // 1, 4, 5 (3, 6, 2, 7)
        // ...

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(line);

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        List<Integer> results = new ArrayList<>();

        while (!queue.isEmpty()) {
            for (int i = 0; i < k - 1; i++) {
                int removed = queue.remove();
                queue.add(removed);
            }
            int target = queue.remove();
            results.add(target);
        }

        StringJoiner stringJoiner = new StringJoiner(", ", "<", ">");
        for (Integer result : results) {
            stringJoiner.add(String.valueOf(result));
        }
        System.out.println(stringJoiner);
    }
}
