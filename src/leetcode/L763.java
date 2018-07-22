package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L763 {
    public static void main(String[] args) {
        List<Integer> result = new L763().partitionLabels("ababcbacadefegdehijhklije");

        System.out.println(result);
    }

    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastIndexMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        int prevCursor = 0;

        for (int i = 0; i < s.length(); i++) {
            lastIndexMap.put(s.charAt(i), i);
        }

        for (int i = 0; i < s.length(); i++) {
            int lastIndex = lastIndexMap.get(s.charAt(i));

            for (int j = i; j <= lastIndex && j < s.length(); j++) {
                if (lastIndexMap.get(s.charAt(j)) > lastIndex) {
                    lastIndex = lastIndexMap.get(s.charAt(j));
                }
            }

            i = lastIndex;

            result.add(lastIndex + 1 - prevCursor);

            prevCursor = lastIndex + 1;
        }

        return result;
    }
}
