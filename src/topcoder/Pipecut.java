package topcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pipecut {

    public double probability(int[] items, int L) {
        Arrays.sort(items);
        List<int[]> ncr = ncr(items, 2);

        double total = ncr.size();
        double longEnough = 0;

        for (int[] combinations : ncr) {
            if (combinations[0] > L || combinations[1] - combinations[0] > L || 100 - combinations[1] > L) {
                longEnough++;
            }
        }

        return longEnough / total;
    }

    public List<int[]> ncr(int[] items, int r) {
        List<int[]> results = new ArrayList<>();
        int right[] = {};

        ncrUtil(items, right, results, r);

        return results;
    }

    public void ncrUtil(int[] left, int[] right, List<int[]> results, int r) {
        if (right.length == r) {
            results.add(right);
            return;
        }

        for (int i = 0; i < left.length; i++) {
            int newRight[] = new int[right.length + 1];

            System.arraycopy(right, 0, newRight, 0, right.length);
            newRight[right.length] = left[i];

            int[] newLeft = new int[left.length - i - 1];
            System.arraycopy(left, i + 1, newLeft, 0, left.length - i - 1);

            ncrUtil(newLeft, newRight, results, r);
        }
    }
}
