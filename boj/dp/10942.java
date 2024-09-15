import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    /*
        자연수 N개: N1 N2 N3 N4 ...
        질문 M개: [S번째, E번째] => 팰린드롬?

        N은 2_000, M은 1_000_000

        브루트 포스 => O(N * M) => 시간 초과

        단순 순회가 아닌 방식으로 팰린드롬 여부를 구해야 함
        팰린드롬이란, 수열이 대칭을 이루는 경우

        동적계획법

        palin[S, E] = palin[S + 1, E - 1] && (arr[S] == arr[E])
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] arr = new int[N + 1];

        StringTokenizer stz = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        boolean[][] palindrome = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            palindrome[i][i] = true;
        }

        // 진행방향 주의, 점화식 이슈로 아래쪽부터 시작
        // 위부터 시작하면 palindrome[i + 1][j - 1]이 0을 참조
        for (int i = N; i > 0; i--) {
            for (int j = i + 1; j <= N; j++) {
                if (j == i + 1) { // 사이즈가 2인 경우는 별도 처리
                    palindrome[i][j] = arr[i] == arr[j];
                    continue;
                }

                palindrome[i][j] = palindrome[i + 1][j - 1] &&
                        arr[i] == arr[j];
            }
        }


        int M = Integer.parseInt(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(reader.readLine());

            int S = Integer.parseInt(stz.nextToken());
            int E = Integer.parseInt(stz.nextToken());

            writer.write(palindrome[S][E] ? '1' : '0');
            writer.write('\n');
        }

        writer.flush();
    }
}
