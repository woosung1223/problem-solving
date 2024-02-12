import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        int[] increasing = new int[N];
        int[] decreasing = new int[N];

        for (int i = 0; i < N; i++) {
            increasing[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    increasing[i] = Math.max(increasing[i], increasing[j] + 1);
                }
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            decreasing[i] = 1;

            for (int j = N - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    decreasing[i] = Math.max(decreasing[i], decreasing[j] + 1);
                }
            }
        }

        int max = -1;

        for (int i = 0; i < N; i++) {
            int num = increasing[i] + decreasing[i] - 1;
            max = Math.max(num, max);
        }

        System.out.println(max);
    }
}

