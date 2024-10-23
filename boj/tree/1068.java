import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*
        트리 노드의 개수 (N <= 50)
        노드 하나를 지웠을 때 리프 노드의 개수는?

        트리는 각 노드가 하나의 부모를 가지므로,
        지우고자 하는 노드와 부모 노드의 엣지를 무효화시킴

        그리고 In/Pre/Post Order 탐색 아무거나 하면서
        리프 노드 개수 탐색하기

        노드 지우기 => O(n)
        탐색하기 => O(n)
     */

    static boolean[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        graph = new boolean[N][N];

        int root = 0;

        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1) {
                graph[parent][i] = true;
            } else {
                root = i;
            }
        }

        int delete = Integer.parseInt(reader.readLine());
        if (delete == root) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (graph[i][delete]) {
                graph[i][delete] = false;
            }
        }

        // 탐색
        int result = recur(root);
        System.out.println(result);
    }

    static int recur(int current) {
        int result = 0;
        boolean hasChild = false;
        for (int i = 0; i < graph.length; i++) {
            if (graph[current][i]) {
                result += recur(i);
                hasChild = true;
            }
        }

        if (!hasChild) {
            return 1;
        }

        return result;
    }
}
