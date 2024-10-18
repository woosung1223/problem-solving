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
        이분 그래프인지 아닌지 판별하라
        테스트 케이스 K
        첫 줄에는 V, E

        V <= 20_000
        E <= 200_000

        이분 그래프가 되려면?
        그래프의 정점을 둘로 분할했을 때
        집합 내 정점끼리는 인접하지 않아야 함

        => A - B - C = {A, C}, {B}
        임의의 한 정점에서 시작하여, 집합에 추가함

        A(1집합) => B(2집합) => C(1집합) => D(2집합) ...
        결국 인접한 노드와 색깔이 같지 않게 하는 문제와 동일한 것임

        A - B - C - D - B의 경우, 어떻게 색깔을 칠하더라도 안됨
        A(1) - B(2) - C(1) - D(2)

        즉, 사이클이 있는 건 상관이 없는데,
        사이클이 생겼을 때 색깔 규칙에 어긋난다면 이분 그래프 불가능
    */

    static List<Integer>[] graph;
    static boolean[] visited;
    static boolean[] color;
    static boolean cantBe;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());
        while (testCase-- > 0) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            if (V == 1) {
                System.out.println("NO");
                continue;
            }

            graph = new List[V + 1];

            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[from].add(to);
                graph[to].add(from);
            }

            visited = new boolean[V + 1];
            color = new boolean[V + 1];
            cantBe = false;

            for (int j = 1; j <= V; j++) {
                if (!visited[j]) {
                    bfs(j);
                }
            }
            
            System.out.println(cantBe ? "NO" : "YES");
        }
    }

    static void bfs(int current) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(current);
        visited[current] = true;

        while(!queue.isEmpty()) {
            int point = queue.poll();

            for (int next : graph[point]) {
                if (visited[next] && color[point] == color[next]) {
                    cantBe = true;
                    return;
                } else if (visited[next]) {
                    continue;
                }

                visited[next] = true;
                color[next] = !color[point];
                queue.add(next);
            }
        }
    }
}
