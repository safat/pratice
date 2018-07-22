package basic;

import java.util.stream.IntStream;

public class MergeSort {

    public static void main(String[] args) {
        int[] numbers = {10, 20, 5, 12, 1};

        int[] sortedValues = mergeSort(numbers);

        System.out.println(toString(sortedValues));
    }

    private static int[] mergeSort(int[] numbers) {
        if (numbers.length == 1) {
            return numbers;
        }

        int leftArrayLen = numbers.length / 2;
        int rightArrayLen = numbers.length - leftArrayLen;

        int leftArray[] = new int[leftArrayLen];
        int rightArray[] = new int[rightArrayLen];

        System.arraycopy(numbers, 0, leftArray, 0, leftArrayLen);
        System.arraycopy(numbers, leftArrayLen, rightArray, 0, rightArrayLen);

        return merge(mergeSort(leftArray), mergeSort(rightArray));
    }

    private static int[] merge(int[] left, int[] right) {
        System.out.println("merging... " + toString(left) + " & " + toString(right));

        int[] merged = new int[left.length + right.length];

        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }

        while (i < left.length) {
            merged[k++] = left[i++];
        }

        while (j < right.length) {
            merged[k++] = right[j++];
        }

        return merged;
    }

    private static String toString(int[] values) {
        return IntStream.of(values).mapToObj(String::valueOf).reduce((a, b) -> a.concat(", ").concat(b)).get();
    }
}

