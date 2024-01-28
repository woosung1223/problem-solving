import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        int fiveNumber = N / 5;
        int threeNumber = 0;

        while (true) {
            int number = fiveNumber * 5 + threeNumber * 3;

            if (number > N) {
              fiveNumber--;
              threeNumber = 0;
              continue;
            } else if (number == N) {
                break;
            } else if (fiveNumber == 0 && N % 3 != 0) {
                System.out.println(-1);
                return;
            }
            threeNumber++;
        }

        System.out.println(fiveNumber + threeNumber);
    }
}
