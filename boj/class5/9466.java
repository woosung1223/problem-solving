import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /*
        사이클이 없는 노드를 구하는 문제

        사이클이 있는 경우는,
        ex. 1 -> 2 -> 3 -> 1 ...
        ex. 1 -> 1

        주의) 노드는 노드 자신을 선택할 수도 있음 (이 경우도 사이클로 봄)

        노드의 수 n <= 100_000

        사이클이 없다는 것을 어떻게 알까?

        해당 노드에서 출발하여 방향을 따라 가다가, 자기 자신으로 돌아오기 전에,
        다른 이미 방문했던 노드에 도착하면 사이클이 없다는 것

        사이클을 탐지하는 방법은,
        1. 위상정렬 후 방문하지 않은 노드를 체크
        2. DFS를 이용해 두 번 방문하는 노드를 체크

        방향성이 있는 그래프이므로 위상 정렬 가능

        차수가 0인 노드들을 체크하고, 위상정렬
        이후 방문한 노드들은 어느 팀에도 속하지 않는 것임

        위상정렬의 시간복잡도는 모든 노드에 대하여 모든 엣지를 체크해야 하므로 O(V + E)
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(reader.readLine());
            int[] arr = new int[n];
            int[] indegree = new int[n];

            StringTokenizer stz = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stz.nextToken()) - 1;
                indegree[arr[i]]++;
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (indegree[i] == 0) {
                    queue.add(i);
                }
            }

            boolean[] visited = new boolean[n];
            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (visited[current]) { // 사이클이 생기기 시작한 경우 PASS
                    continue;
                }

                visited[current] = true;

                int next = arr[current];
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }

            int result = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    result++;
                }
            }

            System.out.println(result);
        }
    }
}
