import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(reader.readLine());
        int iterator = 0;
        int result = 0;

        int count = 0;
        while (true) {
            if (String.valueOf(iterator).contains("666")) {
                count++;
            }
            if (num == count) {
                result = iterator;
                break;
            }
            iterator++;
        }
        System.out.println(result);
    }
}
