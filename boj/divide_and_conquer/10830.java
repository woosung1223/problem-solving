import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int mod = 1_000;
    static Map<Long, int[][]> dp = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        long B = Long.parseLong(stz.nextToken());

        int[][] matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(reader.readLine());

            for (int j = 0; j < N; j++) {
                int number = Integer.parseInt(stz.nextToken());
                matrix[i][j] = number;
            }
        }

        int[][] result = recur(matrix, B);

        for (int[] ints : result) {
            for (int anInt : ints) {
                System.out.print(anInt % mod + " ");
            }
            System.out.println();
        }
    }

    static int[][] recur(int[][] matrix, long b) {
        if (!dp.containsKey(b)) {
            if (b == 1) {
                dp.put(b, matrix);
            } else if (b == 2) {
                dp.put(b, multiplyMatrix(matrix, matrix));
            } else if (b % 2 == 0) {
                dp.put(b, multiplyMatrix(recur(matrix, b / 2), recur(matrix, b / 2)));
            } else {
                dp.put(b, multiplyMatrix(matrix,
                        multiplyMatrix(recur(matrix, b / 2), recur(matrix, b / 2))));
            }
        }
        return dp.get(b);
    }

    static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2) {
        int size = matrix1.length;
        int[][] newMatrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int sum = 0;

                for (int z = 0; z < size; z++) {
                    sum += matrix1[i][z] * matrix2[z][j];
                }

                newMatrix[i][j] = sum % mod;
            }
        }
        return newMatrix;
    }
}

