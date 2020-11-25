package euler;

/**
 * @author muhossain
 * @since 2020-06-20
 */

public class Three {
    public static void main(String[] args) {
        long val = 600851475143L;

        System.out.println(largestPrimeFactor(val));
    }


    private static int largestPrimeFactor(long number) {
        int i;
        long copyOfInput = number;

        for (i = 2; i <= copyOfInput; i++) {
            if (copyOfInput % i == 0) {
                copyOfInput /= i;
                i--;
            }
        }

        return i;
    }
}
