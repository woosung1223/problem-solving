import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String word = reader.readLine();
            map.put(word, 0);
        }

        for (int i = 0; i < M; i++) {
            String word = reader.readLine();

            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            }
        }

        int count = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            count += entry.getValue();
        }

        System.out.println(count);
    }
}
