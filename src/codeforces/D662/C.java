package codeforces.D662;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * @author muhossain
 * @since 2020-08-08
 */

public class C {
    public static void main(String[] args) {
        FasterScanner fs = new FasterScanner();

        int t = fs.nextInt();
        StringBuilder output = new StringBuilder();


        for (int i = 1; i <= t; i++) {
            int n = fs.nextInt();

            Map<Integer, Integer> freMap = new HashMap<>();

            int maxFreq = 0;
            int maxFreqCount = 0;

            for (int j = 0; j < n; j++) {
                int a = fs.nextInt();

                freMap.putIfAbsent(a, 0);
                freMap.put(a, freMap.get(a) + 1);

                if (freMap.get(a) > maxFreq) {
                    maxFreq = freMap.get(a);
                    maxFreqCount = 1;
                } else if (freMap.get(a) == maxFreq) {
                    maxFreqCount++;
                }
            }

            int result = (((n - maxFreqCount) / (maxFreq - 1)) - 1);

            output.append(result).append("\n");
        }

        System.out.print(output);
    }

    static class Item {
        int a;
        int freq;

        public Item(int a, int freq) {
            this.a = a;
            this.freq = freq;
        }
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
