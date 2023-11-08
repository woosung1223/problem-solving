import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String line = reader.readLine();
            numbers.add(Integer.parseInt(line));

        }

        Collections.sort(numbers);

        StringBuilder stringBuilder = new StringBuilder();

        for (Integer each : numbers) {
            stringBuilder.append(each).append('\n');
        }
        System.out.println(stringBuilder);
    }
}

