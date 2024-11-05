import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /*
        보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 두 지점
        보물이 묻혀 있는 두 곳 간의 최단거리는?

        N, M <= 50

        매트릭스의 크기가 작아서 모든 요소에 대해 탐색을 수행하면 될듯
        모든 요소(N*M)에 대해 BFS(4방향 * N*M개의 요소)를 수행하면 O(N*M*(4*N*M))

        2500 * (10000) = 2500만이므로 시간 초과 X
     */

    static int N;
    static int M;
    static char[][] matrix;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 'L') {
                    int furthest = bfs(i, j);
                    result = Math.max(furthest, result);
                }
            }
        }
        System.out.println(result);
    }

    static int bfs(int i, int j) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        queue.add(new Point(i, j, 0));
        visited[i][j] = true;

        int furthest = 0;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            furthest = Math.max(furthest, point.depth);

            for (int k = 0; k < 4; k++) {
                int x = dx[k] + point.x;
                int y = dy[k] + point.y;

                if (x < 0 || y < 0 || x >= N || y >= M) {
                    continue;
                } else if (visited[x][y] || matrix[x][y] == 'W') {
                    continue;
                }
                visited[x][y] = true;
                queue.add(new Point(x, y, point.depth + 1));
            }
        }

        return furthest;
    }

    static class Point {
        int x;
        int y;
        int depth;

        public Point(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}
