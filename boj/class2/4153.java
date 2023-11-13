import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = reader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(line, " ");

            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());

            if (a == 0 && b == 0 && c == 0) {
                break;
            }
            List<Integer> list = Arrays.asList(a, b, c);
            Collections.sort(list);

            if (list.get(0) * list.get(0) + list.get(1) * list.get(1) == list.get(2) * list.get(2)) {
                System.out.println("right");
                continue;
            }
            System.out.println("wrong");
        }
    }
}
