package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class B1105 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputMeta = br.readLine().split(" ");

        String input = br.readLine();

        int n = Integer.parseInt(inputMeta[0]);
        int k = Integer.parseInt(inputMeta[1]);

        Map<Character, Integer> segmentCount = new HashMap<>();
        int maxFreq = 0;

        for (int i = 0; i < input.length(); i++) {
            char start = input.charAt(i);
            int count = 1;

            while (i < input.length() - 1 && input.charAt(i + 1) == start) {
                count++;
                i++;
            }

            Integer freq = segmentCount.getOrDefault(start, 0);
            freq += (count / k);

            segmentCount.put(start, freq);

            maxFreq = Math.max(maxFreq, freq);
        }

        System.out.println(maxFreq);
    }
}
