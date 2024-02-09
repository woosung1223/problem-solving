import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int C = Integer.parseInt(stz.nextToken());

        // 가장 인접한 두 공유기 사이의 거리가 최대가 되도록 이분탐색 -> C개의 공유기를 설치할 수 있어야 함
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }

        long start = 0;
        long end = Integer.MAX_VALUE;

        Arrays.sort(arr);
        // 1 2 4 8 9

        while (start <= end) {
            long mid = (start + end) / 2;

            int count = 1;
            int compare = arr[0];

            for (int i = 1; i < N; i++) {
                if (arr[i] - compare >= mid) {
                    count++;
                    compare = arr[i];
                }
            }

            if (count >= C) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(start - 1);
    }
}

