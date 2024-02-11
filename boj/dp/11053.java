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

        int[] length = new int[N];
        // length[k] = K번째의 값을 끝으로 하는 가장 긴 수열의 길이
        length[0] = 1;

        for (int i = 1; i < N; i++) {
            length[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    length[i] = Math.max(length[i], length[j] + 1);
                }
            }
        }

        int max = -1;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, length[i]);
        }

        System.out.println(max);
    }
}

