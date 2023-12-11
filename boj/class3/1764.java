package harustudy.backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
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

        Map<String, Integer> count = new HashMap<>();

        for (int i = 0; i < N + M; i++) {
            String name = reader.readLine();
            count.put(name, count.getOrDefault(name, 0) + 1);
        }

        List<String> results = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() >= 2) {
                results.add(entry.getKey());
            }
        }

        Collections.sort(results);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(results.size() + "\n");
        for (String result : results) {
            writer.write(result + "\n");
        }
        writer.flush();

        reader.close();
        writer.close();
    }
}
