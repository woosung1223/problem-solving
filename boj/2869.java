import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int go = Integer.parseInt(stz.nextToken());
        int back = Integer.parseInt(stz.nextToken());
        int target = Integer.parseInt(stz.nextToken());

        int day = (target - back) / (go - back);

        if ((target - back) % (go - back) != 0) {
            day++;
        }

        System.out.println(day);
    }
}
