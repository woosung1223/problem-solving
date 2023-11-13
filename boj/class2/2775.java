import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        /*
        ...
        1 4 10 20 35
        1 3 6 10 15
        1 2 3 4 5
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseSize = Integer.parseInt(reader.readLine());

        for (int i = 0; i < testCaseSize; i++) {
            int k = Integer.parseInt(reader.readLine());
            int n = Integer.parseInt(reader.readLine());

            int[][] matrix = new int[k + 1][n];

            for (int col = 0; col < n; col++) {
                matrix[0][col] = col + 1;
            }

            for (int row = 1; row <= k; row++) {
                matrix[row][0] = 1;
            }

            for (int row = 1; row <= k; row++) {
                for (int col = 1; col < n; col++) {
                    matrix[row][col] = matrix[row -1][col] + matrix[row][col - 1];
                }
            }

            System.out.println(matrix[k][n - 1]);
        }
    }
}
