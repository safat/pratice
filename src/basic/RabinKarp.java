package basic;

/**
 * @author muhossain
 * @since 2020-10-25
 */
//https://cp-algorithms.com/string/string-hashing.html
//https://cp-algorithms.com/string/rabin-karp.html
public class RabinKarp {

    private static int BASE = 31;
    private static long MOD = (int) 1e9 + 7;

    private static int indexOf(String pattern, String text) {
        long[] pow = new long[text.length() + 1];
        pow[0] = 1;

        for (int i = 1; i < pow.length; i++) {
            pow[i] = (pow[i - 1] * BASE) % MOD;
        }

        long[] h = new long[text.length() + 1];

        for (int i = 0; i < h.length - 1; i++) {
            h[i + 1] = (h[i] + (text.charAt(i) - 'a' + 1) * pow[i]) % MOD;
        }

        long patternHash = 0;

        for (int i = 0; i < pattern.length(); i++) {
            patternHash = (patternHash + (pattern.charAt(i) - 'a' + 1) * pow[i]) % MOD;
        }

        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            long currentHash = (h[i + pattern.length()] + MOD - h[i]) % MOD;

            if (currentHash == (patternHash * pow[i]) % MOD) {
                // potential match

                if (text.substring(i, Math.min(text.length(), i + pattern.length() + 1)).equals(pattern)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(indexOf("abc", "babdcdasdasdabc"));
    }
}
