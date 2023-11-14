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
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(reader.readLine());

            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        int count = 0;
        int maxSize = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (visited[i][j]) {
                    continue;
                }

                if (matrix[i][j] == 1) {
                    count++;
                    Queue<Point> queue = new LinkedList<>();
                    queue.add(new Point(i, j));
                    visited[i][j] = true;

                    int size = 0;
                    while (!queue.isEmpty()) {
                        size++;
                        Point point = queue.poll();

                        for (int z = 0; z < 4; z++) {
                            int x = point.getX() + dx[z];
                            int y = point.getY() + dy[z];

                            if (x < 0 || x >= N || y < 0 || y >= M) {
                                continue;
                            }


                            if (matrix[x][y] == 1 && !visited[x][y]) {
                                visited[x][y] = true;
                                queue.add(new Point(x, y));
                            }
                        }
                    }

                    if (maxSize < size) {
                        maxSize = size;
                    }
                }
            }
        }
        System.out.println(count);
        System.out.println(maxSize);
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
            return y;
        }
    }
}
