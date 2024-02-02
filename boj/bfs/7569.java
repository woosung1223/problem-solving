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

        int M = Integer.parseInt(stz.nextToken());
        int N = Integer.parseInt(stz.nextToken());
        int H = Integer.parseInt(stz.nextToken());

        int[][][] matrix = new int[H][N][M];

        int[] dx = {-1, 0, 1, 0, 0, 0};
        int[] dy = {0, -1, 0, 1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};

        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                stz = new StringTokenizer(reader.readLine());
                for (int k = 0; k < M; k++) {
                    matrix[i][j][k] = Integer.parseInt(stz.nextToken());

                    if (matrix[i][j][k] == 1) {
                        queue.add(new Point(i, j, k));
                    }
                }
            }
        }

        // setup done
        while (true) {
            boolean hasResult = false;

            while (!queue.isEmpty()) {
                Point point = queue.poll();

                for (int i = 0; i < 6; i++) {
                    int x = dx[i] + point.x;
                    int y = dy[i] + point.y;
                    int z = dz[i] + point.z;

                    if (x < 0 || y < 0 || z < 0 ||
                            x >= H || y >= N || z >= M) {
                        continue;
                    } else if (matrix[x][y][z] != 0) {
                        continue;
                    }
                    hasResult = true;
                    matrix[x][y][z] = matrix[point.x][point.y][point.z] + 1;
                    queue.add(new Point(x, y, z));
                }
            }

            if (!hasResult) {
                break;
            }
        }

        // results
        boolean hasZero = false;
        int max = -1;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (matrix[i][j][k] == 0) {
                        hasZero = true;
                    }

                    if (matrix[i][j][k] > max) {
                        max = matrix[i][j][k];
                    }
                }
            }
        }

        if (hasZero) {
            System.out.println(-1);
        } else {
            System.out.println(max - 1);
        }
    }

    static class Point {
        int x;
        int y;
        int z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
