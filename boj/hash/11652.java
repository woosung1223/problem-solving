import java.math.BigDecimal;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        Map<BigDecimal, Integer> hash = new HashMap<>();
        for (int i = 0; i < size; i++) {
            BigDecimal number = new BigDecimal(reader.readLine());
            hash.put(number, hash.getOrDefault(number, 0) + 1);
        }

        int max = -1;
        BigDecimal result = BigDecimal.valueOf(-1);

        for (Map.Entry<BigDecimal, Integer> entry : hash.entrySet()) {
            if (entry.getValue() > max) {
                result = entry.getKey();
                max = entry.getValue();
            } else if (entry.getValue() == max) {
                if (result.compareTo(entry.getKey()) > 0) {
                    result = entry.getKey();
                }
            }

        }

        System.out.println(result);
    }
}
