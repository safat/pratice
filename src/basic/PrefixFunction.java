package basic;

import java.util.stream.IntStream;

public class PrefixFunction {
    public static void main(String[] args) {
//        String input = "qwertyqwertyqwerty";
        String input = "aaa";
        int[] prefixFunction = optimizedPrefixFunction(input);

        IntStream.range(0, prefixFunction.length).forEach(x -> System.out.print(x + "\t"));
        System.out.println();
        IntStream.range(0, prefixFunction.length).forEach(x -> System.out.print(input.charAt(x) + "\t"));
        System.out.println();
        IntStream.of(prefixFunction).forEach(x -> System.out.print(x + "\t"));
    }

    public static int[] prefixFunction(String s) {
        int prefixFunction[] = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; j <= i; j++) {
                if (s.substring(0, j).equals(s.substring(i - j + 1, i + 1))) {
                    prefixFunction[i] = j;
                }
            }
        }

        return prefixFunction;
    }

    public static int[] optimizedPrefixFunction(String s) {
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
}
