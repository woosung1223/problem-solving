import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        char[][] matrix = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        int firstResult = bfsAndGetAreaCounts(matrix, List.of('R')) +
                bfsAndGetAreaCounts(matrix, List.of('G')) +
                bfsAndGetAreaCounts(matrix, List.of('B'));

        int secondResult = bfsAndGetAreaCounts(matrix, List.of('R', 'G')) +
                bfsAndGetAreaCounts(matrix, List.of('B'));

        System.out.println(firstResult + " " + secondResult);
    }

    static int bfsAndGetAreaCounts(char[][] matrix,
                                   List<Character> targets) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if (targets.contains(matrix[i][j]) && !visited[i][j]) {
                    Queue<Point> queue = new LinkedList<>();
                    queue.add(new Point(i, j));
                    visited[i][j] = true;
                    count++;

                    while (!queue.isEmpty()) {
                        Point point = queue.poll();

                        for (int z = 0; z < 4; z++) {
                            int x = dx[z] + point.x;
                            int y = dy[z] + point.y;

                            if (x < 0 || y < 0 || x >= matrix.length ||
                                    y >= matrix[0].length) {
                                continue;
                            } else if (visited[x][y] || !targets.contains(matrix[x][y])) {
                                continue;
                            }

                            visited[x][y] = true;
                            queue.add(new Point(x, y));
                        }
                    }
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
