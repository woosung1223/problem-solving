import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        int[] arr = new int[N];

        stz = new StringTokenizer(reader.readLine());

        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(stz.nextToken());
            arr[i] = number;
        }

        int[] costs = new int[N + 1];
        costs[1] = arr[0];

        for (int i = 2; i <= N; i++) {
            costs[i] = costs[i - 1] + arr[i - 1];
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(reader.readLine());

            int start = Integer.parseInt(stz.nextToken());
            int end = Integer.parseInt(stz.nextToken());

            /*
                5 4 3 2 1
                0 5 9 12 14 15

                result = costs[end] - costs[start - 1];
                누적합을 구해놓으면 구간을 자유롭게 설정할 수 있음
             */

            sb.append(costs[end] - costs[start - 1]).append("\n");
        }

        System.out.print(sb);
    }
}
