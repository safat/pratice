package basic;


import java.util.List;
import java.util.ArrayList;

/**
 * @author muhossain
 * @since 2020-07-30
 */

public class KMP {
    public static void main(String[] args) {
        String text = "AAAABAAABA";
        String pattern = "AAABA";

        List<Integer> index = find(text, pattern);

        index.forEach(System.out::println);
    }

    private static List<Integer> find(String text, String pattern) {
        List<Integer> indexes = new ArrayList<>();

        int[] lps = constructLps(pattern);
        int j = 0;
        int i = 0;

        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
                i++;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }

            if (j == pattern.length()) {
                indexes.add(i - j);
                j = lps[j - 1];
            }
        }

        return indexes;
    }

    private static int[] constructLps(String pattern) {
        int[] lps = new int[pattern.length()];
        lps[0] = 0;
        int len = 0;

        for (int i = 1; i < pattern.length(); i++) {

            while (pattern.charAt(len) != pattern.charAt(i) && len > 0) {
                len = lps[len - 1];
            }

            if (pattern.charAt(len) == pattern.charAt(i)) {
                len++;
            }

            lps[i] = len;

        }

//        for (int i = 0; i < 10; i++) {
//            len++;
//
//            System.out.println("len: " + len);
//
//            for (int j = 0; j < lps.length; j++) {
//                System.out.print(lps[j] + ": ");
//            }
//
//            System.out.println();
//        }

        return lps;
    }
}

