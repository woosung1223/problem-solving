import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int M = Integer.parseInt(reader.readLine());

        List<Integer> list = new ArrayList<>();

        if (M > 0) {
            StringTokenizer stz = new StringTokenizer(reader.readLine());
            while (stz.hasMoreTokens()) {
                list.add(Integer.parseInt(stz.nextToken()));
            }
        }

        int upOrDownMin = Math.abs(100 - N);
        int jumpLeft = jump(list, N, upOrDownMin, false);
        int jumpRight = jump(list, N, upOrDownMin, true);

        int jumpMin = Math.min(jumpRight, jumpLeft);

        System.out.println(Math.min(upOrDownMin, jumpMin));
    }

    static int jump(List<Integer> list, int n, int min, boolean inc) {
        int number = n;

        for (int i = 0; i < min; i++) {
            String string = String.valueOf(number);
            boolean contains = false;
            for (char c : string.toCharArray()) {
                if (list.contains(c - '0')) {
                    contains = true;
                    break;
                }
            }


            if (!contains) {
                int value = string.length() + Math.abs(number - n);
                if (value < min) {
                    min = value;
                    break;
                }
            }

            if (inc) {
                number++;
            } else {
                number--;
            }
        }

        return min;
    }

}

