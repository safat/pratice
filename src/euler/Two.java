package euler;

/**
 * @author muhossain
 * @since 2020-06-20
 */

public class Two {
    public static void main(String[] args) {

        long biggestFiv = 4000000;

        long current = 1;
        long prev = 1;
        long sum = 0;

        while (current < biggestFiv) {
            if (current % 2 == 0) {
                sum += current;
            }

            long tmpCurrent = current;
            current = current + prev;
            prev = tmpCurrent;

        }

        System.out.println(sum);
    }

}
