import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {

    /*
        학생들의 키가 선후 관계로 주어지는데, 작은 학생부터 정렬하는 문제
        => 위상정렬

        차수가 0인 노드를 시작점으로 설정하고 탐색 진행
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        List<Integer>[] graph = new List[N + 1];
        int[] indegree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(reader.readLine());

            int from = Integer.parseInt(stz.nextToken());
            int to = Integer.parseInt(stz.nextToken());

            graph[from].add(to);
            indegree[to]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            List<Integer> available = graph[current];
            for (int next : available) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        StringJoiner sj = new StringJoiner(" ");
        for (int each : result) {
            sj.add(String.valueOf(each));
        }

        System.out.println(sj.toString());
    }
}
