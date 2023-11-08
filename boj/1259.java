import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while (!(line = reader.readLine()).equals("0")) {
            StringBuilder sb = new StringBuilder(line);

            if (sb.equals(sb.reverse())) {
                System.out.println("yes");
                continue;
            }
            System.out.println("no");
        }
    }
}
