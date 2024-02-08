import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());

        List<Pair> list = new ArrayList<>();

        while (N-- > 0) {
            stz = new StringTokenizer(reader.readLine());

            list.add(new Pair(
                    Integer.parseInt(stz.nextToken()),
                    Integer.parseInt(stz.nextToken())
            ));
        }

        list.sort((a, b) -> {
            if (a.y == b.y) {
                return a.x - b.x;
            } else {
                return a.y - b.y;
            }
        });

        int result = 1;
        Pair before = list.get(0);

        for (Pair each : list) {
            if (each.x >= before.y && before != each) {
                before = each;
                result++;
            }
        }

        System.out.println(result);
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}
