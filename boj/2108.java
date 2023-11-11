import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            int number = Integer.parseInt(reader.readLine());
            arr[i] = number;
        }

        int avg = 0;
        for (int i = 0; i < size; i++) {
            avg += arr[i];
        }
        System.out.println(Math.round((double)avg / size));

        Arrays.sort(arr);
        System.out.println(arr[size / 2]);

        int frequentMax = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

            if (map.get(arr[i]) > frequentMax) {
                frequentMax = map.get(arr[i]);
            }
        }

        List<Integer> frequent = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (map.get(arr[i]) == frequentMax) {
                frequent.add(arr[i]);
            }
        }
        Collections.sort(frequent);
        frequent = frequent.stream().distinct()
                .collect(Collectors.toList());

        if (frequent.size() > 1) {
            System.out.println(frequent.get(1));
        } else {
            System.out.println(frequent.get(0));
        }

        System.out.println(arr[size - 1] - arr[0]);
    }
}
