import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /*
        최소 개수의 SAFE ZONE
        지도의 모든 위치에서 피리를 붐에 따라 접근할 수 있는 칸을 찾는 것

        1 <= N <= 1000

        - 탐색 응용 문제
        SAFE ZONE은 그룹 당 하나면 됨

        A -> B -> C -> ... SAFE-ZONE
        A에서 SAFE-ZONE에 도달할 수 있으면, B, C에서도 도달할 수 있음

        다만 문제는 시작점 A가 항상 가장 먼 경로라는 보장이 없다는 것
        몇회차에 방문했냐를 지속적으로 저장하고, 해당 회차가 아닌 이전 회차에 방문했다면
        그룹 카운팅 X
     */

    static char[][] matrix;
    static Integer[][] degree;
    static int currentDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        matrix = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        degree = new Integer[N][M]; // 차수를 저장하는 배열
        currentDegree = 0;
        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 탐색
                currentDegree++;
                Point start = new Point(i, j);
                if (recursiveSearch(start)) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    static boolean recursiveSearch(Point point) {
        if (degree[point.x][point.y] != null &&
                degree[point.x][point.y] == currentDegree) {
            // 사이클이 있는 경우는 정상 카운팅
            return true;
        } else if (degree[point.x][point.y] != null &&
                degree[point.x][point.y] < currentDegree) {
            // 이전 차수에 방문했던 경우는 배제
            return false;
        }

        degree[point.x][point.y] = currentDegree;
        char command = matrix[point.x][point.y];

        if (command == 'L') {
            return recursiveSearch(new Point(point.x, point.y - 1));
        } else if (command == 'R') {
            return recursiveSearch(new Point(point.x, point.y + 1));
        } else if (command == 'U') {
            return recursiveSearch(new Point(point.x - 1, point.y));
        } else {
            return recursiveSearch(new Point(point.x + 1, point.y));
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
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
