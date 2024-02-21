import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;

    static int[][] matrix;
    static boolean[][] visited;

    static int maxCost;
    static int cost;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());

        matrix = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(reader.readLine());

            for (int j = 0; j < M; j++) {
                int number = Integer.parseInt(stz.nextToken());
                matrix[i][j] = number;
            }
        }

        List<Integer> results = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cost = 0;
                maxCost = 0;

                visited[i][j] = true;
                dfs(i, j, 1);
                int fifthCost = fifthTetromino(i, j);
                visited[i][j] = false;

                results.add(Math.max(maxCost, fifthCost));
            }
        }

        System.out.println(Collections.max(results));
    }

    static void dfs(int i, int j, int depth) {
        cost += matrix[i][j];

        if (depth == 4) { // 크기가 4라면 종료
            maxCost = Math.max(cost, maxCost);
            return;
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        // (i, j) 에서부터 시작해서 이동 크기가 4인 모든 비용 중 최대치

        for (int z = 0; z < 4; z++) {
            int x = dx[z] + i;
            int y = dy[z] + j;

            if (x < 0 || y < 0 || x >= N || y >= M) {
                continue;
            } else if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;

            dfs(x, y, depth + 1);

            cost -= matrix[x][y];
            visited[x][y] = false;
        }
    }

    private static int fifthTetromino(int i, int j) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        List<Integer> values = new ArrayList<>();

        for (int z = 0; z < 4; z++) {
            int x = dx[z] + i;
            int y = dy[z] + j;

            if (x < 0 || y < 0 || x >= N || y >= M) {
                continue;
            }
            values.add(matrix[x][y]);
        }

        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (Integer each : values) {
            sum += each;
            min = Math.min(min, each);
        }

        if (values.size() == 4) {
            return sum - min + matrix[i][j]; // 중앙값은 무조건 포함, 그리고 가장 작은 값을 뺌
        }
        return sum + matrix[i][j];
    }
}
