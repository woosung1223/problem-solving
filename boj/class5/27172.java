import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /*
        내 수로 상대 수를 나누어 떨어뜨릴 수 있다면 승리 (반대의 경우 패배)
        모든 사람은 다른 사람들과 한 번씩은 대결해야 함
        각 플레이어가 가지고 있는 수를 알 때,점수를 계산

        N <= 100_000

        N이 최대 100_000이므로 O(N^2) 알고리즘은 불가능
        즉, 각 수를 순회하면서 다른 모든 수를 비교하는 게 불가능하다는 의미

        배수만 검증
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int[] arr = new int[N];
        boolean[] exists = new boolean[1_000_001];
        int max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
            max = Math.max(max, arr[i]);
            exists[arr[i]] = true;
        }

        int[] score = new int[1_000_001];
        for (int i = 0; i < N; i++) {
            int number = arr[i];
            for (int j = number * 2; j <= max; j += number) {
                if (exists[j]) {
                    score[j]--;
                    score[number]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(score[arr[i]] + " ");
        }
        System.out.println(sb.toString().trim());
    }
}
