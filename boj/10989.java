import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        short[] numbers = new short[size];

        for (int i = 0; i < size; i++) {
            short number = Short.parseShort(reader.readLine());
            numbers[i] = number;
        }

        int[] counting = new int[10001];

        for (short number : numbers) {
            counting[number]++;
        }

        for (int i = 1; i < counting.length; i++) {
            counting[i] += counting[i - 1];
        }

        short[] results = new short[size];
        for (int i = 0; i < size; i++) {
            short number = numbers[i];
            counting[number]--;
            results[counting[number]] = number;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (short result : results) {
            stringBuilder.append(result)
                    .append('\n');
        }

        System.out.println(stringBuilder);
    }
}
