import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String line = reader.readLine();
            StringTokenizer stz = new StringTokenizer(line);
            points.add(new Point(
                    Integer.parseInt(stz.nextToken()),
                    Integer.parseInt(stz.nextToken())
            ));
        }

        Collections.sort(points, (a, b) -> {
            if (a.getY() > b.getY()) {
                return 1;
            } else if (a.getY() < b.getY()) {
                return -1;
            } else {
                return a.getX() - b.getX();
            }
        });

        points.forEach(System.out::println);
    }

    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
}
