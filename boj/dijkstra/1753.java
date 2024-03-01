import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int E = Integer.parseInt(stz.nextToken());

        int[][] matrix = new int[N + 1][N + 1];

        for (int i = 0; i < E; i++) {
            stz = new StringTokenizer(reader.readLine());

            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());

            matrix[a][b] = c;
            matrix[b][a] = c;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = 100_000_000;
                }
            }
        }

        stz = new StringTokenizer(reader.readLine());

        int v1 = Integer.parseInt(stz.nextToken());
        int v2 = Integer.parseInt(stz.nextToken());

        /*
        최단경로인데, 이미 지났던 경로를 다시 지나는 경우는 언제?
        - v1, v2를 지나야 한다는 제약조건 때문.
        - 즉, v1 혹은 v2가 n으로 향하는 길이 아닌 경우.
        - 경로는 두 가지 케이스가 존재.
            1. 시작 - v1 - v2 - 끝
            2. 시작 - v2 - v1 - 끝
        - 위 케이스로 다익스트라, 다만 '한번 이동했던 간선도 다시 이동할 수 있다'는 제약조건이 있으니 끊어서

        정답은
        d(시작 -> v1) + d(v1 -> v2) + d(v2 -> 끝)
        or
        d(시작 -> v2) + d(v2 -> v1) + d(v1 -> 끝)
         */

        int[][] case1 = {{1, v1}, {v1, v2}, {v2, N}};
        int[][] case2 = {{1, v2}, {v2, v1}, {v1, N}};

        int totalCost1 = dijkstra(N, matrix, case1);
        int totalCost2 = dijkstra(N, matrix, case2);

        if (totalCost1 == -1 && totalCost2 == -1) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(totalCost1, totalCost2));
        }
    }

    static int dijkstra(int N, int[][] matrix, int[][] cases) {
        List<Integer> costs = new ArrayList<>();

        for (int[] each : cases) {
            int start = each[0];
            int end = each[1];

            int[] distances = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                distances[i] = matrix[start][i];
            }

            boolean[] visited = new boolean[N + 1];
            visited[start] = true;

            for (int i = 0; i < N - 1; i++) {
                int smallestIndex = getSmallestIndex(distances, visited);
                visited[smallestIndex] = true;

                for (int j = 1; j <= N; j++) {
                    if (!visited[j] &&
                            distances[smallestIndex]
                                    + matrix[smallestIndex][j] < distances[j]) {
                        distances[j] = distances[smallestIndex] + matrix[smallestIndex][j];
                    }
                }
            }

            if (start == end) {
                costs.add(0);
                continue;
            } else if (distances[end] == 100_000_000) {
                return -1;
            }

            costs.add(distances[end]);
        }

        int result = 0;
        for (Integer cost : costs) {
            result += cost;
        }
        return result;
    }

    static int getSmallestIndex(int[] arr, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = Integer.MAX_VALUE;

        for (int i = 1; i < arr.length; i++) {
            if (!visited[i] && arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
