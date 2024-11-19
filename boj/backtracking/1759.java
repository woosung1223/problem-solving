import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    /*
        C개의 문자에서 L의 길이를 가진 암호 찾기
        최소 한 개의 모음(aeiou)과 최소 두 개의 자음으로 구성

        3 <= L <= C <= 15

        입력 크기를 보니 백트래킹이라고 생각 (완전탐색 + 유망성 판단)

        - 유망성을 판단하는 기준
        => 오름차순으로 정렬되어 있는지
     */

    static int L;
    static int C;

    static char[] arr;
    static boolean[] used;

    static List<String> result;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        used = new boolean[C];
        result = new ArrayList<>();
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        recur("");
        Collections.sort(result);
        for (String s : result) {
            System.out.println(s);
        }
    }

    static void recur(String current) {
        if (current.length() == L) {
            int aeiouCount = 0;
            for (int i = 0; i < current.length(); i++) {
                if (current.charAt(i) == 'a' || current.charAt(i) == 'e' ||
                        current.charAt(i) == 'i' || current.charAt(i) == 'o' ||
                        current.charAt(i) == 'u') {
                    aeiouCount++;
                }
            }

            if (aeiouCount >= 1 && aeiouCount <= L - 2) {
                result.add(current);
            }
            return;
        }

        for (int i = 0; i < C; i++) {
            if (!used[i] && promising(current + arr[i])) {
                used[i] = true;
                recur(current + arr[i]);
                used[i] = false;
            }
        }
    }

    static boolean promising(String target) {
        char before = 'a' - 1;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) <= before) {
                return false;
            }
            before = target.charAt(i);
        }
        return true;
    }
}
