import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        List<Dot> dots = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            dots.add(new Dot(x, y));
        }

        dots.sort((x, y) -> {
            if (x.getX() == y.getX()) {
                return x.getY() - y.getY();
            } else {
                return x.getX() - y.getX();
            }
        });

        for (Dot each : dots) {
            System.out.println(each);
        }

    }

    static class Dot {
        int x;
        int y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public String toString() {
            return String.valueOf(x) + " " + String.valueOf(y);
        }
    }
}
