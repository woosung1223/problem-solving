import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            StringTokenizer stz = new StringTokenizer(reader.readLine());

            Student student = new Student(stz.nextToken(),
                    Integer.parseInt(stz.nextToken()),
                    Integer.parseInt(stz.nextToken()),
                    Integer.parseInt(stz.nextToken()));

            students.add(student);
        }

        students.sort((x, y) -> {
            if (x.getKorean() > y.getKorean()) {
                return -1;
            } else if (x.getKorean() < y.getKorean()) {
                return 1;
            } else {
                if (x.getEnglish() > y.getEnglish()) {
                    return 1;
                } else if (x.getEnglish() < y.getEnglish()) {
                    return -1;
                } else {
                    if (x.getMath() > y.getMath()) {
                        return -1;
                    } else if (x.getMath() < y.getMath()) {
                        return 1;
                    } else {
                        return x.getName().compareTo(y.getName());
                    }
                }
            }
        });

        for (Student student : students) {
            System.out.println(student.getName());
        }
    }

    static class Student {

        String name;
        int korean;
        int english;
        int math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        public String getName() {
            return name;
        }

        public int getKorean() {
            return korean;
        }

        public int getEnglish() {
            return english;
        }

        public int getMath() {
            return math;
        }
    }
}
