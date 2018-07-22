package basic;

public class BinarySearch {

    public static int binarySearch(int[] values, int n) {

        return binarySearch(values, 0, values.length, n);
    }

    private static int binarySearch(int[] values, int start, int end, int n) {
        if (start > end) {
            return -1;
        }

        int mid = start + (end - start) / 2;

        if (values[mid] == n) {
            return mid;
        }

        return n > values[mid] ?
                binarySearch(values, mid + 1, end, n)
                : binarySearch(values, start, mid - 1, n);
    }
}
