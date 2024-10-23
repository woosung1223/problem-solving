import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /*
        구현 문제
        좌표 (r,c)에서 시작
        d=0 북쪽, d=1 동쪽, d=2 남쪽, d=3 서쪽

        1. 현재 칸이 청소되지 않았으면 현재 칸 청소
        2. 주변 4칸 중 청소되지 않은 칸이 있다면 반시계 방향으로 돌면서
           바라보는 방향이 청소되지 않은 칸이라면 이동
        3. 주변 4칸 중 청소되지 않은 칸이 없다면 방향 유지한 채로 후진, 다시 체크
           벽에 부딪히면 작동을 멈춤
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(reader.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[N][M];
        visited[r][c] = true;
        int result = 1;

        // 북-동-남-서
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(r, c, d));

        while (!queue.isEmpty()) {
            Status status = queue.poll();
            boolean hasAreaToMove = false;
            int direction = status.direction;
            for (int i = 0; i < 4; i++) {
                direction = (direction + 3) % 4;
                int x = status.x + dx[direction];
                int y = status.y + dy[direction];

                if (x < 0 || y < 0 || x >= N || y >= M) {
                    continue;
                } else if (visited[x][y] || matrix[x][y] == 1) {
                    continue;
                }
                result++;
                hasAreaToMove = true;
                visited[x][y] = true;
                queue.add(new Status(x, y, direction));
                break;
            }

            if (!hasAreaToMove) {
                int x = status.x - dx[direction];
                int y = status.y - dy[direction];

                if (x < 0 || y < 0 || x >= N || y >= M) {
                    break;
                } else if (matrix[x][y] == 1) {
                    break;
                }

                queue.add(new Status(x, y, direction));
            }
        }
        System.out.println(result);
    }

    static class Status {
        int x;
        int y;
        int direction;

        public Status(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}
