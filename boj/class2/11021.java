import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        int testCaseNum = 1;

        for (int i = 0; i < size; i++) {
            String line = reader.readLine();
            String[] split = line.split(" ");

            int result = Integer.parseInt(split[0]) + Integer.parseInt(split[1]);

            System.out.println(
                    String.format("Case #%d: %d + %d = %d", testCaseNum++,
                            Integer.parseInt(split[0]), Integer.parseInt(split[1]), result)
            );
        }
    }
}
