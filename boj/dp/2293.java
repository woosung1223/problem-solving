import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    /*
        n가지 종류의 동전
        가치의 합이 k원이 되게 하고 싶음 => 경우의 수는?
        단, 각각의 동전은 몇개라도 사용할 수 있음

        n <= 100

        - DP 문제

        dp[x] = dp[x - coin] 와 같은 방식으로 구할 수 있음
        예를 들어, 7을 만드는 경우의 수는 dp[6] + dp[5] + dp[2]

        하지만 이 방식대로 진행한다면 중복이 생겨나게 됨
        왜 중복이 생기는가?

        [1, 2, 5]에서 4를 고려해보자. dp[4] = dp[3] + dp[2] =
            dp[2] + dp[2] + dp[1]이 된다.
        즉, 가치 2의 만들어내는 경우의 수가 여러 번 더해지게 된다.

        그렇다면 코인을 순회하면서 dp 배열을 초기화한다면?
        각 코인에 대해 n 가치를 만들어내는 방식을 차례대로 합산

        즉, 1로 만드는 경우의 수 + 2로 만드는 경우의 수 + ... 가
        중복 없이 계산된다
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());

        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            value[i] = Integer.parseInt(reader.readLine());
        }

        int[] dp = new int[k + 1];

        dp[0] = 1;

        for (int coin : value) {
            for (int i = coin; i <= k; i++) {
                dp[i] += dp[i - coin];
            }
        }

        System.out.println(dp[k]);
    }
}
