package leetcode;

/**
 * @author muhossain
 * @since 2020-08-15
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class L560 {

    public static void main(String[] args) {
        System.out.println(new L560().subarraySum(new int[]{10, 2, -2, -20, 10}, -10));
    }

    public int subarraySum(int[] nums, int k) {
        int result = 0;

        Map<Integer, Integer> prefixSumFrequency = new HashMap<>();

        int prefixSum = 0;

        for (int num : nums) {
            prefixSum += num;

            if (prefixSum == k) {
                result++;
            }

            if (prefixSumFrequency.containsKey(prefixSum - k)) {
                result += prefixSumFrequency.get(prefixSum - k);
            }

            prefixSumFrequency.putIfAbsent(prefixSum, 0);
            prefixSumFrequency.put(prefixSum, prefixSumFrequency.get(prefixSum) + 1);
        }

        return result;
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public static FastReader getFileReader(String fileName) throws FileNotFoundException {
            return new FastReader(new InputStreamReader(new FileInputStream(new File(fileName))));
        }

        public static FastReader getDefaultReader() throws FileNotFoundException {
            return new FastReader();
        }

        public FastReader(InputStreamReader inputStreamReader) {
            br = new BufferedReader(inputStreamReader);
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
