package codeforces.D538;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        long n = Long.parseLong(input[0]);
        long base = Long.parseLong(input[1]);

        long zeroCount = Long.MAX_VALUE;

        Map<Long, Long> primeFactorsFrequencyMap = getPrimeFactorFrequencyMap(base);

        for (Map.Entry<Long, Long> entry : primeFactorsFrequencyMap.entrySet()) {
            long p = entry.getKey();
            long c = entry.getValue();

            long count = 0;
            long k = n;

            while (k / p > 0) {
                count += k / p;
                k /= p;
            }

            zeroCount = Math.min(zeroCount, count / c);
        }

        System.out.println(zeroCount);
    }

    private static Map<Long, Long> getPrimeFactorFrequencyMap(long base) {
        Map<Long, Long> primeFactorFrequencyMap = new HashMap<>();

        long twoCount = 0;

        while (base % 2 == 0) {
            base /= 2;
            twoCount++;
        }

        if (twoCount > 0) {
            primeFactorFrequencyMap.put(2L, twoCount);
        }

        for (int i = 3; i <= Math.sqrt(base); i += 2) {
            long count = 0;

            while (base % i == 0) {
                base /= i;
                count++;
            }

            if (count > 0) {
                primeFactorFrequencyMap.put((long) i, count);
            }
        }

        if (base > 2) {
            primeFactorFrequencyMap.put(base, 1L);
        }

        return primeFactorFrequencyMap;
    }
}
