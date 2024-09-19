import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    /*
        부 배열의 합이 T가 되는 모든 부 배열 쌍의 개수
        부 배열은 크기가 1일수도, 혹은 배열 전체 크기일수도 있음

        1 <= n <= 1000 (첫번째 배열의 크기)
        1 <= m <= 1000 (두번째 배열의 크기)

        브루트포스는 안됨 ?

        n = 3, m = 3인 경우, 경우의 수는 아래와 같음

        1 => 1, 2, 3, 12, 23, 123
        2 => 1, 2, 3, 12, 23, 123
        3 => 1, 2, 3, 12, 23, 123
        12 => 1, 2, 3, 12, 23, 123
        23 => 1, 2, 3, 12, 23, 123
        123 -> 1, 2, 3, 12, 23, 123

        O(n^2 * m^2)
        따라서 n => 1000, m => 1000의 경우 시간 초과

        1. 누적합 배열을 구해서 => O(n)
        2. 모든 가능한 구간합을 전개 => O(n^2)
        3. HashMap 저장 후 두 구간합 배열에 매칭되는 요소가 있는지 확인 => O(n)

        => O(K * n^2) scale 내 풀이 가능
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());

        int N = Integer.parseInt(reader.readLine());
        int[] leftArr = new int[N];
        StringTokenizer stz = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            leftArr[i] = Integer.parseInt(stz.nextToken());
        }

        int M = Integer.parseInt(reader.readLine());
        int[] rightArr = new int[M];
        stz = new StringTokenizer(reader.readLine());
        for (int i = 0; i < M; i++) {
            rightArr[i] = Integer.parseInt(stz.nextToken());
        }

        int[] leftRangeSum = new int[N];
        int[] rightRangeSum = new int[M];

        leftRangeSum[0] = leftArr[0];
        rightRangeSum[0] = rightArr[0];

        for (int i = 1; i < N; i++) {
            leftRangeSum[i] = leftRangeSum[i - 1] + leftArr[i];
        }

        for (int i = 1; i < M; i++) {
            rightRangeSum[i] = rightRangeSum[i - 1] + rightArr[i];
        }

        List<Integer> leftAllCases = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int sum = leftRangeSum[j] - leftRangeSum[i] + leftArr[i];
                leftAllCases.add(sum);
            }
        }

        List<Integer> rightAllCases = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            for (int j = i; j < M; j++) {
                int sum = rightRangeSum[j] - rightRangeSum[i] + rightArr[i];
                rightAllCases.add(sum);
            }
        }

        // 중복 요소를 위한 카운터
        Map<Integer, Long> counter = new HashMap<>();
        for (int each : rightAllCases) {
            counter.put(each, counter.getOrDefault(each, 0l) + 1);
        }

        long count = 0;
        for (int each : leftAllCases) {
            count += counter.getOrDefault(T - each, 0l);
        }

        System.out.println(count);
    }
}
