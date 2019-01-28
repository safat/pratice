package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class B1102 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");

        int[] items = new int[input.length];
        int[] freq = new int[5000 + 1];
        int maxFreq = Integer.MIN_VALUE;

        for (int i = 0; i < input.length; i++) {
            int a = Integer.parseInt(input[i]);
            freq[a]++;

            items[i] = a;
            maxFreq = Math.max(maxFreq, freq[a]);
        }

        if (maxFreq > k || n < k) {
            System.out.println("NO");
        } else {
            StringBuilder output = new StringBuilder();
            Map<Integer, Set<Integer>> usedColor = new HashMap<>();

            int j = 0;
            for (int item : items) {
                if (!usedColor.containsKey(item)) {
                    usedColor.put(item, new HashSet<>());
                }

                while (usedColor.get(item).contains((j % k) + 1)) {
                    j++;
                }

                output.append((j % k) + 1).append(" ");
                usedColor.get(item).add((j % k) + 1);

                j++;
            }

            System.out.println("YES");
            System.out.println(output.substring(0, output.length() - 1));
        }

    }
}
