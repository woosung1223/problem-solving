import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;

    static int[] items;
    static List<Node>[] matrix;

    static int INF = 100 * 15;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(stz.nextToken());
        m = Integer.parseInt(stz.nextToken());
        int r = Integer.parseInt(stz.nextToken());

        items = new int[n + 1];

        stz = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(stz.nextToken());
        }

        matrix = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            matrix[i] = new ArrayList<>();
        }

        for (int i = 0; i < r; i++) {
            stz = new StringTokenizer(reader.readLine());

            int from = Integer.parseInt(stz.nextToken());
            int to = Integer.parseInt(stz.nextToken());
            int cost = Integer.parseInt(stz.nextToken());

            matrix[from].add(new Node(to, cost));
            matrix[to].add(new Node(from, cost));
        }

        // 수색범위 m, 얻을 수 있는 최대 아이템 개수

        int max = -1;
        for (int i = 1; i <= n; i++) {
            int result = dijkstra(i);
            max = Math.max(result, max);
        }

        System.out.println(max);
    }

    static int dijkstra(int startNode) {
        int[] distance = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            distance[i] = INF;
        }
        distance[startNode] = 0;

        Queue<Node> queue = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        queue.add(new Node(startNode, 0));

        while (!queue.isEmpty()) {
            Node next = queue.poll();

            for (Node available : matrix[next.number]) {
                if (next.cost + available.cost <= m &&
                        next.cost + available.cost < distance[available.number]) {
                    distance[available.number] = next.cost + available.cost;
                    queue.add(new Node(available.number, distance[available.number]));
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] <= m) {
                result += items[i];
            }
        }
        return result;
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
