import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    /*
        최소 신장 트리?
         1. 주어진 그래프의 모든 정점들을 연결하면서
         2. 가중치의 합이 최소가 되는 트리

        그래프는 사이클이 있는 상태로 주어진다는 것을 유의

        벨만포드 알고리즘은 O(VE)이므로 시간 초과

        가중치의 합이 최소가 되려면?
        엣지를 가중치가 작은 순으로 정렬하고, 가장 가중치가 작은 엣지부터 순회
        만약 엣지의 from, to 중 하나라도 방문하지 않은 노드라면 최단 가중치 확정

        정렬 후 가중치가 작은 엣지부터 순회하는 것이기에,
        후에 탐색할 엣지에서 더 가중치가 짧아지는 경우가 없음

        즉, a - b 가 최소 가중치로 확정되었다면,
        a - c - b 는 어떻게 되든 더 작은 가중치를 가질 수 없다
        (정렬을 했기 때문에, a - c 혹은 c - b가 음수더라도 해당 부분부터 체크하기에 문제 X)

        단, 트리는 특정 정점에서 다른 정점으로의 경로가 유일해야 하기 때문에,
        유니온 파인드 방식을 통해 엣지가 가르키는 두 노드가 같은 부모를 가지는지 체크
     */

    static Integer[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int V = Integer.parseInt(stz.nextToken());
        int E = Integer.parseInt(stz.nextToken());

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            stz = new StringTokenizer(reader.readLine());

            int A = Integer.parseInt(stz.nextToken());
            int B = Integer.parseInt(stz.nextToken());
            int C = Integer.parseInt(stz.nextToken());

            edges.add(new Edge(A, B, C));
        }

        edges.sort((x, y) -> x.cost - y.cost);

        parents = new Integer[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }

        int totalCost = 0;
        for (Edge edge : edges) {
            if (find(edge.from) == find(edge.to)) {
                continue;
            }
            // union
            union(edge.from, edge.to);
            totalCost += edge.cost;
        }

        System.out.println(totalCost);
    }

    static int find(int vertex) {
        if (parents[vertex] != vertex) {
            parents[vertex] = find(parents[vertex]);
        }
        return parents[vertex];
    }

    static void union(int vertex1, int vertex2) {
        int parent1 = find(vertex1);
        int parent2 = find(vertex2);

        parents[parent1] = parent2;
    }

    static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
