import java.math.BigInteger;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        String line = reader.readLine();

        int r = 31;
        int m = 1_234_567_891;

        BigInteger result = BigInteger.valueOf(0);
        BigInteger rSum = BigInteger.valueOf(1);

        for (int i = 0; i < line.length(); i++) {
            long value = line.charAt(i) - 'a' + 1;
            BigInteger toAdd = rSum.multiply(BigInteger.valueOf(value));
            result = result.add(toAdd);
            rSum = rSum.multiply(BigInteger.valueOf(r));
        }

        System.out.println(result.subtract(BigInteger.valueOf(m).multiply(result.divide(BigInteger.valueOf(m)))));
    }
}
