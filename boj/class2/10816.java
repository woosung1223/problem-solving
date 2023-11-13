import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        StringTokenizer stringTokenizer1 = new StringTokenizer(reader.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int key = Integer.parseInt(stringTokenizer1.nextToken());
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int m = Integer.parseInt(reader.readLine());
        StringTokenizer stringTokenizer2 = new StringTokenizer(reader.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int number = Integer.parseInt(stringTokenizer2.nextToken());
            sb.append(map.getOrDefault(number, 0)).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}
