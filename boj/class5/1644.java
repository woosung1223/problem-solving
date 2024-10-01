import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /*
        연속된 소수의 합으로 나타낼 수 있는 경우의 수
        소수는 한번씩만 사용할 수 있음

        N <= 4_000_000

        우선 해당 N 이전까지의 소수를 모두 구함 (에라토스테네스의 체)
        이후 투 포인터 사용

        투 포인터를 통해 N 값을 가지는 케이스를 찾고,
        해당 과정에서 만약 N보다 커지는 경우가 된다면 좌측 포인터 이동
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        List<Integer> primeNumbers = findPrimeNumbers(N);
        int left = 0;
        int right = 0;

        int result = 0;
        int sum = 0;

        while (left <= primeNumbers.size() - 1) {
            sum += primeNumbers.get(right);

            if (sum == N) {
                result++;
                left++;
                right = left;
                sum = 0;
            } else if (sum > N || right == primeNumbers.size() - 1) {
                left++;
                right = left;
                sum = 0;
            } else {
                right++;
            }
        }

        System.out.println(result);
    }

    static List<Integer> findPrimeNumbers(int N) {
        // 에라토스테네스의 체
        // 어떤 수 X가 소수라면, X의 배수는 소수가 아니다
        boolean[] isPrime = new boolean[N + 1];

        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (!isPrime[i]) {
                continue;
            }

            int count = 0;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    count++;
                }
            }

            if (count == 0) {
                int number = i * 2;
                while (number <= N) {
                    isPrime[number] = false;
                    number += i;
                }
            } else {
                isPrime[i] = false;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                result.add(i);
            }
        }

        return result;
    }
}
