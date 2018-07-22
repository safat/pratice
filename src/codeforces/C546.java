package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class C546 {
    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        int n = reader.readInt();
        int k1 = reader.readInt();

        Queue<Integer> p1 = new LinkedList<>();
        Queue<Integer> p2 = new LinkedList<>();

        for (int i = 0; i < k1; i++) {
            p1.add(reader.readInt());
        }

        int k2 = reader.readInt();

        for (int i = 0; i < k2; i++) {
            p2.add(reader.readInt());
        }

        int i = 0;

        while (i++ < 110) {
            if (p1.isEmpty() || p2.isEmpty()) {
                break;
            }

            int c1 = p1.poll();
            int c2 = p2.poll();

            if (c1 > c2) {
                p1.add(c2);
                p1.add(c1);
            } else {
                p2.add(c1);
                p2.add(c2);
            }
        }

        Boolean winner1 = null;

        if (p1.isEmpty()) {
            winner1 = false;
        }

        if (p2.isEmpty()) {
            winner1 = true;
        }

        --i;

        if (winner1 == null) {
            System.out.println("-1");
        } else {
            System.out.println(winner1 ? i + " 1" : i + " 2");
        }
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
