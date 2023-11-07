import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], true);
        }

        int half = nums.length / 2;
        int keySetCount = map.keySet().size();

        if (keySetCount > half) {
            return half;
        }
        return keySetCount;
    }
}
