import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            int age = Integer.parseInt(stringTokenizer.nextToken());
            String name = stringTokenizer.nextToken();

            persons.add(new Person(age, name));
        }

        persons.sort(Comparator.comparingInt(x -> x.age));

        for (Person person : persons) {
            System.out.println(person);
        }
    }

    static class Person {

        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.valueOf(age) + " " + name;
        }
    }
}
