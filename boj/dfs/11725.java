import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer> graph[];
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        graph = new ArrayList[N + 1];
        parent = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer stz = new StringTokenizer(reader.readLine());

            int node1 = Integer.parseInt(stz.nextToken());
            int node2 = Integer.parseInt(stz.nextToken());

            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        dfs(1, 1);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 2; i <= N; i++) {
            writer.write(parent[i] + "\n");
        }

        writer.flush();
    }

    static void dfs(int before, int current) {
        if (visited[current]) {
            return;
        }

        parent[current] = before;
        visited[current] = true;
        List<Integer> nodes = graph[current];
        for (int node : nodes) {
            dfs(current, node);
        }
    }
}
