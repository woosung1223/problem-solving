import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
        
        List<Integer> result = new ArrayList<>();
        result.add(arr[0]);

        for (int i = 1; i < N; i++) {
            int target = arr[i];

            if (target > result.get(result.size() - 1)) {
                result.add(target);
                continue;
            }

            int start = 0;
            int end = result.size() - 1;

            while (start < end) {
                int mid = (start + end) / 2;

                if (result.get(mid) < target) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }

            result.set(end, target);
        }

        System.out.println(result.size());
    }
}

