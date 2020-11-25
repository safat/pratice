package basic;

/**
 * @author muhossain
 * @since 2020-07-15
 */

public class CountStair {

    public static void main(String[] args) {

        System.out.println(count(4));

    }

    public static int count(int n) {
        if (n < 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        return count(n - 1) + count(n - 2);
    }
}
