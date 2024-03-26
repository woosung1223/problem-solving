import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] matrix;
    static int[][] count;
    static boolean[][] visited;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        matrix = new int[N][M];
        count = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(reader.readLine());

            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        // 1. 완전탐색으로 2차원 배열로 해당 격자에 접근한 횟수를 카운팅
        // 2. 카운팅이 2 이상이면 삭제
        // 3. 1 - 2 반복

        int time = 0;
        while (true) {
            dfs(0, 0);
            boolean removed = removeCheese();
            resetCount();
            resetVisited();

            if (!removed) {
                break;
            }
            time++;
        }

        System.out.println(time);
    }

    static void dfs(int i, int j) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int z = 0; z < 4; z++) {
            int x = dx[z] + i;
            int y = dy[z] + j;

            if (x < 0 || y < 0 || x >= N || y >= M) {
                continue;
            } else if (visited[x][y]) {
                continue;
            } else if (matrix[x][y] == 1) { // 치즈를 만나면
                count[x][y]++;
                continue;
            }

            visited[x][y] = true;
            dfs(x, y);
        }
    }

    static boolean removeCheese() {
        boolean removed = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (count[i][j] >= 2) {
                    matrix[i][j] = 0;
                    removed = true;
                }
            }
        }
        return removed;
    }

    static void resetCount() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                count[i][j] = 0;
            }
        }
    }

    static void resetVisited() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = false;
            }
        }
    }
}

