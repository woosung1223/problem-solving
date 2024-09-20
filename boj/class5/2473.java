import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    /*
        세 용액을 합쳐서 0에 가장 가까운 조합?
        요소 값의 범위는 -10억 ~ 10억

        n은 5000 이하

        - n^2 탐색하면서, 마지막 하나는 구간 이분 탐색

        6
        8 -1 2 -10 10 7
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer stz = new StringTokenizer(reader.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        long resultSum = Long.MAX_VALUE;
        long resultFirst = -1;
        long resultSecond = -1;
        long resultThird = -1;

        Arrays.sort(arr);

        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                long sum = arr[i] + arr[j];
                int targetIndex = binarySearch(arr, j + 1, arr.length - 1, -sum);

                // binary search의 결과는 원하는 값보다 크거나 같은 값
                // 따라서 해당 인덱스 1 이전의 값도 고려해야 함
                if (targetIndex != j + 1 &&
                        Math.abs(sum + arr[targetIndex - 1]) <
                        Math.abs(sum + arr[targetIndex])) {
                    targetIndex--;
                }

                sum += arr[targetIndex];

                if (Math.abs(sum) < Math.abs(resultSum)) {
                    resultSum = sum;
                    resultFirst = arr[i];
                    resultSecond = arr[j];
                    resultThird = arr[targetIndex];
                }
            }
        }

        long[] result = {resultFirst, resultSecond, resultThird};
        Arrays.sort(result);
        System.out.printf("%d %d %d", result[0], result[1], result[2]);


    }

    static int binarySearch(int[] arr, int left, int right, long target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
