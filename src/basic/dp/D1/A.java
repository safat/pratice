package basic.dp.D1;

/**
 * @author muhossain
 * @since 2019-08-18
 */
// Given a number N, find number of different ways to write n as the sum of 1, 3, 4
public class A {
    private static int N = 5;

    public static void main(String[] args) {
        int D[] = new int[N + 1];

        D[0] = 1;
        D[1] = 1;
        D[2] = 1; // 1 + 1
        D[3] = 2; // 1 + 1 + 1, 3

        for (int i = 4; i <= N; i++) {
            D[i] = D[i - 1] + D[i - 3] + D[i - 4];
        }

        System.out.println(D[N]);
    }
}
