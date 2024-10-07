import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    /*
        한 아이의 사탕을 뺴앗으면 그 아이의 친구들 사탕도 뺏어야 함

        아이들의 친구 관계는 무방향 간선으로 구성됨
        또한, 사이클이 있을 수 있는 그래프 형태임

        아이를 K번 미만으로 거치면서, 최대 얻을 수 있는 사탕 개수는?

        Knapsack 문제와 비슷함
            비용 => 그래프 내 노드의 개수
            이익 => 그래프 내 노드들의 사탕 개수 총합

        그래프 번호를 G(G >= 1), 아이 명수를 C(C >= 1)라고 할 때,
            COST[G][C] = G 그래프까지 C명의 아이까지로 얻을 수 있는 최대 사탕 개수
            CANDY[G] = G 그래프의 캔디 총 개수

        점화식 ?
            1. 이미 아이 수가 K 이상인 경우 (더 아이를 추가할 필요가 없음)
            2. 그렇지 않은 경우 (이전 그룹의 결과에서 현재 그룹만큼의 공간을 만들고,
                거기서 현재 그룹이 소지한 캔디 수를 더함)

            COST[G][C] = Math.max(COST[G - 1][C- SUM(KIDS[G])] + CANDY[G]
                COST[G-1][C]);

        완전탐색으로 KIDS 배열을 구해야 함 => 시간초과?
        엣지가 주어지므로, 유니온 파인드를 통해 바로바로 그룹화가 가능함
     */

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());

        int[] candy = new int[N + 1];
        parent = new int[N + 1];

        stz = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= N; i++) {
            candy[i] = Integer.parseInt(stz.nextToken());
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(reader.readLine());

            int from = Integer.parseInt(stz.nextToken());
            int to = Integer.parseInt(stz.nextToken());

            union(from, to);
        }

        int[] candyWithGroups = new int[N + 1];
        int[] kidsWithGroups = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int parent = findParent(i);
            candyWithGroups[parent] += candy[i];
            kidsWithGroups[parent]++;
        }

        int [][] cost = new int[N + 1][K];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < K; j++) {

                if (j - kidsWithGroups[i] >= 0) {
                    cost[i][j] = Math.max(cost[i - 1][j - kidsWithGroups[i]] + candyWithGroups[i],
                            cost[i - 1][j]);
                } else {
                    cost[i][j] = cost[i - 1][j];
                }
            }
        }

        System.out.println(cost[N][K - 1]);
    }

    static int findParent(int i) {
        if (parent[i] != i) {
            parent[i] = findParent(parent[i]);
        }
        return parent[i];
    }

    static void union(int x, int y) {
        int parentX = findParent(x);
        int parentY = findParent(y);

        if (parentX == parentY) {
            return;
        }

        if (parentX < parentY) {
            parent[parentY] = parentX;
        } else {
            parent[parentX] = parentY;
        }
    }
}
