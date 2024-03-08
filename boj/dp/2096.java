import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        int[] maxDp = new int[3];
        int[] minDp = new int[3];

        StringTokenizer stz = new StringTokenizer(reader.readLine());

        // 배열 초기화
        for (int j = 0; j < 3; j++) {
            int number = Integer.parseInt(stz.nextToken());
            maxDp[j] = number;
            minDp[j] = number;
        }

        for (int i = 0; i < N - 1; i++) {
            stz = new StringTokenizer(reader.readLine());

            int[] arr = new int[3];
            for (int j = 0; j < 3; j++) {
                arr[j] = Integer.parseInt(stz.nextToken());
            }

            int[] newMaxDp = new int[3];
            int[] newMinDp = new int[3];

            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    newMaxDp[j] = arr[j] + Math.max(maxDp[j], maxDp[j + 1]);
                    newMinDp[j] = arr[j] + Math.min(minDp[j], minDp[j + 1]);
                } else if (j == 2) {
                    newMaxDp[j] = arr[j] + Math.max(maxDp[j], maxDp[j - 1]);
                    newMinDp[j] = arr[j] + Math.min(minDp[j], minDp[j - 1]);
                } else {
                    newMaxDp[j] = arr[j] + Math.max(maxDp[j], Math.max(
                            maxDp[j - 1], maxDp[j + 1]
                    ));
                    newMinDp[j] = arr[j] + Math.min(minDp[j], Math.min(
                            minDp[j - 1], minDp[j + 1]
                    ));
                }
            }

            maxDp = newMaxDp;
            minDp = newMinDp;
        }

        int max = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            max = Math.max(max, maxDp[i]);
            min = Math.min(min, minDp[i]);
        }

        System.out.println(max + " " + min);
    }
}
