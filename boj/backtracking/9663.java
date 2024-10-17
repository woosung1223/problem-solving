import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    /*
        N-Queen

        백트래킹
        N x N 체스판에서 모든 경우의 수를 체크함
        단, 퀸끼리 공격이 가능한 경우 유망하지 않다고 판단함
    */

    static int[] queens;
    static int result;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        queens = new int[N];
        result = 0;

        backtracking(0);
        System.out.println(result);
    }

    static void backtracking(int current) {
        for (int i = 1; i <= N; i++) {
            if (isPromising(current, i)) {
                queens[current] = i;
                if (current == N - 1) {
                    result++;
                    return;
                }
                backtracking(current + 1);
                queens[current] = 0;
            }
        }
    }

    static boolean isPromising(int current, int i) {
        // current에 i를 놓는게 유망한지 아닌지 체크
        // 가로 체크
        for (int j = 0; j < current; j++) {
            if (queens[j] == i) {
                return false;
            }
        }
        // 좌측 대각선 체크
        int temp = 1;
        for (int j = 1; j <= current; j++) {
            if (queens[current - j] == i - temp ||
                    queens[current - j] == i + temp) {
                return false;
            }
            temp++;
        }

        return true;
    }
}
