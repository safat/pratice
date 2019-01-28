package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.regex.Pattern;

public class A251 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int d = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        int[] points = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            points[i] = Integer.parseInt(input[i]);
        }

        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < points.length - 2; i++) {

            int index = binarySearch(points, points[i], i + 1, points.length, d,
                    (start, end, distance) -> Math.abs(end - start) <= distance);

            long offset = index - i;
            result = result.add(BigInteger.valueOf(((offset - 1) * (offset - 2)) / 2));
        }

        System.out.println(result);
    }

    private static int binarySearch(int[] a, int pivot, int fromIndex, int toIndex, int key, Distance distance) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (distance.apply(midVal, pivot, key))
                low = mid + 1;
            else {
                high = mid - 1;
            }
        }

        return low;
    }

    @FunctionalInterface
    interface Distance {
        boolean apply(int start, int end, int d);
    }
}
