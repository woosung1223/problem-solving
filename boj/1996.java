import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        for (int i = 0; i < size; i++) {
            int count = 0;
            StringTokenizer stz = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stz.nextToken());
            int m = Integer.parseInt(stz.nextToken());

            stz = new StringTokenizer(reader.readLine());
            Queue<Integer> queue = new LinkedList<>();

            for (int j = 0; j < n; j++) {
                int number = Integer.parseInt(stz.nextToken());
                queue.add(number);
            }

            while (!queue.isEmpty()) {
                if (isBiggerPresent(queue)) {
                    int removed = queue.remove();
                    queue.add(removed);

                    if (m == 0) {
                        m = queue.size() - 1;
                    } else {
                        m--;
                    }

                } else {
                    if (m == 0) {
                        System.out.println(count + 1);
                        break;
                    }
                    queue.remove();
                    m--;
                    count++;
                }
            }
        }
    }

    public static boolean isBiggerPresent(Queue<Integer> queue) {
        List<Integer> list = new LinkedList<>(queue);
        int first = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > first) {
                return true;
            }
        }
        return false;
    }
}
