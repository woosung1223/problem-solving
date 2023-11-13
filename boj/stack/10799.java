import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        int opened = 0;
        int result = 0;

        for (int i = 0; i < line.length(); i++) {
            char target = line.charAt(i);

            if (target == '(') {
                opened++;
            } else {
                if (line.charAt(i - 1) == '(') {
                    opened--;
                    result += opened;
                } else {
                    result++;
                    opened--;
                }
            }
        }
        System.out.println(result);
    }
}
