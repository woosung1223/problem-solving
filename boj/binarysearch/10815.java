import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] bag = new int[N];

        StringTokenizer stz = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            bag[i] = Integer.parseInt(stz.nextToken());
        }

        int M = Integer.parseInt(reader.readLine());
        int[] deck = new int[M];
        stz = new StringTokenizer(reader.readLine());
        for (int i = 0; i < M; i++) {
            deck[i] = Integer.parseInt(stz.nextToken());
        }

        boolean[] results = new boolean[M];

        Arrays.sort(bag);

        for (int i = 0; i < M; i++) {
            int target = deck[i];

            int left = 0;
            int right = bag.length - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (bag[mid] == target) {
                    results[i] = true;
                    break;
                } else if (bag[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        StringJoiner stringJoiner = new StringJoiner(" ");
        for (boolean result : results) {
            stringJoiner.add(result ? "1" : "0");
        }

        System.out.println(stringJoiner);
    }
}
