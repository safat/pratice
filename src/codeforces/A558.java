package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A558 {
    static class Pair implements Comparable<Pair> {
        int value, index;

        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Pair o1) {
            return this.index - o1.index;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "value=" + value +
                    ", index=" + index +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Pair pair = new Pair(1, 2);
        Method method = pair.getClass().getDeclaredMethods()[0]; // do filtering by the name you have

        int n = Integer.parseInt(br.readLine());

        List<Pair> positive = new ArrayList<>();
        List<Pair> negative = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] inputMeta = br.readLine().split(" ");
            int index = Integer.parseInt(inputMeta[0]);
            int appleCount = Integer.parseInt(inputMeta[1]);

            if (index > 0) {
                positive.add(new Pair(appleCount, index));
            } else {
                negative.add(new Pair(appleCount, -1 * index));
            }
        }

        Collections.sort(positive);
        Collections.sort(negative);

        System.out.println(findMaxSum(positive, negative));

    }

    private static int findMaxSum(List<Pair> positive, List<Pair> negative) {
        List<Pair> small, large;

        if (positive.size() > negative.size()) {
            large = positive;
            small = negative;
        } else {
            large = negative;
            small = positive;
        }

        int smallSum = small.stream().map(x -> x.value).reduce(0, (x, y) -> (x + y));
        int largeSum = large.stream().limit(small.size()).map(x -> x.value).reduce(0, (x, y) -> (x + y));

        return smallSum + largeSum + (large.size() != small.size() ? large.get(small.size()).value : 0);
    }
}
