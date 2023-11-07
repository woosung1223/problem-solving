import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> items = new HashMap<>();
        for (String key : completion) {
            int count = items.getOrDefault(key, 0);
            items.put(key, count + 1);
        }

        for (String key : participant) {
            if (items.containsKey(key)) {
                int value = items.get(key) - 1;
                items.put(key, value);
            }

            if (items.get(key) == null || items.get(key) < 0) {
                return key;
            }
        }
        return null;
    }
}
