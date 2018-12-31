package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class C701 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();
        String input = br.readLine();

        int minWindow = findMinWindowOptimized(input);

        System.out.println(minWindow);
    }

    private static int findMinWindowOptimized(String input) {
        Set<Character> characterSet = new HashSet<>();

        for (char ch : input.toCharArray()) {
            characterSet.add(ch);
        }

        int uniqueCount = characterSet.size();

        Map<Character, Integer> frequency = new HashMap<>();
        int start = 0;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < input.length(); i++) {
            frequency.putIfAbsent(input.charAt(i), 0);
            frequency.computeIfPresent(input.charAt(i), (k, v) -> v + 1);

            if (frequency.size() == uniqueCount) {
                while (frequency.get(input.charAt(start)) > 1) {
                    frequency.computeIfPresent(input.charAt(start), (k, v) -> v - 1);
                    start++;
                }

                ans = Math.min(ans, i - start + 1);
            }

            if (ans == uniqueCount) {
                return ans;
            }
        }

        return ans;
    }
}
