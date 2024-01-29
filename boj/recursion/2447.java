import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static boolean[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        matrix = new boolean[size][size];
        recur(0, 0, size);

        StringBuilder sb = new StringBuilder();

        for (boolean[] booleans : matrix) {
            for (boolean b : booleans) {
                sb.append(b ? "*" : " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void recur(int x, int y, int size) {
        if (size == 3) {
            matrix[x][y] = true;
            matrix[x][y + 1] = true;
            matrix[x][y + 2] = true;

            matrix[x + 1][y] = true;
            matrix[x + 1][y + 1] = false;
            matrix[x + 1][y + 2] = true;

            matrix[x + 2][y] = true;
            matrix[x + 2][y + 1] = true;
            matrix[x + 2][y + 2] = true;

            return;
        }

        recur(x, y, size / 3);
        recur(x, y + size / 3, size / 3);
        recur(x, y + size / 3 * 2, size / 3);

        recur(x + size / 3, y, size / 3);
        recur(x + size / 3, y + size / 3 * 2, size / 3);

        recur(x + size / 3 * 2, y, size / 3);
        recur(x + size / 3 * 2, y + size / 3, size / 3);
        recur(x + size / 3 * 2, y + size / 3 * 2, size / 3);
    }
}
