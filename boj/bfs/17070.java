import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] matrix;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer stz = new StringTokenizer(reader.readLine());

            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        if (matrix[N - 1][N - 1] == 1) {
            System.out.println(0);
            return;
        }

        Pipe initialPipe = new Pipe(0, 0, 0, 1);

        Queue<Pipe> queue = new LinkedList<>();
        queue.add(initialPipe);

        int count = 0;
        while (!queue.isEmpty()) {
            Pipe pipe = queue.poll();

            if (pipe.rightX == N - 1 && pipe.rightY == N - 1) {
                count++;
            }

            if (pipe.isHorizontal()) {
                Pipe nextPipe1 = new Pipe(pipe.leftX, pipe.leftY + 1,
                        pipe.rightX, pipe.rightY + 1);
                Pipe nextPipe2 = new Pipe(pipe.leftX, pipe.leftY + 1,
                        pipe.rightX + 1, pipe.rightY + 1);

                if (!exceedBoundary(nextPipe1) && !hasWall(nextPipe1)) {
                    queue.add(nextPipe1);
                }

                if (!exceedBoundary(nextPipe2) && !hasWall(nextPipe2)) {
                    queue.add(nextPipe2);
                }

            } else if (pipe.isVertical()) {
                Pipe nextPipe1 = new Pipe(pipe.leftX + 1, pipe.leftY,
                        pipe.rightX + 1, pipe.rightY);
                Pipe nextPipe2 = new Pipe(pipe.leftX + 1, pipe.leftY,
                        pipe.rightX + 1, pipe.rightY + 1);

                if (!exceedBoundary(nextPipe1) && !hasWall(nextPipe1)) {
                    queue.add(nextPipe1);
                }

                if (!exceedBoundary(nextPipe2) && !hasWall(nextPipe2)) {
                    queue.add(nextPipe2);
                }

            } else if (pipe.isDiagonal()) {
                Pipe nextPipe1 = new Pipe(pipe.leftX + 1, pipe.leftY + 1,
                        pipe.rightX, pipe.rightY + 1);
                Pipe nextPipe2 = new Pipe(pipe.leftX + 1, pipe.leftY + 1,
                        pipe.rightX + 1, pipe.rightY);
                Pipe nextPipe3 = new Pipe(pipe.leftX + 1, pipe.leftY + 1,
                        pipe.rightX + 1, pipe.rightY + 1);

                if (!exceedBoundary(nextPipe1) && !hasWall(nextPipe1)) {
                    queue.add(nextPipe1);
                }

                if (!exceedBoundary(nextPipe2) && !hasWall(nextPipe2)) {
                    queue.add(nextPipe2);
                }

                if (!exceedBoundary(nextPipe3) && !hasWall(nextPipe3)) {
                    queue.add(nextPipe3);
                }
            }
        }

        System.out.println(count);
    }

    static boolean exceedBoundary(Pipe pipe) {
        return pipe.rightX < 0 || pipe.rightY < 0 ||
                pipe.rightX >= N || pipe.rightY >= N;
    }

    static boolean hasWall(Pipe pipe) {
        if (pipe.isVertical() || pipe.isHorizontal()) { // 우측 끝만 체크
            return matrix[pipe.rightX][pipe.rightY] == 1;
        }

        return matrix[pipe.rightX][pipe.rightY] == 1 ||
                matrix[pipe.rightX - 1][pipe.rightY] == 1 ||
                matrix[pipe.rightX][pipe.rightY - 1] == 1;
    }

    static class Pipe {

        int leftX;
        int leftY;

        int rightX;
        int rightY;

        public Pipe(int leftX, int leftY, int rightX, int rightY) {
            this.leftX = leftX;
            this.leftY = leftY;
            this.rightX = rightX;
            this.rightY = rightY;
        }

        public boolean isVertical() {
            return leftY == rightY;
        }

        public boolean isHorizontal() {
            return leftX == rightX;
        }

        public boolean isDiagonal() {
            if (leftY - rightY == 0) {
                return false;
            }
            int changeRatio = (leftX - rightX) / (leftY - rightY);
            return changeRatio == 1 || changeRatio == -1;
        }
    }
}
