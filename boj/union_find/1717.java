import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(stz.nextToken());
        int m = Integer.parseInt(stz.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            stz = new StringTokenizer(reader.readLine());

            int op = Integer.parseInt(stz.nextToken());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());

            if (op == 0) { // union
                int aParent = findParent(a);
                int bParent = findParent(b);

                if (aParent != bParent) {
                    if (aParent < bParent) {
                        parent[bParent] = aParent;
                    } else {
                        parent[aParent] = bParent;
                    }
                }
            } else { // find
                int aParent = findParent(a);
                int bParent = findParent(b);

                if (aParent == bParent) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    static int findParent(int i) {
        if (parent[i] != i) {
            parent[i] = findParent(parent[i]);
            return parent[i];
        }
        return parent[i];
    }
}
