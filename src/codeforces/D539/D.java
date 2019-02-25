package codeforces.D539;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int n = countSplit(input);

        System.out.println(n == -1 ? "Impossible" : n);
    }

    private static int countSplit(String input) {
        if (allSame(input)) {
            return -1;
        }

        int splitCount = 0;
        int level = 1;

        while (true) {
            if (input.isEmpty()) {
                return splitCount;
            }

            if (input.length() % 2 == 0) {
                splitCount += level;
            } else {
                splitCount += level * 2;
            }

            String left = input.substring(0, input.length() / 2);
            String right = input.substring((input.length() + 1) / 2);

            if (left.length() > 3 && left.equals(new StringBuilder(left).reverse().toString())) {
                return splitCount;
            }

            if (level == 1 && allSame(left)) {
                return -1;
            }

            if (!left.equals(right)) {
                return splitCount;
            }

            input = left;

            level++;
        }
    }

    private static boolean allSame(String input) {
        char start = input.charAt(0);

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) != start) {
                return false;
            }
        }

        return true;
    }
}
