import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[][] matrix;
    static boolean[] visited;

    static int max = 1;
    static int R;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        R = Integer.parseInt(stz.nextToken());
        C = Integer.parseInt(stz.nextToken());

        matrix = new char[R][C];
        visited = new boolean['Z' + 1];

        for (int i = 0; i < R; i++) {
            String line = reader.readLine();

            for (int j = 0; j < C; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        visited[matrix[0][0]] = true;
        backtracking(0, 0, 1);
        System.out.println(max);
    }

    static void backtracking(int x, int y, int depth) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextY < 0 ||
                    nextX >= R || nextY >= C) {
                continue;
            } else if (visited[matrix[nextX][nextY]]) {
                continue;
            }

            visited[matrix[nextX][nextY]] = true;
            backtracking(nextX, nextY, depth + 1);
            visited[matrix[nextX][nextY]] = false;

            max = Math.max(max, depth + 1);
        }
    }
}
