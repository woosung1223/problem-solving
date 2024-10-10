import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    /*
        LCS 문제 (DP)
        ACAYKP, CAPCAK => ACAK

        만약 비교하는 두 문자가 같다면 +1
        만약 다르다면? 0으로 처리할 게 아니라 (이건 최장 공통 문자열임)
        이전까지의 최대 값으로 설정해야 함
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word1 = reader.readLine();
        String word2 = reader.readLine();

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (i == 0 || j == 0) {
                    continue;
                }
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        System.out.println(dp[word1.length()][word2.length()]);
    }
}

