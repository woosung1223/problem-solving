import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /*
        원숭이는 K번만 말처럼 이동할 수 있다
        이외에는 인접한 칸으로만 이동이 가능
        좌측 위에서부터 시작할 때, 우측 아래까지 최소 비용으로 가는 방법은?

        K <= 30
        W, H <= 200

        일반 BFS 탐색으로 비용을 저장하면서 가면서,
        말을 통해 현재 칸으로 이동할 수 있는 경우 비용을 갱신하는 방법이 있음
        다만 말은 이동 제한이 있어서 문제임

        BFS 탐색을 하는데, 각 방문 지점에서의 '말 이동 횟수'를 저장하자
        다만 점프를 해야 하는 시기를 적절히 조절해야 함

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < W; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][][] visited = new boolean[H][W][K + 1];
        int[][] count = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                count[i][j] = Integer.MAX_VALUE;
            }
        }
        if (matrix[0][0] != 1) {
            count[0][0] = 0;
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 0));
        visited[0][0][0] = true;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        int[] horseDx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] horseDy = {-1, 1, -2, 2, -2, 2, -1, 1};

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == H - 1 && point.y == W - 1) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int x = dx[i] + point.x;
                int y = dy[i] + point.y;

                if (x < 0 || y < 0 || x >= H || y >= W) {
                    continue;
                } else if (visited[x][y][point.horseCount] || matrix[x][y] == 1) {
                    continue;
                }
                visited[x][y][point.horseCount] = true;
                count[x][y] = count[point.x][point.y] + 1;
                queue.add(new Point(x, y, point.horseCount));
            }

            for (int i = 0; i < 8; i++) {
                int x = horseDx[i] + point.x;
                int y = horseDy[i] + point.y;

                if (x < 0 || y < 0 || x >= H || y >= W) {
                    continue;
                } else if (point.horseCount >= K) {
                    break; // K번 넘어가면 안됨
                } else if (matrix[x][y] == 1 || visited[x][y][point.horseCount + 1]) {
                    continue;
                }
                
                count[x][y] = count[point.x][point.y] + 1;
                visited[x][y][point.horseCount + 1] = true;
                queue.add(new Point(x, y, point.horseCount + 1));
            }
        }

        System.out.println(count[H - 1][W - 1] != Integer.MAX_VALUE ?
                count[H - 1][W - 1] : -1);
    }

    static class Point {
        int x;
        int y;
        int horseCount;

        public Point(int x, int y, int horseCount) {
            this.x = x;
            this.y = y;
            this.horseCount = horseCount;
        }
    }
}
