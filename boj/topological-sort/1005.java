import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /*
        특정 건물을 지으면 승리
        단, 건물을 짓는 데에는 순서, 소요 시간이 있음
        또한 건설 순서는 꼭 지켜져야 함 (건설이 완료되는 순서)

        노드 간 선행, 후행 관계가 있음 => 위상정렬
        다만 정렬된 노드 집합은 필요하지 않고, 그 과정에서 거리 배열을 최신화 (DP)

     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        while (T-- > 0) {
            StringTokenizer stz = new StringTokenizer(reader.readLine());
            int N = Integer.parseInt(stz.nextToken()); // 건물 개수
            int K = Integer.parseInt(stz.nextToken()); // 규칙의 개수

            stz = new StringTokenizer(reader.readLine());

            boolean[][] graph = new boolean[N + 1][N + 1];
            int[] cost = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                cost[i] = Integer.parseInt(stz.nextToken());
            }

            // 진입 차수
            int[] indegree = new int[N + 1];

            for (int i = 0; i < K; i++) {
                stz = new StringTokenizer(reader.readLine());

                int start = Integer.parseInt(stz.nextToken());
                int end = Integer.parseInt(stz.nextToken());

                graph[start][end] = true;
                indegree[end]++;
            }

            int W = Integer.parseInt(reader.readLine()); // 목표 건물

            // 거리 배열 갱신
            int[] distance = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                distance[i] = cost[i];
            }

            Queue<Integer> queue = new LinkedList<>();

            // 시작점 찾기, indegree가 0인 노드
            // 단, 모든 노드가 연결되어 있다는 보장이 없으므로, indegree가 0인 노드가 여러개 일 수 있음
            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) {
                    queue.add(i);
                }
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();

                if (current == W) {
                    break;
                }

                boolean[] available = graph[current];
                for (int i = 1; i <= N; i++) {
                    if (available[i]) {
                        distance[i] = Math.max(distance[i],
                                distance[current] + cost[i]);

                        indegree[i]--;
                        // 진입점이 하나라면, 유일한 경로이자 선택 가능하므로 진입
                        if (indegree[i] == 0) {
                            queue.add(i);
                        }
                    }
                }
            }

            System.out.println(distance[W]);
        }
    }
}
