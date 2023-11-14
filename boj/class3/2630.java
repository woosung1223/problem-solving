import java.util.*;
import java.io.*;

public class Main {

    static int white = 0;
    static int blue = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        int[][] matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer stz = new StringTokenizer(reader.readLine());

            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(stz.nextToken());
            }
        }
        recur(matrix, 0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }

    public static void recur(int[][] matrix, int row, int col, int size) {
        if (size == 0) {
            return;
        }

        boolean isSameColor = true;
        int color = matrix[row][col];
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (matrix[i][j] != color) {
                    isSameColor = false;
                    break;
                }
            }
        }

        if (isSameColor && color == 0) {
            white++;
        } else if (isSameColor && color == 1) {
            blue++;
        } else {
            recur(matrix, row, col, size / 2);
            recur(matrix, row + size / 2, col, size / 2);
            recur(matrix, row, col + size / 2, size / 2);
            recur(matrix, row + size / 2, col + size / 2, size / 2);
        }
    }
}
