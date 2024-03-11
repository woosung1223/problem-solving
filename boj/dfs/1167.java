import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Node>[] graph;
    static boolean[] visited;
    static int max;
    static int farthest;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(reader.readLine());

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            StringTokenizer stz = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(stz.nextToken());

            while (stz.hasMoreTokens()) {
                int to = Integer.parseInt(stz.nextToken());
                if (to == -1) {
                    break;
                }
                int cost = Integer.parseInt(stz.nextToken());

                graph[from].add(new Node(to, cost));
                graph[to].add(new Node(from, cost));
            }
        }
        
        /*
        a -> b 노드의 거리가 최장이라면,
        a -> ... -> b 사이의 모든 노드 x에 대해서도 x -> b는 최장거리임
        그 이유는 최장 거리는 DP처럼 부분 최장거리의 합으로 이루어져 있으므로 (다익스트라가 DP 인것을 생각해보면)
        따라서 임의의 노드로부터 가장 먼 노드를 구하고, 해당 노드로부터 가장 먼 길이를 구하면 최장거리
         */

        visited = new boolean[V + 1];
        dfs(1, 0);

        visited = new boolean[V + 1];
        dfs(farthest, 0);

        System.out.println(max);
    }

    static void dfs(int node, int cost) {
        if (visited[node]) {
            return;
        }

        if (cost > max) {
            max = cost;
            farthest = node;
        }

        visited[node] = true;
        List<Node> nodes = graph[node];
        for (Node each : nodes) {
            dfs(each.number, cost + each.cost);
        }
    }

    static class Node {
        int number;
        int cost;

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }
    }
}
