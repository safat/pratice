package basic;

public class MaxHeap {

    public static void main(String[] args) {
        int input[] = {6, 4, 5, 3, 2, 0, 10, 12};

        heapSort(input);

        printData(input);

    }

    private static void heapSort(int[] data) {
        buildMaxHeap(data, data.length);

        for (int i = data.length; i > 1; i--) {
            swap(data, 0, i - 1);
            maxHeapify(data, 0, i - 1);
        }
    }

    private static void buildMaxHeap(int[] data, int n) {
        for (int i = n / 2; i >= 0; i--) {
            maxHeapify(data, i, n);
        }
    }

    private static void maxHeapify(int[] data, int i, int n) {
        int left = i * 2 + 1;
        int right = left + 1;

        int larger = i;

        if (left < n && data[i] < data[left]) {
            larger = left;
        }

        if (right < n && data[right] > data[larger]) {
            larger = right;
        }

        if (i != larger) {
            swap(data, i, larger);
            maxHeapify(data, larger, n);
        }
    }

    private static void swap(int[] data, int i, int larger) {
        int tmp = data[larger];
        data[larger] = data[i];
        data[i] = tmp;
    }

    private static void printData(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.println(i + " : " + data[i]);
        }

        System.out.println();
    }
}
