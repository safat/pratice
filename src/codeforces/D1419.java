package codeforces;


import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * @author muhossain
 * @since 2020-10-03
 */

public class D1419 {

    public static void main(String[] args) {
        FasterScanner fs = new FasterScanner();
        int n = fs.nextInt();

        StringBuilder output = new StringBuilder();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = fs.nextInt();
        }

        Arrays.sort(nums);

        boolean[] marker = new boolean[n + 1];

        int[] outputNm = new int[n];

        int position = 1;

        for (int i = 0; i < n / 2; i++) {
            outputNm[position] = nums[i];
            marker[position] = true;

            position += 2;

        }

        position = 0;

        for (int i = n / 2; i < nums.length; i++) {
            while (marker[position]) {
                position++;
            }

            outputNm[position] = nums[i];

            position++;
        }


        int count = 0;

        for (int i = 0; i < outputNm.length; i++) {
            output.append(outputNm[i]).append(" ");

            if (i != 0 && i != outputNm.length - 1) {
                if (outputNm[i] < outputNm[i - 1] && outputNm[i] < outputNm[i + 1]) {
                    count++;
                }
            }
        }

        System.out.println(count + "\n" + output.substring(0, output.length() - 1));
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
