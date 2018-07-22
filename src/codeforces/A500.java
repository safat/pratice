package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class A500 {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);

        int n = in.readInt();
        int t = in.readInt();

        int[] a = in.readIntArray(n - 1);

        int i = 1;

        while (i < t) {
            i += a[i - 1];
        }

        System.out.println(i == t ? "YES" : "NO");
    }

    public static String join(Integer[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return null;
        }

        StringBuilder result = new StringBuilder();

        for (int n : numbers) {
            result.append(n).append(", ");
        }

        return result.substring(0, result.length() - 1);
    }

    static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() {
            try {
                if (curChar >= numChars) {
                    curChar = 0;
                    numChars = stream.read(buf);
                    if (numChars <= 0)
                        return -1;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return buf[curChar++];
        }

        public int readInt() {
            return (int) readLong();
        }

        public long readLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
                if (c == -1) throw new RuntimeException();
            }
            boolean negative = false;
            if (c == '-') {
                negative = true;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                res *= 10;
                res += (c - '0');
                c = read();
            } while (!isSpaceChar(c));
            return negative ? (-res) : (res);
        }

        public int[] readIntArray(int size) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) arr[i] = readInt();
            return arr;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

    }
}


