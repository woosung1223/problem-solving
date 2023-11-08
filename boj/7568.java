import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String line = reader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(line);

            int weight = Integer.parseInt(stringTokenizer.nextToken());
            int height = Integer.parseInt(stringTokenizer.nextToken());

            persons.add(new Person(weight, height));
        }

        for (int i = 0; i < size; i++) {
            int count = 1;

            for (int j = 0; j < size; j++) {
                if (i != j &&
                        persons.get(i).getWeight() < persons.get(j).getWeight() &&
                        persons.get(i).getHeight() < persons.get(j).getHeight()) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }

    static class Person {

        private int weight;
        private int height;

        public Person(int weight, int height) {
            this.weight = weight;
            this.height = height;
        }

        public int getWeight() {
            return weight;
        }

        public int getHeight() {
            return height;
        }
    }
}
