import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        int removed;
        while (true) {
            removed = queue.remove();
            if (queue.isEmpty()) {
                System.out.println(removed);
                break;
            }
            toAdd = queue.remove();
            queue.add(toAdd);
        }
    }
}
