import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int INF = 3_000_001; // 거리의 최대값은 300_000 x 10

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int V = Integer.parseInt(stz.nextToken());
        int E = Integer.parseInt(stz.nextToken());

        int startNode = Integer.parseInt(reader.readLine());
        Map<Integer, List<Node>> graph = new HashMap<>();

        for (int i = 0; i < E; i++) {
            stz = new StringTokenizer(reader.readLine());

            int u = Integer.parseInt(stz.nextToken());
            int v = Integer.parseInt(stz.nextToken());
            int w = Integer.parseInt(stz.nextToken());

            List<Node> nodes = graph.getOrDefault(u, new ArrayList<>());
            nodes.add(new Node(v, w));
            graph.put(u, nodes);
        }

        int[] distance = new int[V + 1];
        boolean[] visited = new boolean[V + 1];

        for (int i = 1; i <= V; i++) {
            distance[i] = INF;
        }
        distance[startNode] = 0;

        Queue<Node> queue = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        queue.add(new Node(startNode, 0));

        while (!queue.isEmpty()) {
            int currentIndex = queue.poll().index;
            visited[currentIndex] = true;

            for (Node node : graph.getOrDefault(currentIndex, new ArrayList<>())) {
                if (!visited[node.index] &&
                        distance[currentIndex] + node.cost < distance[node.index]) {
                    distance[node.index] = distance[currentIndex] + node.cost;
                    queue.add(new Node(node.index, distance[node.index]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            System.out.println(distance[i] == INF ? "INF" : distance[i]);
        }
    }

    static class Node {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
}
