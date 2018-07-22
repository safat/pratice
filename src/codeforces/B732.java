package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.stream.IntStream;

public class B732 {
    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);

        int n = reader.readInt();
        int k = reader.readInt();

        int input[] = new int[n];

        for (int i = 0; i < n; i++) {
            input[i] = reader.readInt();
        }

        int[] output = new int[n];
        output[0] = input[0];
        int totalDelta = 0;

        for (int i = 1; i < n; i++) {
            if (input[i] + output[i - 1] < k) {
                output[i] = input[i] + (k - (input[i] + output[i - 1]));
                totalDelta += (output[i] - input[i]);
            } else {
                output[i] = input[i];
            }
        }

        System.out.println(totalDelta);

        System.out.println(IntStream.of(output).mapToObj(String::valueOf).reduce((a, b) -> a.concat(" ").concat(b)).get());
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
