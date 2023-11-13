import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static boolean[] cached = new boolean[21];

    static {
        for (int i = 0; i < 21; i++) {
            cached[i] = true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(reader.readLine());

        boolean[] bits = new boolean[21];

        for (int i = 0; i < size; i++) {
            String command = reader.readLine();

            if (command.contains("add")) {
                int number = Integer.parseInt(command.split(" ")[1]);
                bits[number] = true;

            } else if (command.contains("remove")) {
                int number = Integer.parseInt(command.split(" ")[1]);
                bits[number] = false;

            } else if (command.contains("check")) {
                int number = Integer.parseInt(command.split(" ")[1]);
                writer.write((bits[number] ? 1 : 0) + "\n");

            } else if (command.contains("toggle")) {
                int number = Integer.parseInt(command.split(" ")[1]);
                bits[number] = !bits[number];

            } else if (command.contains("all")) {
                bits = Arrays.copyOf(cached, 21);

            } else if (command.contains("empty")) {
                bits = new boolean[21];
            }
        }
        writer.flush();
    }
}
