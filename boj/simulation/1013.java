import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    /*
        + => 반복 의미
        | => or 의미

        (100 | 11)+ => 11 혹은 100의 반복으로 이루어진 비트열

        (100+1+ | 01+)+ 패턴을 가려내라
        - 위 패턴의 의미
        부분집합 1: 10 이후 0 반복, 1 반복 (ex. 1001)
        부분집합 2: 0 이후 1 반복 (ex. 01, 011)

        그리고 위 두 부분집합을 0개 이상씩 조합하여 만든 경우의 수
        (1001, 01, 1001011 ...)

        어떻게 가려낼 수 있을까? 정규식 없이
        핵심은 '10'으로 시작하는 경우에만 100+1+이 될 수 있다는 것

        따라서 각 요소를 순회하면서, 10을 마주하는 순간 이후 0 순차 제거, 1 순차 제거

        그리고 01 패턴은 따로 분리해서 100+1+ 패턴으로 체킹이 되지 않은 애들을
        순회하면서 0이 나오면 1이 뒤에 있는지 순차적으로 체크하면 될듯

        100+1+ 패턴부터 먼저 체크하는 이유는, 01 패턴으로 먼저 체크하면
        '10'과 같이 유효하지 않은 패턴이 나와버리는 경우가 있기 때문

        다만 10011001 처럼 1이 연속되는 경우를 조심해야 함
        무조건 1이라고 해서 진행 X, 1이 여러개라면 마지막 1은 사용 가능하게 남겨둬야 함
    */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());

        while (T-- > 0) {
            String line = reader.readLine();

            boolean[] checked = new boolean[line.length()];
            boolean[] checkedButAvailable = new boolean[line.length()];

            // 100+1+ 패턴 체크
            for (int i = 1; i < line.length(); i++) {
                if ((checked[i - 1] || checked[i]) &&
                        (!checkedButAvailable[i - 1] || checked[i])) {
                    continue;
                }

                if (line.charAt(i - 1) == '1' && line.charAt(i) == '0') {
                    int start = i - 1;
                    int end = i + 1;
                    int zeroCount = 0;
                    int oneCount = 0;
                    // 0 체크

                    while (end < line.length() &&
                            line.charAt(end) == '0') {
                        end++;
                        zeroCount++;
                    }

                    while (end < line.length() &&
                            line.charAt(end) == '1') {
                        end++;
                        oneCount++;
                    }

                    if (zeroCount > 0 && oneCount > 0) {
                        // end - 1 까지 마킹
                        for (int j = start; j <= end - 1; j++) {
                            checked[j] = true;
                        }

                        if (oneCount > 1 && end != line.length()) {
                            checkedButAvailable[end - 1] = true;
                        }
                    }
                } else if (line.charAt(i - 1) == '0' && line.charAt(i) == '1') {
                    checked[i - 1] = true;
                    checked[i] = true;
                }
            }

            boolean result = true;
            for (boolean b : checked) {
                if (!b) {
                    result = false;
                    break;
                }
            }

            if (result) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}

