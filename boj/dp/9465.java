import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(reader.readLine());

            int[][] matrix = new int[2][N];

            for (int i = 0; i < 2; i++) {
                StringTokenizer stz = new StringTokenizer(reader.readLine());

                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(stz.nextToken());
                }
            }

            int[][] dp = new int[2][N];
            dp[0][0] = matrix[0][0];
            dp[1][0] = matrix[1][0];

            if (N == 1) {
                System.out.println(Math.max(dp[0][0], dp[1][0]));
                continue;
            }

            dp[0][1] = dp[1][0] + matrix[0][1];
            dp[1][1] = dp[0][0] + matrix[1][1];

            for (int i = 2; i < N; i++) {
                dp[0][i] = matrix[0][i] + Math.max(
                        dp[1][i - 1], Math.max(
                                dp[0][i - 2], dp[1][i - 2]));

                dp[1][i] = matrix[1][i] + Math.max(
                        dp[0][i - 1], Math.max(
                                dp[0][i - 2], dp[1][i - 2]));
            }

            int max = -1;
            for (int[] arr : dp) {
                for (int i : arr) {
                    max = Math.max(i, max);
                }
            }

            System.out.println(max);
        }
    }
}
