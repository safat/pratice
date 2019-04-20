package basic.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RodCutting {
    public static void main(String[] args) {
        Map<Integer, Integer> priceMap = new HashMap<>();

        priceMap.put(1, 4);
        priceMap.put(2, 5);
        priceMap.put(3, 8);
        priceMap.put(4, 9);
        priceMap.put(5, 10);
        priceMap.put(6, 17);
        priceMap.put(7, 50);
        priceMap.put(8, 20);

        int n = 8;

        int maxPrice = findMaxPrice(priceMap, n);

        System.out.println(maxPrice);
    }

    private static int findMaxPrice(Map<Integer, Integer> priceMap, int n) {
        int[] maxPrices = new int[n + 1];
        Arrays.fill(maxPrices, -1000);

        for (int i = 1; i <= n; i++) {
            for (Map.Entry<Integer, Integer> priceEntry : priceMap.entrySet()) {
                int x = priceEntry.getKey();
                int y = i - priceEntry.getKey();

                if (x < 0 || y < 0) {
                    continue;
                }

                int price = getPrice(x, maxPrices, priceMap) + getPrice(y, maxPrices, priceMap);

                if (price > maxPrices[i]) {
                    maxPrices[i] = price;
                }
            }
        }

        return maxPrices[n];
    }

    private static int getPrice(int i, int[] maxPrices, Map<Integer, Integer> priceMap) {
        if (i == 0) {
            return 0;
        }

        if (i >= 1 && i < maxPrices.length) {
            return Math.max(priceMap.get(i), maxPrices[i]);
        }

        return -1000;
    }
}
