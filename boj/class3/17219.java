import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        Map<String, String> hash = new HashMap<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer innerLoopStz = new StringTokenizer(reader.readLine());
            hash.put(innerLoopStz.nextToken(), innerLoopStz.nextToken());
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < M; i++) {
            String key = reader.readLine();
            writer.write(hash.get(key) + "\n");
        }
        writer.flush();

        reader.close();
        writer.close();
    }
}
