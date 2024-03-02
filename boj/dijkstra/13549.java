import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());

        boolean[] visited = new boolean[100_001];
        int[] distance = new int[100_001];
        visited[N] = true;

        for (int i = 0; i < 100_001; i++) {
            distance[i] = 100_001; // 거리의 최대 크기는 100_000번 +1 혹은 -1로 이동했을 때
        }

        distance[N] = 0;

        Queue<Node> queue = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        queue.add(new Node(N, 0));

        while (!queue.isEmpty()) {
            Node next = queue.poll();

            if (next.index + 1 >= 0 && next.index + 1 <= 100_000 &&
                    distance[next.index] + 1 < distance[next.index + 1]) {
                distance[next.index + 1] = distance[next.index] + 1;
                queue.add(new Node(next.index + 1, distance[next.index + 1]));
            }

            if (next.index - 1 >= 0 && next.index - 1 <= 100_000 &&
                    distance[next.index] + 1 < distance[next.index - 1]) {
                distance[next.index - 1] = distance[next.index] + 1;
                queue.add(new Node(next.index - 1, distance[next.index - 1]));
            }

            if (next.index * 2 >= 0 && next.index * 2 <= 100_000 &&
                    distance[next.index] < distance[next.index * 2]) {
                distance[next.index * 2] = distance[next.index];
                queue.add(new Node(next.index * 2, distance[next.index * 2]));
            }
        }

        System.out.println(distance[K]);
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
