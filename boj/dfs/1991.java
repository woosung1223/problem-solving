import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static Map<String, List<String>> graph;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        graph = new HashMap<>();
        N = Integer.parseInt(reader.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer stz = new StringTokenizer(reader.readLine());

            String middle = stz.nextToken();
            String left = stz.nextToken();
            String right = stz.nextToken();

            List<String> nodes = new ArrayList<>();
            nodes.add(left);
            nodes.add(right);

            graph.put(middle, nodes);
        }

        String root = "A";

        dfs1(root);
        System.out.println();
        dfs2(root);
        System.out.println();
        dfs3(root);
    }

    static void dfs1(String currentNode) {
        if (currentNode.equals(".")) {
            return;
        }
        System.out.print(currentNode);

        List<String> nodes = graph.get(currentNode);
        dfs1(nodes.get(0));
        dfs1(nodes.get(1));
    }

    static void dfs2(String currentNode) {
        if (currentNode.equals(".")) {
            return;
        }
        List<String> nodes = graph.get(currentNode);
        dfs2(nodes.get(0));
        System.out.print(currentNode);
        dfs2(nodes.get(1));
    }

    static void dfs3(String currentNode) {
        if (currentNode.equals(".")) {
            return;
        }
        List<String> nodes = graph.get(currentNode);
        dfs3(nodes.get(0));
        dfs3(nodes.get(1));
        System.out.print(currentNode);
    }
}
