import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;

    static int[][] matrix;
    static int[] costs;

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        matrix = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(reader.readLine());

            int from = Integer.parseInt(stz.nextToken());
            int to = Integer.parseInt(stz.nextToken());

            matrix[from][to] = 1;
            matrix[to][from] = 1;
        }

        Map<Integer, Integer> results = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            costs = new int[N + 1];
            visited = new boolean[N + 1];

            bfs(i);

            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (i != j) {
                    sum += costs[j];
                }
            }
            results.put(i, sum);
        }

        int min = Collections.min(results.values());

        List<Integer> resultVertexes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : results.entrySet()) {
            if (entry.getValue() == min) {
                resultVertexes.add(entry.getKey());
            }
        }
        System.out.println(Collections.min(resultVertexes));
    }

    static void bfs(int startVertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);

        visited[startVertex] = true;

        while (!queue.isEmpty()) {
            int point = queue.poll();

            for (int i = 0; i <= N; i++) {
                if (matrix[point][i] != 1) {
                    continue;
                } else if (visited[i]) {
                    continue;
                }
                costs[i] = costs[point] + 1;
                visited[i] = true;
                queue.add(i);
            }
        }
    }
}
