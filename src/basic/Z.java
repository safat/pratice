package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Z {
    public static int[] calculateZ(char[] input) {
        int[] Z = new int[input.length];
        int left = 0, right = 0;

        for (int k = 1; k < input.length; k++) {

            if (k > right) {
                left = right = k;

                while (right < input.length && input[right] == input[right - left]) {
                    right++;
                }

                Z[k] = right - left;
                right--;
            } else {
                //within the box
                int k1 = k - left;

                if (Z[k1] + k - 1 < right) {
                    Z[k] = Z[k1];
                } else {
                    left = k;

                    while (right < input.length && input[right] == input[right - left]) {
                        right++;
                    }

                    Z[k] = right - left;
                    right--;
                }
            }
        }

        return Z;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String pattern = br.readLine();
        String string = br.readLine();

        String zString = pattern + "$" + string;
//        int[] z = calculateZ(zString.toCharArray());
        int[] z = calculateZ("abxabxcabxabxay".toCharArray());

        int matchCount = 0;

        for (int aZ : z) {
            if (aZ == pattern.length()) {
                matchCount++;
            }
        }

        System.out.println(matchCount);
    }
}
