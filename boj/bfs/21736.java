import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        char[][] matrix = new char[N][M];

        Point start = null;

        for (int i = 0; i < N; i++) {
            String line = reader.readLine();

            for (int j = 0; j < M; j++) {
                matrix[i][j] = line.charAt(j);

                if (matrix[i][j] == 'I') {
                    start = new Point(i, j);
                }
            }
        }

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        queue.add(start);
        visited[start.x][start.y] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int x = dx[i] + point.x;
                int y = dy[i] + point.y;

                if (x < 0 || y < 0 || x >= N || y >= M) {
                    continue;
                } else if (matrix[x][y] == 'X' || visited[x][y]) {
                    continue;
                }
                if (matrix[x][y] == 'P') {
                    count++;
                }
                queue.add(new Point(x, y));
                visited[x][y] = true;
            }
        }

        System.out.println(count == 0 ? "TT" : count);
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
