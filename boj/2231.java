import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        int result = 0;
        for (int i = 0; i < N; i++) {
            int number = i;
            int sum = 0;

            while (number > 0) {
                sum += number % 10;
                number = number / 10;
            }

            if (sum + i == N) {
                result = i;
                break;
            }
        }
        System.out.println(result);
    }
}

