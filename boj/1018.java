import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        boolean[][] board = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = reader.readLine();

            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j) == 'B';
            }
        }

        boolean[][] boardStartsWithW = new boolean[8][8];
        boolean[][] boardStartsWithB = new boolean[8][8];

        for (int i = 0; i < 8; i++) {
            boolean starts = i % 2 == 0;

            for (int j = 0; j < 8; j++) {
                if (j % 2 == 0) {
                    boardStartsWithB[i][j] = starts;
                    boardStartsWithW[i][j] = !starts;
                } else {
                    boardStartsWithB[i][j] = !starts;
                    boardStartsWithW[i][j] = starts;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                int diffB = diff(board, boardStartsWithB, i, j);
                int diffW = diff(board, boardStartsWithW, i, j);

                min = Math.min(min, Math.min(diffB, diffW));
            }
        }

        System.out.println(min);
    }

    public static int diff(boolean[][] targetBoard, boolean[][] baseBoard, int i, int j) {
        int count = 0;
        for (int x = i, q = 0; x < i + 8; x++, q++) {
            for (int y = j, w = 0; y < j + 8; y++, w++) {
                if (targetBoard[x][y] != baseBoard[q][w]) {
                    count++;
                }
            }
        }
        return count;
    }
}
