import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*
        N 개의 물건, 각 물건은 무게 W와 가치 V를 가짐
        => 최대 가치는?

        - knapsack 문제
        N 개의 물건 각각에 대하여,
        배낭에 넣었을 때의 가치와 넣지 않았을 때의 가치를 비교
        단, 배낭이 이미 차서 들어갈 수 없다면 해당 물건의 무게만큼 비운 경우의 가치에 더하기

        - 점화식
        N은 물건의 번호, W는 물건의 무게
        cost[N][W] = Math.max(cost[N - 1][W - WEIGHT[N]] + VALUE[N],
                        cost[N - 1][W]);
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());

        int[] weight = new int[N + 1];
        int[] value = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            stz = new StringTokenizer(reader.readLine());

            weight[i] = Integer.parseInt(stz.nextToken());
            value[i] = Integer.parseInt(stz.nextToken());
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (weight[i] <= j) { // 현재 물건의 무게가 배낭의 현재 무게보다 작거나 같다면 뺄 수 있음
                    dp[i][j] = Math.max(dp[i - 1][j - weight[i]] + value[i],
                            dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
