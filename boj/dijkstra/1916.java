import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import org.w3c.dom.Node;

public class Main {

    static Map<Integer, List<Node>> graph;
    static long INF = 100_000L * 100_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int M = Integer.parseInt(reader.readLine());

        graph = new HashMap<>();

        for (int i = 0; i < M; i++) {
            StringTokenizer stz = new StringTokenizer(reader.readLine());

            int from = Integer.parseInt(stz.nextToken());
            int to = Integer.parseInt(stz.nextToken());
            int value = Integer.parseInt(stz.nextToken());

            List<Node> nodes = graph.getOrDefault(from, new ArrayList<>());
            nodes.add(new Node(to, value));
            graph.put(from, nodes);
        }

        StringTokenizer stz = new StringTokenizer(reader.readLine());
        int start = Integer.parseInt(stz.nextToken());
        int end = Integer.parseInt(stz.nextToken());

        long[] distance = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            distance[i] = INF;
        }
        distance[start] = 0;

        Queue<Node> queue = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        queue.add(new Node(start, 0));

        for (int i = 0; i < N - 1; i++) {
            if (!queue.isEmpty()) {
                Node next = queue.poll();

                List<Node> nodes = graph.getOrDefault(next.number, new ArrayList<>());
                for (Node node : nodes) {
                    if (node.cost + next.cost < distance[node.number]) {
                        distance[node.number] = node.cost + next.cost;
                        queue.add(new Node(node.number, (int) distance[node.number]));
                    }
                }
            }
        }

        System.out.println(distance[end]);
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
