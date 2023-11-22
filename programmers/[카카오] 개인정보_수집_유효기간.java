import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();

        for (String term : terms) {
            StringTokenizer stz = new StringTokenizer(term);

            String key = stz.nextToken();
            int val = Integer.parseInt(stz.nextToken());

            termMap.put(key, val);
        }

        List<Integer> results = new ArrayList<>();

        int count = 0;
        for (String privacy : privacies) {
            count++;

            StringTokenizer stz = new StringTokenizer(privacy);
            String date = stz.nextToken();
            String alphabet = stz.nextToken();

            int toAdd = termMap.get(alphabet);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            LocalDate targetDate = LocalDate.parse(date, formatter);
            LocalDate todayDate = LocalDate.parse(today, formatter);

            if (targetDate.plus(toAdd, ChronoUnit.MONTHS).isEqual(todayDate) ||
                    targetDate.plus(toAdd, ChronoUnit.MONTHS).isBefore(todayDate)) {
                results.add(count);
            }
        }

        return results.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}
