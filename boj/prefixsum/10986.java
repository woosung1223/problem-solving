import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        stz = new StringTokenizer(reader.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        int[] costs = new int[N];
        costs[0] = arr[0] % M;

        for (int i = 1; i < N; i++) {
            costs[i] = (costs[i - 1] + arr[i]) % M;
        }

        // 결과 = 0의 개수 + 나머지 숫자들의 개수 C 2
        long result = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            if (costs[i] == 0) {
                result++;
            }
            map.put(costs[i], map.getOrDefault(costs[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result += (long) entry.getValue() * (entry.getValue() - 1) / 2;
        }

        System.out.println(result);
    }
}
