package leetcode;

import java.util.*;

public class L890 {

    public static void main(String[] args) {
        String[] words = {"mee", "aqq", "dddd", "dede", "dkd"};
        String pattern = "abb";

        List<String> output = new L890().findAndReplacePattern(words, pattern);

        System.out.println(output);
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> results = new ArrayList<>();

        for (String word : words) {

            if (word.length() != pattern.length()) {
                continue;
            }

            if (isMatched(word, pattern)) {
                results.add(word);
            }
        }

        return results;
    }

    private boolean isMatched(String word, String pattern) {
        Map<Character, Integer> wordPositionMap = new HashMap<>();
        Map<Character, Integer> patternPositionMap = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            Integer wPosition = wordPositionMap.get(word.charAt(i));
            Integer pPosition = patternPositionMap.get(pattern.charAt(i));

            if (wPosition != pPosition) {
                return false;
            } else {
                wordPositionMap.put(word.charAt(i), i + 1);
                patternPositionMap.put(pattern.charAt(i), i + 1);
            }
        }

        return true;
    }
}
