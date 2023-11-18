import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int M = Integer.parseInt(stz.nextToken());
        int N = Integer.parseInt(stz.nextToken());

        int[][] matrix = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(reader.readLine());

            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        Queue<Point> queue = new LinkedList<>();
        Queue<Point> anotherQueue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (matrix[i][j] == 1) {
                    queue.add(new Point(i, j));
                    visited[i][j] = true;
                }
            }
        }

        int day = 0;
        while (true) {
            while (!queue.isEmpty()) {
                Point point = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int x = point.x + dx[i];
                    int y = point.y + dy[i];

                    if (x < 0 || x >= N || y < 0 || y >= M) {
                        continue;
                    } else if (matrix[x][y] == -1 || visited[x][y]) {
                        continue;
                    }

                    anotherQueue.add(new Point(x, y));
                    visited[x][y] = true;
                }
            }

            queue = new LinkedList<>(anotherQueue);
            anotherQueue.clear();
            if (queue.isEmpty()) {
                break;
            }
            day++;
        }

        boolean cant = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0 && visited[i][j] == false) {
                    cant = true;
                }
            }
        }

        if (cant) {
            System.out.println(-1);
        } else {
            System.out.println(day);
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + "," + y;
        }
    }
}
