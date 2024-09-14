import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    /*
        정점의 수 10만, 쿼리의 수 10만이기 때문에 단순 탐색은 시간 초과 (100억 연산)
        분할정복을 하면서, 배열에 값을 저장하는 방식으로 해결
        count(parent) = count(child_1) + count(child_2) + ... + count(child_n)

        다만 문제는 입력값으로부터 어떻게 트리를 만들어낼 것인가
        입력은 U, V로 구성되는데, 어떤 것이 Parent가 될 것인가는 결정할 수 없음

        우리가 아는 정보는 R(루트의 번호)
        이를 토대로 엣지를 연결시켜야 함

        우선 양방향으로 다 저장하고, 루트부터 탐색하면서
        부모 노드는 진행 X
     */

    /*
               5
            4    6
           3     7 8 9
          1 2
    */

    static Map<Integer, List<Integer>> tree;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken()); // 정점 수
        int R = Integer.parseInt(stz.nextToken()); // 루트 번호
        int Q = Integer.parseInt(stz.nextToken()); // 쿼리의 수

        tree = new HashMap<>();
        count = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            stz = new StringTokenizer(reader.readLine());

            int U = Integer.parseInt(stz.nextToken());
            int V = Integer.parseInt(stz.nextToken());

            List<Integer> children1 = tree.getOrDefault(U, new ArrayList<>());
            children1.add(V);
            tree.put(U, children1);

            List<Integer> children2 = tree.getOrDefault(V, new ArrayList<>());
            children2.add(U);
            tree.put(V, children2);
        }

        recur(R, R);

        for (int i = 0; i < Q; i++) {
            int target = Integer.parseInt(reader.readLine());
            System.out.println(count[target]);
        }
    }

    static int recur(int number, int parent) {
        if (count[number] != 0) {
            return count[number];
        }

        if (!tree.containsKey(number) || tree.get(number).size() == 0) {
            count[number] = 1;
            return count[number];
        }


        List<Integer> children = tree.get(number);

        int sum = 0;
        for (int child : children) {
            if (parent != child) {
                sum += recur(child, number);
            }
        }

        count[number] = sum + 1; // 자기 자신도 포함
        return count[number];
    }
}
