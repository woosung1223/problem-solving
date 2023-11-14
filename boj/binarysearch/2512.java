import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        Arrays.sort(arr);

        int totalBudget = Integer.parseInt(reader.readLine());

        int left = 0;
        int right = 1_000_000_000;

        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            long sumBudget = 0;

            for (int i = 0; i < N; i++) {
                sumBudget += Math.min(mid, arr[i]);
            }

            if (sumBudget <= totalBudget) {
                result = Math.max(result, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        OptionalInt max = Arrays.stream(arr).max();
        System.out.println(Math.min(result, max.getAsInt()));
    }
}
