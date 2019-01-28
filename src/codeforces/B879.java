package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B879 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        long k = Long.parseLong(input[1]);

        input = br.readLine().split(" ");
        int[] strength = new int[n];

        int max = 0;
        int[] cumulativeMax = new int[n];


        for (int i = 0; i < input.length; i++) {
            strength[i] = Integer.parseInt(input[i]);

            if (strength[i] > max) {
                max = strength[i];
            }

            cumulativeMax[i] = max;
        }

        cumulativeMax[0] = strength[0];

        int winIndex = -1;


        for (int i = 0; i < n; i++) {
            long lastMatchIndex = i + k;

            if (lastMatchIndex < n && (cumulativeMax[(int) lastMatchIndex] == strength[i] ||
                    (i > 0 && cumulativeMax[(int) lastMatchIndex - 1] == strength[i] && strength[i] > strength[i - 1]))) {
                winIndex = i;
                break;
            }
        }

        System.out.println(winIndex == -1 ? max : strength[winIndex]);
    }
}
