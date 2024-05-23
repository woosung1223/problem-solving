import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] arr = new int[N];
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        /*
        최대 개수는 2000개이므로,
        2000개 요소 각각에 대해 투 포인터 알고리즘을 수행해도 2000 * 2000 = 4_000_000회의 연산
         */

        Arrays.sort(arr);
        int result = 0;

        // 0을 조심해야 함
        // 0이 연산에 포함되는 경우는 (0 + 자기 자신)으로 조건 만족 가능
        // 다만 0끼리는,
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;

            while (left < right) {
                int sum = arr[left] + arr[right];

                if (sum > arr[i]) {
                    right--;
                } else if (sum < arr[i]) {
                    left++;
                } else {
                    if (left != i && right != i) {
                        // 자기 자신은 포함 X
                        result++;
                        break;
                    } else {
                        // 자기 자신이 포함된 경우라면, 자신이 있는 쪽을 줄임
                        if (left == i) {
                            left++;
                        } else {
                            right--;
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }
}
