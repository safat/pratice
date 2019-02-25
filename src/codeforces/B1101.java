package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1101 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        // [:|:]
        int firstBracket = -1, firstColon = -1, secondColon = -1, lastBracket = -1;

        firstBracket = input.indexOf('[');

        if (firstBracket >= 0) {
            firstColon = input.indexOf(':', firstBracket + 1);
        }

        if (firstColon >= 1) {
            lastBracket = input.lastIndexOf(']');
        }

        if (lastBracket >= 3) {
            secondColon = input.lastIndexOf(':', lastBracket - 1);
        }

        int barCount = -1;

        if (firstBracket != -1 && firstColon != -1 && lastBracket != -1 && secondColon != -1 && lastBracket > secondColon && secondColon > firstColon) {
            barCount = 0;

            for (int i = firstColon; i < secondColon; i++) {
                if (input.charAt(i) == '|') {
                    barCount++;
                }
            }
        }

        System.out.println(barCount >= 0 ? barCount + 4 : -1);
    }
}
