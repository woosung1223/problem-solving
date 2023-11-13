import java.io.*;

public class Main {

    public static int[] zeroArr;
    public static int[] oneArr;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        for (int i = 0; i < size; i++) {
            int number = Integer.parseInt(reader.readLine());
            flushArr(number);
            fibonacci(zeroArr, number);
            fibonacci(oneArr, number);

            System.out.println(zeroArr[number] + " " + oneArr[number]);
        }
    }

    public static void flushArr(int number) {
        zeroArr = new int[number + 2];
        zeroArr[0] = 1;
        zeroArr[1] = 0;

        for (int i = 2; i < number + 2; i++) {
            zeroArr[i] = -1;
        }

        oneArr = new int[number + 2];
        oneArr[0] = 0;
        oneArr[1] = 1;

        for (int i = 2; i < number + 2; i++) {
            oneArr[i] = -1;
        }
    }

    public static int fibonacci(int[] arr, int number) {
        if (number == 0) {
            return 0;
        } else if (number == 1) {
            return 1;
        } else {
            int result;
            if (arr[number - 1] != -1 && arr[number - 2] != -1) {
                result = arr[number - 1] + arr[number - 2];
            } else if (arr[number - 1] != -1) {
                result = arr[number - 1] + fibonacci(arr, number - 2);
            } else if (arr[number - 2] != -1) {
                result = fibonacci(arr, number - 1) + arr[number - 2];
            } else {
                result = fibonacci(arr, number - 1) + fibonacci(arr, number - 2);
            }
            arr[number] = result;
            return result;
        }
    }
}
