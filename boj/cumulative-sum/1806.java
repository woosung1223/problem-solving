import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    /*
        N개의 원소로 이루어진 수열
        합이 S 이상이 되면서, 길이가 최소가 되는 경우?

        누적합을 통해 구간합을 구할 수 있음
        구간합이 S 이상이면서 가장 짧은 경우를 찾으면 됨

        단순히 브루트 포스로 구간합을 순회하면서 찾을 수는 있으나, O(n^2)이라
        시간 제한 (0.5초)에는 미달 (100_000 x 100_000 연산)

        0.5초에서 힌트를 얻자
        입력 값이 100,000이니, O(n * logn) 내에 처리해야 함

        누적합 배열에 이분탐색 -> 누적합 배열은 항상 오름차순이라는 성질 이용

        1. 누적합 배열을 구한 뒤, (n)
        2. 각각의 요소에 대해 S를 넘기는 값을 이분 탐색으로 찾음 (logn)
        3. 반복
    */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int S = Integer.parseInt(stz.nextToken());

        int[] arr = new int[N];
        stz = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N ; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        int[] accumulation = new int[N];
        accumulation[0] = arr[0];
        for (int i = 1; i < N; i++) {
            accumulation[i] = arr[i] + accumulation[i - 1];
        }

        // accumulation[n] = n까지의 누적합
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int target = S + accumulation[i] - arr[i];

            int index = Arrays.binarySearch(accumulation, target);
            if (index < 0) {
                index = -index - 1; // insertion point
            }

            if (index != N) { // N - 1 이라면, 합을 만드는게 불가능하다는 의미
                int length = index - i + 1;
                min = Math.min(min, length);
            }
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(min);
        }
    }
}
