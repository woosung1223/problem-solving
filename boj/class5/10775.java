import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    /*
        공항은 G개의 게이트가 존재 (1 ~ G 번호)
        P개의 비행기가 존재, 각 1번부터 gi번째 게이트 중 하나에 도킹해야 함

        최대 가장 많이 도킹할 수 있는 경우의 수는?

        이분 매칭 문제와 비슷하게 해결할 수 있을듯 (그리디)
        일단 비행기가 도착하면, 해당 비행기가 갈 수 있는 게이트 중 가장 번호가 높은 게이트에 할당

        그리고 그 다음 비행기가 왔을 때, 만약 도킹할 수 있는 게이트가 없다면
        최대 게이트 번호부터 하나씩 줄여가며 도킹 가능 여부 확인

        만약 1번 게이트까지 도킹할 수 있는 곳이 없다면 끝
        그 다음 비행기로는 진행할 수 없음

        단, O(G * P)로는 해결 불가능한데, 1억 회의 연산이 발생하기 때문
        10000개의 비행기가 10000개의 게이트 모두 가능한 경우.


    */

    static int[] plane;
    static int[] gatewayAvailable;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(reader.readLine());
        int P = Integer.parseInt(reader.readLine());

        plane = new int[G + 1]; // 게이트에 도킹된 비행기의 번호
        gatewayAvailable = new int[P + 1]; // 각 비행기마다 최대로 가능한 게이트웨이의 번호

        for (int i = 1; i <= P; i++) {
            // 1번부터 gi까지 중 하나의 게이트에 도킹
            int gi = Integer.parseInt(reader.readLine());
            gatewayAvailable[i] = gi;
        }

        int result = 0;
        int notYetUsed = G;

        for (int planeNumber = 1; planeNumber <= P; planeNumber++) {
            int available = gatewayAvailable[planeNumber];
            boolean docked = false;

            available = Math.min(notYetUsed, available);

            for (int gateway = available; gateway >= 1; gateway--) {
                if (plane[gateway] == 0) {
                    plane[gateway] = planeNumber;
                    docked = true;
                    result++;

                    if (notYetUsed == gateway) {
                        notYetUsed--;
                    }

                    break;
                }
            }

            if (!docked) {
                break;
            }
        }

        System.out.println(result);
    }
}
