import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /*
        스도쿠 퍼즐을 풀기 위한 규칙
        1. 행에 1 ~ 9 중복 X
        2. 열에 1 ~ 9 중복 X
        3. 3 x 3 사각형에 1 ~ 9 중복 X

        구현 + 백트래킹

        0으로 표시된 곳에 1 ~ 9의 숫자를 대입해야 함
        다만 대입하다가 위 세가지 규칙 중 하나를 위반하는 경우,
        유망하지 않다고 판단하고 진행 X

        백트래킹은 재귀로 구현
    */

    static int[][] matrix;
    static List<Point> zeros;
    static boolean isOver;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        matrix = new int[9][9];

        zeros = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            String line = reader.readLine();

            for (int j = 0; j < 9; j++) {
                matrix[i][j] = line.charAt(j) - '0';

                if (matrix[i][j] == 0) {
                    zeros.add(new Point(i, j));
                }
            }
        }

        isOver = false;
        recur(0);

        for (int[] arr : matrix) {
            for (int i : arr) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    static void recur(int index) {
        if (isOver) {
            return;
        }

        if (index == zeros.size()) { // 모두 채운 경우
            isOver = true;
            return;
        }

        Point point = zeros.get(index);

        for (int i = 1; i <= 9; i++) {
            matrix[point.x][point.y] = i;

            // 유망성 체크
            if (isPromising(point.x, point.y)) {
                // 유망하다면 진행
                recur(index + 1);

                if (isOver) {
                    break;
                }
            }
            matrix[point.x][point.y] = 0;
        }
    }

    static boolean isPromising(int i, int j) {
        // 행 체크
        for (int z = 0; z < 9; z++) {
            if (z != j && matrix[i][z] == matrix[i][j]) {
                return false;
            }
        }

        // 열 체크
        for (int z = 0; z < 9; z++) {
            if (z != i && matrix[z][j] == matrix[i][j]) {
                return false;
            }
        }

        // 3 x 3 체크
        for (int z = (i / 3) * 3; z <= (i / 3) * 3 + 2; z++) {
            for (int x = (j / 3) * 3; x <= (j / 3) * 3 + 2; x++) {
                if (z != i && x != j && matrix[z][x] == matrix[i][j]) {
                    return false;
                }
            }
        }

        return true;
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
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
