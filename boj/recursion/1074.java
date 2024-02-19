import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int r = Integer.parseInt(stz.nextToken());
        int c = Integer.parseInt(stz.nextToken());

        long result = recur((int) Math.pow(2, N), r, c);
        System.out.println(result);
    }

    static long recur(int N, int r, int c) {
        if (N == 1) {
            return r > 0 ? (long) r * c + c : c;
        }

        long result;
        int center = N / 2;
        if (r < center && c < center) { // 1분면
            result = recur(center, r, c);
        } else if (r < center && c >= center) { // 2분면
            result = (long) center * center + recur(center, r, c - center);
        } else if (r >= center && c < center) { // 3분면
            result = ((long) center * center) * 2 + recur(center, r - center, c);
        } else { // 4분면
            result = ((long) center * center) * 3 + recur(center, r - center, c - center);
        }
        return result;
    }
}

