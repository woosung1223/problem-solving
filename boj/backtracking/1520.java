import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    /*
        N, M <= 500

        25,000 개의 셀이 있을 수 있고,
        이전 셀보다 낮은 셀만 선택할 수 있기 때문에
        최악의 경우 25,000 개의 셀을 모두 탐색하는 경우임 O(4 * N * M)

        이미 방문했던 경로의 셀을 타기 시작하면, 그 뒤의 경로는 달라질 수 없음 (최적화 가능한 부분)

        경로 길이 배열을 유지하여, 해당 경로를 타려는 경우 빠르게 계산
        그렇다면 특정 셀부터 목적지까지의 경로는 몇개인지 어떻게 알까?

        BFS는 경로를 따로 저장해야 하니까, DFS로 간단하게 해결하자
        함수 호출을 종료하면서 경로 개수 반환

        처음엔 완탐인줄 알았는데 백트래킹 문제인듯
    */

    static int[][] matrix;
    static int[][] way;
    static boolean[][] visited;
    static int M;
    static int N;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        matrix = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        way = new int[M][N];
        visited = new boolean[M][N];

        int result = recur(0, 0);
        System.out.println(result);
    }

    static int recur(int x, int y) {
        if (x == M - 1 && y == N - 1) {
            return 1;
        } else if (visited[x][y]) {
            return way[x][y];
        }

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX < 0 || newX >= M || newY < 0 || newY >= N) {
                continue;
            } else if (matrix[newX][newY] >= matrix[x][y]) {
                continue;
            }
            // 새로운 경로
            way[x][y] += recur(newX, newY);
        }

        return way[x][y];
    }
}
