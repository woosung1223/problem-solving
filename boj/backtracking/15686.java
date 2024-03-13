import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static List<Point> chickens;
    static List<List<Point>> chickenCases;

    static Stack<Integer> combinations;

    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken()); // 폐업시키지 않을 치킨집의 개수

        List<Point> houses = new ArrayList<>();
        chickens = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(reader.readLine());

            for (int j = 0; j < N; j++) {
                int number = Integer.parseInt(stz.nextToken());

                if (number == 1) {
                    houses.add(new Point(i, j));
                } else if (number == 2) {
                    chickens.add(new Point(i, j));
                }
            }
        }

        chickenCases = new ArrayList<>();
        combinations = new Stack<>();

        // 치킨집 조합의 수
        recur(0, 0);

        int min = Integer.MAX_VALUE;

        for (List<Point> points : chickenCases) {
            int result = 0;

            for (Point house : houses) {
                List<Integer> distances = new ArrayList<>();

                for (Point chicken : points) {
                    distances.add(Math.abs(house.x - chicken.x) +
                            Math.abs(house.y - chicken.y));
                }

                int closest = Collections.min(distances);
                result += closest;
            }
            min = Math.min(result, min);
        }

        System.out.println(min);
    }

    static void recur(int index, int depth) {
        if (index >= chickens.size()) {
            return;
        } else if (depth == M) {
            List<Point> points = new ArrayList<>();
            for (int each : combinations) {
                points.add(chickens.get(each));
            }
            chickenCases.add(points);
            return;
        }

        for (int i = index; i < 13; i++) {
            if (!combinations.contains(i)) {
                combinations.push(i);
                recur(i, depth + 1);
                combinations.pop();
            }
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }
}
