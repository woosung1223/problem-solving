import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Edge> edges;

    static int INF = 2500 * 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int TC = Integer.parseInt(reader.readLine());

        while (TC-- > 0) {
            edges = new ArrayList<>();

            StringTokenizer stz = new StringTokenizer(reader.readLine());

            int N = Integer.parseInt(stz.nextToken());
            int M = Integer.parseInt(stz.nextToken());
            int W = Integer.parseInt(stz.nextToken());

            for (int i = 0; i < M; i++) {
                stz = new StringTokenizer(reader.readLine());

                int S = Integer.parseInt(stz.nextToken()); // 연결된 지점의 번호 1
                int E = Integer.parseInt(stz.nextToken()); // 연결된 지점의 번호 2
                int T = Integer.parseInt(stz.nextToken()); // 걸리는 시간

                // 무향
                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }

            List<Integer> searchTargets = new ArrayList<>();
            for (int i = 0; i < W; i++) {
                stz = new StringTokenizer(reader.readLine());

                int S = Integer.parseInt(stz.nextToken()); // 시작 지점
                int E = Integer.parseInt(stz.nextToken()); // 도착 지점
                int T = Integer.parseInt(stz.nextToken()); // 줄어드는 시간

                // 유향
                edges.add(new Edge(S, E, -T));
                searchTargets.add(E);
            }

            // 모든 노드에 대해서 검증할 필요 X
            // 음수 간선과 연결되어 있는 노드만 검증하면 됨
            // 만약 해당 노드들로 돌아온 값이 음수가 아니라면, 다른 노드들은 당연히 음수가 아님

            boolean yes = false;
            for (int i : searchTargets) {
                int[] distance = new int[N + 1];

                for (int j = 1; j <= N; j++) {
                    distance[j] = INF;
                }
                distance[i] = 0;

                for (int j = 0; j < N - 2; j++) {
                    for (Edge edge : edges) {
                        if (distance[edge.from] + edge.value < distance[edge.to]) {
                            distance[edge.to] = distance[edge.from] + edge.value;
                        }
                    }
                }

                if (distance[i] < 0) {
                    yes = true;
                    break;
                }
            }

            if (yes) {
                writer.write("YES" + '\n');
            } else {
                writer.write("NO" + '\n');
            }
        }
        writer.flush();
    }

    static class Edge {
        int from;
        int to;
        int value;

        public Edge(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }
}
