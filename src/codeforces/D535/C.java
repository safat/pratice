package codeforces.D535;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] combinations = {"RGB", "RBG", "GRB", "GBR", "BRG", "BGR"};

        br.readLine();
        String input = br.readLine();

        int minRecolor = Integer.MAX_VALUE;
        StringBuilder result = new StringBuilder();

        for (String combination : combinations) {
            int reColor = 0;
            int index = 0;
            StringBuilder output = new StringBuilder();

            for (char ch : input.toCharArray()) {
                if (combination.charAt(index % 3) != ch) {
                    reColor++;
                }

                output.append(combination.charAt(index % 3));

                index++;
            }

            if (reColor < minRecolor) {
                minRecolor = reColor;
                result = output;
            }
        }

        System.out.println(minRecolor);
        System.out.println(result);
    }
}
