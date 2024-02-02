import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(reader.readLine());

        while (testCase-- > 0) {
            int size = Integer.parseInt(reader.readLine());

            StringTokenizer stz = new StringTokenizer(reader.readLine());
            Point startPoint = new Point(Integer.parseInt(stz.nextToken()),
                    Integer.parseInt(stz.nextToken()));

            stz = new StringTokenizer(reader.readLine());
            Point endPoint = new Point(Integer.parseInt(stz.nextToken()),
                    Integer.parseInt(stz.nextToken()));

            if (startPoint.equals(endPoint)) {
                System.out.println(0);
                continue;
            }

            int[][] matrix = new int[size][size];

            // (-1, -2), (1, -2), (-2, -1), (2, -1), (-2, 1), (2, 1), (-1, 2), (1, 2)

            int[] dx = {-1, 1, -2, 2, -2, 2, -1, 1};
            int[] dy = {-2, -2, -1, -1, 1, 1, 2, 2};

            Queue<Point> queue = new LinkedList<>();

            queue.add(startPoint);

            matrix[startPoint.x][startPoint.y] = 1;

            boolean isOver = false;
            while (!isOver && !queue.isEmpty()) {
                Point point = queue.poll();

                for (int i = 0; i < 8; i++) {
                    int x = point.x + dx[i];
                    int y = point.y + dy[i];

                    if (x < 0 || y < 0 || x >= size || y >= size) {
                        continue;
                    } else if (matrix[x][y] != 0) {
                        continue;
                    }
                    if (new Point(x, y).equals(endPoint)) {
                        System.out.println(matrix[point.x][point.y]);
                        isOver = true;
                        break;
                    }

                    matrix[x][y] = matrix[point.x][point.y] + 1;
                    queue.add(new Point(x, y));

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

        public boolean equals(Point other) {
            return this.x == other.x && this.y == other.y;
        }
    }

    static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + ",");
            }
            System.out.println();
        }
    }
}
