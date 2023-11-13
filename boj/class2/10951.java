import java.io.*;

public class Main {

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        
        String line;
        while (line = bufferedReader.readLine() != null) {
            String[] split = line.split(" ");
            
            System.out.println(Integer.parseInt(split[0]) + Integer.parseInt(split[1]));
        }
    }
}
