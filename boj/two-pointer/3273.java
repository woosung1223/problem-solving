import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] arr = new int[n];

        StringTokenizer stz = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(stz.nextToken());
            arr[i] = number;
        }
        int x = Integer.parseInt(reader.readLine());

        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;

        int result = 0;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == x) {
                result++;
                left++; // 아무거나 증가시켜도 상관없음
            } else if (sum > x) {
                right--; // sum 값을 줄여야 하므로 right--
            } else {
                left++; // sum 값을 늘려야 하므로 left++
            }
        }

        System.out.println(result);
    }
}

