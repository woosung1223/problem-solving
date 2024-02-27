import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static Map<String, String> parent;
    static Map<String, Integer> counting;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());

        while (testCase-- > 0) {
            int F = Integer.parseInt(reader.readLine());

            parent = new HashMap<>();
            counting = new HashMap<>();

            for (int i = 0; i < F; i++) {
                StringTokenizer stz = new StringTokenizer(reader.readLine());
                String from = stz.nextToken();
                String to = stz.nextToken();

                parent.putIfAbsent(from, from);
                parent.putIfAbsent(to, to);

                counting.putIfAbsent(from, 1);
                counting.putIfAbsent(to, 1);

                union(from, to);

                System.out.println(counting.get(findParent(from)));
            }
        }
    }

    static void union(String start, String to) {
        String startParent = findParent(start);
        String toParent = findParent(to);

        if (!startParent.equals(toParent)) {
            parent.put(toParent, startParent);

            counting.put(startParent, counting.get(startParent) + counting.get(toParent));
            counting.remove(toParent);
        }
    }

    static String findParent(String i) {
        if (!i.equals(parent.get(i))) {
            parent.put(i, findParent(parent.get(i)));
            counting.put(i, counting.getOrDefault(i, 0) + 1);
        }
        return parent.get(i);
    }
}
