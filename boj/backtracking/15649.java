import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;

    static boolean[] visited;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());

        visited = new boolean[N + 1];
        arr = new int[M];

        recur(0);
    }

    static void recur(int depth) {
        if (depth == M) {
            StringJoiner sj = new StringJoiner(" ");
            for (int i = 0; i < M; i++) {
                sj.add(String.valueOf(arr[i]));
            }
            System.out.println(sj);
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = i;
                recur(depth + 1);
                visited[i] = false;
            }
        }
    }
}
