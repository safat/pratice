package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B711 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println(1);
            System.exit(0);
        }

        long[][] items = new long[n][n];

        long magicSum = 0;
        long sum = 0;
        boolean magicSumFound = false;
        int mi = 0;
        int mj = 0;

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");

            boolean nonZeroRow = true;

            if (!magicSumFound) {
                magicSum = 0;
            }

            for (int j = 0; j < input.length; j++) {
                items[i][j] = Long.parseLong(input[j]);

                if (items[i][j] == 0) {
                    mi = i;
                    mj = j;
                }

                if (!magicSumFound) {

                    if (items[i][j] == 0) {
                        nonZeroRow = false;
                    } else {
                        magicSum += items[i][j];
                    }
                }
            }

            if (nonZeroRow) {
                magicSumFound = true;
            }
        }


        for (long item : items[mi]) {
            sum += item;
        }

        long delta = magicSum - sum;
        items[mi][mj] = delta;

        boolean isValidMs = delta > 0 && isValidMS(items, magicSum);

        System.out.println(isValidMs ? delta : -1);
    }

    private static boolean isValidMS(long[][] items, long magicSum) {
        long mainDiagonalSum = 0;
        long secondaryDiagonalSum = 0;

        for (int i = 0; i < items.length; i++) {
            long rowSum = 0;
            long colSum = 0;

            for (int j = 0; j < items.length; j++) {

                rowSum += items[i][j];
                colSum += items[j][i];

                if (i == j) {
                    mainDiagonalSum += items[i][j];
                }

                if (j == items.length - i - 1) {
                    secondaryDiagonalSum += items[i][j];
                }
            }

            if (rowSum != magicSum || colSum != magicSum) {
                return false;
            }
        }

        if (mainDiagonalSum != magicSum || secondaryDiagonalSum != magicSum) {
            return false;
        }

        return true;
    }
}
