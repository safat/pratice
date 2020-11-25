//package codeforces;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author muhossain
 * @since 2020-09-28
 */

public class A1416 {

    public static void main(String[] args) {
        FasterScanner fs = new FasterScanner();
        int T = fs.nextInt();

        StringBuilder result = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringBuilder output = new StringBuilder();

            int n = fs.nextInt();

            int[] nums = new int[n];

            for (int i = 0; i < nums.length; i++) {
                nums[i] = fs.nextInt();
            }

            Map<Integer, Integer> prevPositionMap = new HashMap<>();
            Map<Integer, Integer> maxIntervalMap = new HashMap<>();
            Map<Integer, Integer> numberIntervalMap = new TreeMap<>();

            for (int i = 0; i < nums.length; i++) {
                if (!prevPositionMap.containsKey(nums[i])) {
                    numberIntervalMap.put(nums[i], i + 1);
                    prevPositionMap.put(nums[i], i + 1);
                } else {
                    int prevPosition = prevPositionMap.get(nums[i]);
                    int currentInterval = i + 1 - prevPosition;
                    int prevInterval = numberIntervalMap.get(nums[i]);

                    numberIntervalMap.put(nums[i], Math.max(currentInterval, prevInterval));
                    prevPositionMap.put(nums[i], i + 1);
                }
            }

            for (Map.Entry<Integer, Integer> prevPositionEntry : prevPositionMap.entrySet()) {
                int num = prevPositionEntry.getKey();
                numberIntervalMap.put(num, Math.max(numberIntervalMap.get(num),
                        nums.length - prevPositionEntry.getValue() + 1));
            }

            for (Map.Entry<Integer, Integer> numberIntervalEntry : numberIntervalMap.entrySet()) {
                if (maxIntervalMap.containsKey(numberIntervalEntry.getValue()) &&
                        maxIntervalMap.get(numberIntervalEntry.getValue()) < numberIntervalEntry.getKey()) {
                    continue;
                }

                maxIntervalMap.put(numberIntervalEntry.getValue(), numberIntervalEntry.getKey());
            }

            int lastNonNeg = Integer.MAX_VALUE;

            for (int i = 1; i <= n; i++) {
                if (maxIntervalMap.containsKey(i)) {
                    lastNonNeg = Math.min(lastNonNeg, maxIntervalMap.get(i));

                    output.append(lastNonNeg).append(" ");
                } else {
                    output.append(lastNonNeg < Integer.MAX_VALUE ? lastNonNeg + " " : "-1 ");
                }
            }

            result.append(output.substring(0, output.length() - 1)).append("\n");
        }

        System.out.print(result);
    }

    public static class FasterScanner {
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public String nextLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        public long[] nextLongArray(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
    }
}
