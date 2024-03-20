import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] matrix;

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        int R = Integer.parseInt(stz.nextToken());

        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(reader.readLine());

            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        int maxDepth = Math.min(N, M) / 2;

        while (R-- > 0) { // R번 돌리기

            for (int depth = 0; depth < maxDepth; depth++) { // 바깥 껍질부터 하나씩
                // 좌측 상단부터 시작하므로 저장
                int firstValue = matrix[depth][depth];

                // 상
                for (int j = depth; j < M - depth - 1; j++) {
                    matrix[depth][j] = matrix[depth][j + 1];
                }

                // 우
                for (int j = depth; j < N - depth - 1; j++) {
                    matrix[j][M - depth - 1] = matrix[j + 1][M - depth - 1];
                }

                // 하
                for (int j = M - depth - 1; j > depth; j--) {
                    matrix[N - depth - 1][j] = matrix[N - depth - 1][j - 1];
                }

                // 좌
                for (int j = N - depth - 1; j > depth; j--) {
                    matrix[j][depth] = matrix[j - 1][depth];
                }

                matrix[depth + 1][depth] = firstValue;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] arr : matrix) {
            for (int i : arr) {
                sb.append(i).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb.toString().trim());
    }
}
