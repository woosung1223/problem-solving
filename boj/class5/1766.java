import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {

    /*
        1번부터 N번의 문제 -> 번호가 난이도를 나타냄
        3번 조건 (가능하면 쉬운 문제부터 풀어야 한다)을 배제하면 단순한 위상정렬 문제

        3번 조건을 해결하는 방법은?
        선행 - 후행 관계를 탐색하다가도, 더 작은 난이도의 '탐색 가능한' 노드가 있다면 탐색해야 함

        ex. 1 -> 4의 이 경우 1 -> 2 -> 3 -> 4가 되어야 함

        PriorityQueue로 해결할 수 있지 않을까..?
        PQ에는 차수가 0인 노드만 저장
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        List<Integer>[] graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(reader.readLine());

            int from = Integer.parseInt(stz.nextToken());
            int to = Integer.parseInt(stz.nextToken());

            graph[from].add(to);
            indegree[to]++;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        StringJoiner result = new StringJoiner(" ");

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(String.valueOf(current));

            for (int adjacent : graph[current]) {
                indegree[adjacent]--;

                if (indegree[adjacent] == 0) {
                    queue.add(adjacent);
                }
            }
        }

        System.out.println(result);
    }
}
