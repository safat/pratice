package codeforces;


import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * @author muhossain
 * @since 2020-09-28
 */

public class B1417 {

    public static void main(String[] args) {
        FasterScanner fs = new FasterScanner();
        int T = fs.nextInt();

        StringBuilder result = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringBuilder out = new StringBuilder();

            int n = fs.nextInt();
            int u = fs.nextInt();

            int[] nums = new int[n];

            for (int i = 0; i < n; i++) {
                nums[i] = fs.nextInt();
            }

            Map<Integer, Integer> frequencyMap = new HashMap<>();

            for (int i = 0; i < n; i++) {
                frequencyMap.put(nums[i], frequencyMap.getOrDefault(nums[i], 0) + 1);
            }

            boolean wentLeft = false;

            for (int i = 0; i < n; i++) {
                if (nums[i] < (u + 1) / 2) {
                    out.append("1 ");
                } else if (nums[i] > u / 2) {
                    out.append("0 ");
                } else {
                    if (!wentLeft) {
                        out.append("1 ");
                    } else {
                        out.append("0 ");
                    }

                    wentLeft = !wentLeft;
                }
            }

            result.append(out.substring(0, out.length() - 1)).append("\n");
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
