import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(line);

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        String line2 = reader.readLine();
        String[] split = line2.split(" ");

        int max = -1;
        for (int i = 0; i < split.length; i++) {
            for (int j = i + 1; j < split.length; j++) {
                for (int k = j + 1; k < split.length; k++) {
                    int x = Integer.parseInt(split[0]);
                    int y = Integer.parseInt(split[1]);
                    int z = Integer.parseInt(split[2]);

                    if (x + y + z > max && x + y + z < m) {
                        max = x + y + z;
                    }
                }
            }
        }

        System.out.println(max);
    }
}
