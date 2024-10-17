import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*
        a가 1이면 b번째 수를 c로 바꿈
        a가 2이면 b번째 수부터 c번째 수까지의 합을 구하여 출력

        N <= 1_000_000

        구간합 배열을 한 번 구하면, 요소가 변경됨에 따라 그 뒷 요소는 모두 영향을 받음
        따라서 최악의 경우 가장 처음의 요소가 지속적으로 바뀐다면,
        매번 새로운 구간합 배열을 만드는 것과 같음

        최대 M(<= 10_000)번의 변경이 일어나니, 시간 초과

        솔루션 => 구간합을 효율적으로 갱신하는 방향

        세그먼트 트리
        값 수정 / 구간합 구하는 과정을 O(logN)으로 해결 가능
    */

    static long[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(reader.readLine());
        }

        tree = new long[arr.length * 4];
        init(1, 0, arr.length - 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                b--;
                update(1, 0, arr.length - 1, b, c);
                arr[b] = c;
            } else if (a == 2) {
                b--;
                c--;
                long result = find(1, 0, arr.length - 1, b, (int) c);
                System.out.println(result);
            }
        }
    }

    static long init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return tree[node];
        }
        int mid = (start + end) / 2;
        tree[node] = init(node * 2, start, mid) +
                init(node * 2 + 1, mid + 1, end);
        return tree[node];
    }

    static void update(int node, int start, int end, int index, long target) {
        // index번째를 target으로
        if (index < start || index > end) {
            return;
        }
        // 만약 구간 내라면
        tree[node] -= arr[index];
        tree[node] += target;

        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        update(node * 2, start, mid, index, target);
        update(node * 2 + 1, mid + 1, end, index, target);
    }

    static long find(int node, int start, int end, int targetStart, int targetEnd) {
        if (targetStart > end || targetEnd < start) {
            return 0;
        } else if (targetStart <= start && end <= targetEnd) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return find(node * 2, start, mid, targetStart, targetEnd) +
                find(node * 2 + 1, mid + 1, end, targetStart, targetEnd);
    }
}
