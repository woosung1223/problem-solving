import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        String line = reader.readLine();
        StringTokenizer stz = new StringTokenizer(line);

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int num = Integer.parseInt(stz.nextToken());
            numbers.add(num);
        }

        int max = Collections.max(numbers);
        int sum = 0;
        for (int each : numbers) {
            if (each != max) {
                sum += (double) each / max * 100;
            }
        }
        System.out.println(sum / numbers.size());
    }
}
