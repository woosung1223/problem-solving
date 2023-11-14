import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        int[] arr = new int[N];
        stz = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = 1_000_000_000;
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            Long tmp = 0L;
            for (int i = 0; i < arr.length; i++) {
                tmp += Math.max(0, arr[i] - mid);
            }

            if (tmp >= M) {
                result = Math.max(result, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}
