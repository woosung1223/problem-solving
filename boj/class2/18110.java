import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        int[] scores = new int[size];
        for (int i = 0; i < size; i++) {
            int score = Integer.parseInt(reader.readLine());
            scores[i] = score;
        }

        int toRemove = (int) Math.round(size * 0.15);
        Arrays.sort(scores);
        int sum = 0;

        for (int i = toRemove; i < size - toRemove; i++) {
            sum += scores[i];
        }

        System.out.println((int) Math.round((double) sum / (size - toRemove * 2)));
    }
}
