import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            String line = reader.readLine();
            for (int j = 0; j < size; j++) {
                matrix[i][j] = line.charAt(j) - '0';
            }
        }

        boolean[][] visited = new boolean[size][size];

        int count = 0;
        List<Integer> innerCounts = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (matrix[i][j] == 1) {
                    if (visited[i][j]) {
                        count++;

                        Queue<Point> queue = new LinkedList<>();
                        queue.add(new Point(i, j));
                        visited[i][j] = true;
                        int innerCount = 0;
                        while (!queue.isEmpty()) {
                            Point point = queue.poll();

                            for (int z = 0; z < 4; z++) {
                                int x = point.x + dx[z];
                                int y = point.y + dy[z];

                                if (x < 0 || x >= size || y < 0 || y >= size) {
                                    continue;
                                } else if (matrix[x][y] != 1 || visited[x][y]) {
                                    continue;
                                }

                                innerCount++;
                                visited[x][y] = true;
                                queue.add(new Point(x, y));
                            }
                        }
                        innerCounts.add(innerCount);
                    }
                }
            }
        }
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
