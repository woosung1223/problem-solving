import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int personCount = Integer.parseInt(stz.nextToken());
        int partyCount = Integer.parseInt(stz.nextToken());

        stz = new StringTokenizer(reader.readLine());

        int truthKnowingPersonCount = Integer.parseInt(stz.nextToken());
        List<Integer> truthKnowingPersons = new ArrayList<>();

        if (truthKnowingPersonCount > 0) {
            for (int i = 0; i < truthKnowingPersonCount; i++) {
                truthKnowingPersons.add(Integer.parseInt(stz.nextToken()));
            }
        }

        List<Set<Integer>> parties = new ArrayList<>();

        for (int i = 0; i < partyCount; i++) {
            stz = new StringTokenizer(reader.readLine());

            int count = Integer.parseInt(stz.nextToken());
            Set<Integer> party = new HashSet<>();
            for (int j = 0; j < count; j++) {
                party.add(Integer.parseInt(stz.nextToken()));
            }
            parties.add(party);
        }

        boolean[][] matrix = new boolean[partyCount][partyCount];

        for (int i = 0; i < partyCount; i++) {
            for (int j = i + 1; j < partyCount; j++) {
                Set<Integer> setA = parties.get(i);
                Set<Integer> setB = parties.get(j);

                // 하나라도 연결된다면 집합이 연결된 것으로 간주
                boolean isConnected = false;
                for (Integer each : setA) {
                    if (setB.contains(each)) {
                        isConnected = true;
                        break;
                    }
                }

                if (isConnected) {
                    matrix[i][j] = true;
                    matrix[j][i] = true;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[partyCount];

        for (int i = 0; i < partyCount; i++) {
            Set<Integer> set = parties.get(i);

            for (int j = 0; j < truthKnowingPersonCount; j++) {
                if (set.contains(truthKnowingPersons.get(j))) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int point = queue.poll();
            boolean[] connected = matrix[point];

            for (int i = 0; i < connected.length; i++) {
                if (visited[i]) {
                    continue;
                } else if (!connected[i]) {
                    continue;
                }
                queue.add(i);
                visited[i] = true;
            }
        }

        int result = 0;
        for (boolean visit : visited) {
            if (!visit) {
                result++;
            }
        }

        System.out.println(result);
    }
}
