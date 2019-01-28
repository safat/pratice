//package codeforces.D535;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();

        String input = br.readLine();
        int cont = 1;
        int result = 0;
        StringBuilder output = new StringBuilder();
        output.append(input.charAt(0));

        for (int i = 1; i < input.length(); i++) {
            output.append(input.charAt(i));

            if (input.charAt(i) == input.charAt(i - 1)) {
                cont++;
            } else {
                if (cont > 1) {
                    for (int start = output.length() - cont; start < output.length() - 1; start += 2) {
                        Character replacement = getReplacingChar(start - 1 >= 0 ? output.charAt(start - 1) : null, (output.charAt(start + 1)));
                        output.setCharAt(start, replacement);
                    }
                }

                result += (cont / 2);

                cont = 1;

            }
        }

        if (cont > 1) {
            for (int start = output.length() - cont + 1; start < output.length(); start += 2) {
                Character replacement = getReplacingChar(start - 1 >= 0 ? output.charAt(start - 1) : null, (start + 1) > output.length() - 1 ? null : output.charAt(start + 1));
                output.setCharAt(start, replacement);
            }
        }

        result += (cont / 2);

        System.out.println(result);
        System.out.println(output);
    }

    private static Character getReplacingChar(Character f, Character s) {
        Set<Character> characterSet = new HashSet<>(Arrays.asList('R', 'G', 'B'));

        characterSet.remove(f);
        characterSet.remove(s);

        return characterSet.iterator().next();
    }
}
