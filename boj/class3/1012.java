import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        for (int z = 0; z < size; z++) {
            StringTokenizer stz = new StringTokenizer(reader.readLine());

            int M = Integer.parseInt(stz.nextToken());
            int N = Integer.parseInt(stz.nextToken());
            int K = Integer.parseInt(stz.nextToken());

            int[][] matrix = new int[M + 2][N + 2];

            for (int i = 0; i < K; i++) {
                stz = new StringTokenizer(reader.readLine());
                int row = Integer.parseInt(stz.nextToken());
                int col = Integer.parseInt(stz.nextToken());

                matrix[row + 1][col + 1] = 1;
            }

            int count = 0;
            for (int i = 1; i < M + 1; i++) {
                for (int j = 1; j < N + 1; j++) {

                    if (matrix[i][j] == 1) {
                        count++;
                        makeZero(matrix, i, j);
                    }
                }
            }
            System.out.println(count);
        }
    }

    private static void makeZero(int[][] matrix, int i, int j) {
        if (matrix[i][j] == 0) {
            return;
        } else if (matrix[i][j] == 1) {
            matrix[i][j] = 0;
        }
        makeZero(matrix, i - 1, j);
        makeZero(matrix, i + 1, j);
        makeZero(matrix, i, j - 1);
        makeZero(matrix, i, j + 1);
    }
}
