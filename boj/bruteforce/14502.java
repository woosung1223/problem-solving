import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;

    static int[][] matrix;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        /*
            안전 영역의 최댓값을 구하라.
            = 벽을 최소한으로 사용해 바이러스를 격리해라.

            브루트 포스로 해결
         */

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());

        matrix = new int[N][M];
        List<Point> viruses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(reader.readLine());

            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(stz.nextToken());

                if (matrix[i][j] == 2) {
                    viruses.add(new Point(i, j));
                }
            }
        }

        int max = -1;
        for (int i = 0; i < N * M; i++) {
            for (int j = i + 1; j < N * M; j++) {
                for (int k = j + 1; k < N * M; k++) {
                    if (matrix[i / M][i % M] != 0 ||
                            matrix[j / M][j % M] != 0 ||
                            matrix[k / M][k % M] != 0) {
                        continue;
                    }

                    matrix[i / M][i % M] = 1;
                    matrix[j / M][j % M] = 1;
                    matrix[k / M][k % M] = 1;

                    // 모든 바이러스 확장
                    visited = new boolean[N][M];
                    for (Point virus : viruses) {
                        bfs(virus);
                    }

                    // 방문하지 않은 격자들 중, 0인 격자 카운팅
                    int count = count();
                    max = Math.max(count, max);

                    matrix[i / M][i % M] = 0;
                    matrix[j / M][j % M] = 0;
                    matrix[k / M][k % M] = 0;
                }
            }
        }

        System.out.println(max);
    }

    static void bfs(Point virus) {
        Queue<Point> queue = new LinkedList<>();

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        queue.add(virus);
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            visited[point.x][point.y] = true;

            for (int i = 0; i < 4; i++) {
                int x = dx[i] + point.x;
                int y = dy[i] + point.y;

                if (x < 0 || y < 0 || x >= N || y >= M) {
                    continue;
                } else if (visited[x][y] || matrix[x][y] == 1) {
                    continue;
                }

                queue.add(new Point(x, y));
            }
        }
    }

    static int count() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (matrix[i][j] == 0 && !visited[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
