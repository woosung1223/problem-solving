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

            for (int j = 0; j < 3; j++) {
                matrix[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        int[][] costs = new int[N][N];

        for (int j = 0; j < 3; j++) {
            costs[0][j] = matrix[0][j];
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != 0) {
                    if (j == 0) {
                        costs[i][j] = Math.min(costs[i - 1][j + 1],
                                costs[i - 1][j + 2]) + matrix[i][j];
                    } else if (j == 1) {
                        costs[i][j] = Math.min(costs[i - 1][j - 1],
                                costs[i - 1][j + 1]) + matrix[i][j];
                    } else {
                        costs[i][j] = Math.min(costs[i - 1][j - 1],
                                costs[i - 1][j - 2]) + matrix[i][j];
                    }
                }
            }
        }

        System.out.println(Math.min(costs[N - 1][0],
                Math.min(costs[N - 1][1], costs[N - 1][2])));
    }
}
