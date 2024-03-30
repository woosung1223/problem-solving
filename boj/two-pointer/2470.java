import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        /*
        -1_000_000_000 ~ 1_000_000_000 범위. 단, 0은 아님
         합이 0에 가장 가까운 특성값을 만드는 두 용액을 찾아라.

         시간 제한: 1초
         최대 100_000개의 데이터가 들어올 수 있음
         */

        int N = Integer.parseInt(reader.readLine());
        int[] arr = new int[N];

        StringTokenizer stz = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = N - 1;

        int min = Integer.MAX_VALUE;
        int minLeft = -1;
        int minRight = -1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (Math.abs(sum) < Math.abs(min)) {
                min = sum;
                minLeft = arr[left];
                minRight = arr[right];
            }

            // sum 값이 0에 근접해지는 쪽으로 이동해야 함
            // sum 값이 음수라면, 양수 값을 늘려야 함(left++)
            // sum 값이 양수라면, 음수 값을 늘려야 함(right--)

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.printf("%d %d", minLeft, minRight);
    }
}

