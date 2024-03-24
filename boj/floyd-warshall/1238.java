import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int INF = 10_000 * 100;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken()); // N명의 학생
        int M = Integer.parseInt(stz.nextToken()); // M개의 단방향 도로
        int X = Integer.parseInt(stz.nextToken()); // X마을에 모여서 파티를 벌이기로 함

        int[][] matrix = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(reader.readLine());

            int from = Integer.parseInt(stz.nextToken());
            int to = Integer.parseInt(stz.nextToken());
            int value = Integer.parseInt(stz.nextToken());

            matrix[from][to] = value;
        }

        // 플로이드 워셜

        for (int baseNode = 1; baseNode <= N; baseNode++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == baseNode || j == baseNode) {
                        continue;
                    }
                    if (matrix[i][baseNode] + matrix[baseNode][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][baseNode] + matrix[baseNode][j];
                    }
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, matrix[i][X] + matrix[X][i]);
        }

        System.out.println(max);
    }
}
