package basic.backtracking;

/**
 * @author muhossain
 * @since 2019-06-10
 */

public class TugOfWar {
    public static void main(String[] args) {
        int items[] = {23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4};

        int total = 0;

        for (int item : items) {
            total += item;
        }

        System.out.println(minDiff(total, items));
    }

    public static int minDiff(int total, int[] items) {
        return minDiffUtil(0, 0, 0, total, items);
    }

    public static int minDiffUtil(int idx, int itemTaken, int tmpSum, int totalSum, int[] items) {
        if (idx >= items.length) {
            return Integer.MAX_VALUE;
        }

        if (itemTaken == items.length / 2) {
            return Math.abs(totalSum - 2 * tmpSum);
        }

        // take current item or not take current item is the question
        return Math.min(minDiffUtil(idx + 1, itemTaken + 1, tmpSum + items[idx], totalSum, items),
                minDiffUtil(idx + 1, itemTaken, tmpSum, totalSum, items));
    }
}
