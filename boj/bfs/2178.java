import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(reader.readLine());

            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        int min = Integer.MAX_VALUE;
        Queue<Point> queue = new LinkedList<>();

        boolean[][] visited = new boolean[N][M];

        queue.add(new Point(0, 0));
        visited[0][0] = true;
        int[][] distance = new int[M][N];

        int count = 0;
        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int x = point.getX() + dx[i];
                int y = point.getY() + dy[i];
            }

            if (x < 0 || x >= N || y < 0 || y >= M) {
                continue;
            } else if (visited[x][y] || matrix[x][y] != 1) {
                continue;
            }

            distance[x][y] = distance[point.getX()][point.getY()] + 1;
            if (x == N - 1 && y == N - 1) {
                if (distance[x][y] > count) {
                    count = distance[x][y];
                }
            }

            visited[x][y] = true;
            queue.add(new Point(x, y));
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return x;
        }
    }
}
