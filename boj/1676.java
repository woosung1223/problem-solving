import java.math.BigDecimal;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        BigDecimal result = factorial(BigDecimal.valueOf(n));
        String string = result.toString();

        int count = 0;
        for (int i = string.length() - 1; i >= 0; i--) {
            if (string.charAt(i) != '0') {
                break;
            }
            if (string.charAt(i) == '0') {
                count++;
            }
        }
        System.out.println(count);
    }

    public static BigDecimal factorial(BigDecimal n) {
        if (n.equals(BigDecimal.valueOf(0))) {
            return BigDecimal.valueOf(1);
        } else {
            return factorial(n.subtract(BigDecimal.valueOf(1)))
                    .multiply(n);
        }
    }
}
