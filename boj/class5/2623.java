import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /*
        노드 간의 선행관계가 주어지고, 정렬하는 문제
        => 위상정렬

        단, 정렬이 안되는 경우도 있다고 함
        이 경우는 탐색 과정에서 모든 노드를 방문하지 못했을 때임

     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken()); // 가수의 수
        int M = Integer.parseInt(stz.nextToken()); // 보조의 수

        List<Integer>[] graph = new ArrayList[N + 1];
        int[] inDegree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(reader.readLine());

            int count = Integer.parseInt(stz.nextToken());
            int beforeNode = -1;

            for (int j = 0; j < count; j++) {
                int temp = Integer.parseInt(stz.nextToken());
                if (beforeNode == -1) {
                    beforeNode = temp;
                    continue;
                }

                graph[beforeNode].add(temp);
                inDegree[temp]++;
                beforeNode = temp;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }


        boolean[] visited = new boolean[N + 1];
        List<Integer> sorted = new ArrayList<>();

        while (!queue.isEmpty()) {
            int current = queue.poll();

            sorted.add(current);
            visited[current] = true;

            for (int next : graph[current]) {
                inDegree[next]--;

                if (inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                System.out.println(0);
                return;
            }
        }

        for (int each : sorted) {
            System.out.println(each);
        }
    }
}
