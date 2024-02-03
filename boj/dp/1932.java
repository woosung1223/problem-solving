import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        int[][] matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer stz = new StringTokenizer(reader.readLine());
            for (int j = 0; j < i + 1; j++) {
                int number = Integer.parseInt(stz.nextToken());
                matrix[i][j] = number;
            }
        }

        int[][] costs = new int[N][N];

        costs[0][0] = matrix[0][0]; // setup initial value

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) { // left side
                    costs[i][j] = costs[i - 1][j] + matrix[i][j];
                }

                else if (j == i) { // right side
                    costs[i][j] = costs[i - 1][j - 1] + matrix[i][j];
                }

                else { // inner side
                    costs[i][j] = Math.max(costs[i - 1][j - 1], costs[i - 1][j]) +
                            matrix[i][j];
                }
            }
        }

        int max = -1;
        for (int i = 0; i < N; i++) {
            if (costs[N - 1][i] > max) {
                max = costs[N - 1][i];
            }
        }

        System.out.println(max);
    }
}
