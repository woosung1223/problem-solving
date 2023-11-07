import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String line;

        while (true) {
            line = bufferedReader.readLine();
            String[] split = line.split(" ");
            if (split[0].equals("0") && split[1].equals("0")) {
                break;
            }
            System.out.println(Integer.parseInt(split[0]) + Integer.parseInt(split[1]));
        }
    }
}
