//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class B1093 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        List<String> inputs = new ArrayList<>();

        while (t-- > 0) {
            inputs.add(br.readLine());
        }

        StringBuilder output = new StringBuilder();

        for (String input : inputs) {
            output.append(goodStr(input)).append("\n");
        }

        System.out.print(output);
    }

    private static String goodStr(String input) {
        char[] inputChars = input.toCharArray();

        int i = (input.length() / 2) - 1, j = (int) Math.ceil(input.length() / 2.0);

        while (j < inputChars.length) {
            if (inputChars[i--] != inputChars[j++]) {
                return input;
            }
        }

        for (int k = 1; k < inputChars.length; k++) {
            if (inputChars[k] != inputChars[k - 1]) {

                Arrays.sort(inputChars);
                return String.valueOf(inputChars);
            }
        }

        return "-1";
    }
}
