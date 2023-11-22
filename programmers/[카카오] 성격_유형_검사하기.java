import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<String, Integer> map = new HashMap<>();


        for (int i = 0; i < survey.length; i++) {
            int choice = choices[i];
            String answer = survey[i];

            if (choice > 4) {
                int point =  choice - 4;
                map.put(String.valueOf(answer.charAt(1)),
                        map.getOrDefault(String.valueOf(answer.charAt(1)), 0) + point);

            } else if (choice < 4) {
                int point = 4 - choice;
                map.put(String.valueOf(answer.charAt(0)),
                        map.getOrDefault(String.valueOf(answer.charAt(0)), 0) + point);
            }
        }

        String[][] iter = {{"R", "T"}, {"C", "F"}, {"J", "M"}, {"A", "N"}};
        String result = "";

        for (String[] each : iter) {
            String left = each[0];
            String right = each[1];

            int leftCount = map.getOrDefault(left, 0);
            int rightCount = map.getOrDefault(right, 0);

            if (leftCount > rightCount) {
                result += left;
            } else if (leftCount == rightCount) {

                if (left.compareTo(right) > 0) {
                    result += right;
                } else {
                    result += left;
                }

            } else {
                result += right;
            }
        }

        return result;
    }
}
