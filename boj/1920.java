import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int[] nNumbers = new int[n];

        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            nNumbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int m = Integer.parseInt(reader.readLine());
        int[] mNumbers = new int[m];

        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; i++) {
            mNumbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        Arrays.sort(nNumbers);

        for (int i = 0; i < m; i++) {
            if (Arrays.binarySearch(nNumbers, mNumbers[i])) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
