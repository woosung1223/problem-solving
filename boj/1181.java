import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        String[] words = new String[size];

        for (int i = 0; i < size; i++) {
            words[i] = reader.readLine();
        }

        Arrays.sort(words, (x, y) -> {
            if (x.length() < y.length()) {
                return -1;
            } else if (x.length() > y.length()) {
                return 1;
            } else {
                return x.compareTo(y);
            }
        });

        Arrays.stream(words)
                .distinct()
                .forEach(System.out::println);
    }
}
