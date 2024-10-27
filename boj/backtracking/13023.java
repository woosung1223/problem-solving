import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    /*
        A - B - C - D - E 관계가 존재하는지?
        사람들은 N-1명 (N <= 2000)
        친구 관계의 수 (M <= 2000)

        그래프의 길이가 5 이상이 되는지 체크
        DFS의 depth가 5가 되는 경우를 체크하면 됨
    */
    static List<Integer>[] friend;
    static boolean[] visited;
    static boolean result;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        friend = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            friend[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friend[a].add(b);
            friend[b].add(a);
        }

        result = false;
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            dfs(i, 1);
            if (result) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }


    static void dfs(int current, int depth) {
        if (depth >= 5) {
            result = true;
            return;
        }
        visited[current] = true;
        for (int next : friend[current]) {
            if (!visited[next]) {
                dfs(next, depth + 1);
            }
        }
        visited[current] = false;
    }
}
