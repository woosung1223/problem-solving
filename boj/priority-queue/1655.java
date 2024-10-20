import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    /*
        중간값 찾기
        - 짝수개라면 중간에 있는 두 수중 작은 수
        - 홀수개라면 중간에 있는 수

        N <= 100_000

        단순히 매번 들어오는 값을 삽입하고 정렬하면 해결 불가능

        - maxHeap + minHeap
        배열을 두 부분으로 나누어 해결(maxheap + minheap)

        maxheap + minheap은, 각각이 정렬된 상태를 유지하므로
        합쳤을 때도 정렬된 상태라는 것을 보장받을 수 있음

        들어오는 요소들을 maxheap, minheap에 번갈아 넣음
        개수를 맞추는 이유는 중간값을 찾게 하기 위함 (maxheap의 루트가 됨)
        Ex. [1,2] + [3,4]

        다만 '정렬된 상태'를 유지해야 하므로 방금 넣은 값이 규칙을 위반한다면 바꿈
        Ex. [1,2] [4,5] 에서 6이 들어가는 경우, [1,2,6] [4,5] 가 아니라 [1,2,4] [5,6]이 되야함
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y - x);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((x, y) -> x - y);

        for (int i = 0; i < N; i++) {
            int target = Integer.parseInt(reader.readLine());

            if (maxHeap.size() == minHeap.size()) {
                maxHeap.add(target);
            } else {
                minHeap.add(target);
            }

            if (!minHeap.isEmpty() && !maxHeap.isEmpty() &&
                    maxHeap.peek() > minHeap.peek()) {
                int temp1 = maxHeap.poll();
                int temp2 = minHeap.poll();
                maxHeap.add(temp2);
                minHeap.add(temp1);
            }

            System.out.println(maxHeap.peek());
        }
    }
}

