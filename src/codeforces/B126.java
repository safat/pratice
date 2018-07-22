//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B126 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String password = findLongtestPass(input);

        System.out.println(password);
    }

    public static int[] prefixFunction(String s) {
        int prefixFunction[] = new int[s.length()];

        int j = 0;

        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = prefixFunction[j - 1];
            }

            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }

            prefixFunction[i] = j;
        }

        return prefixFunction;
    }

    private static String findLongtestPass(String input) {
        int n = input.length() - 1;
        int[] prefixFunc = prefixFunction(input);

        if (prefixFunc[n] == 0) {
            return "Just a legend";
        }

        for (int i = 1; i < input.length() - 1; i++) {
            if (prefixFunc[i] == prefixFunc[input.length() - 1]) {
                return input.substring(0, prefixFunc[i]);
            }
        }

        int prev = prefixFunc[n] - 1;

        if (prev > 0 && prefixFunc[prev] > 0) {
            return input.substring(0, prefixFunc[prev]);
        }

        return "Just a legend";
    }
}
