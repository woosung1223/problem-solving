import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        int testCase = Integer.parseInt(reader.readLine());

        // 모든 소문자 알파벳에 대해 누적합을 구해두자.

        Map<Character, int[]> map = new HashMap<>();

        for (char i = 'a'; i <= 'z'; i++) {
            int[] arr = new int[line.length()];
            arr[0] = line.charAt(0) == i ? 1 : 0;

            for (int j = 1; j < line.length(); j++) {
                arr[j] = arr[j - 1] + (line.charAt(j) == i ? 1 : 0);
            }

            map.put(i, arr);
        }

        StringBuffer sb = new StringBuffer();

        while (testCase-- > 0) {
            StringTokenizer stz = new StringTokenizer(reader.readLine());

            char target = stz.nextToken().charAt(0);

            int startIndex = Integer.parseInt(stz.nextToken());
            int endIndex = Integer.parseInt(stz.nextToken());

            int[] arr = map.get(target);

            if (startIndex == 0) {
                int result = arr[endIndex];
                sb.append(result).append("\n");
            } else {
                int result = arr[endIndex] - arr[startIndex - 1];
                sb.append(result).append("\n");
            }
        }

        System.out.println(sb);
    }
}
