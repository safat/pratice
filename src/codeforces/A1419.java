package codeforces;


import java.io.IOException;
import java.util.InputMismatchException;

/**
 * @author muhossain
 * @since 2020-10-03
 */

public class A1419 {

    public static void main(String[] args) {
        FasterScanner fs = new FasterScanner();
        int totalTest = fs.nextInt();

        StringBuilder output = new StringBuilder();

        for (int i = 1; i <= totalTest; i++) {
            int len = fs.nextInt();
            String numStr = fs.nextLine();

//            List<Integer> aList = new ArrayList<>();
//            List<Integer> bList = new ArrayList<>();
//
//            for (int d = 0; d < len; d++) {
//
//                if (((d + 1) % 2) == 0) {
//                    bList.add((Character.getNumericValue(n.charAt(d)) % 10) % 2);
//                } else {
//                    aList.add((Character.getNumericValue(n.charAt(d)) % 10) % 2);
//                }
//            }
//
//            Collections.sort(aList);
//            bList.sort(Collections.reverseOrder());
//
//            while (true) {
//                if (aList.size() + bList.size() >= 2) {
//                    aList.remove(0);
//                }
//
//                if (aList.size() + bList.size() == 1) {
//                    int item = aList.size() > 0 ? aList.get(0) : bList.get(0);
//
//                    if (item == 1) {
//                        output.append("1\n");
//                    } else {
//                        output.append("2\n");
//                    }
//
//                    break;
//                }
//
//                if (bList.size() > 0) {
//                    bList.remove(0);
//                }
//            }

            // editorial solution

            boolean hasOddInOddPosition = false;
            boolean hasEvenInEvenPosition = false;

            for (int index = 0; index < numStr.length(); index++) {

                if (Character.getNumericValue(numStr.charAt(index)) % 2 == 0) {
                    if ((index + 1) % 2 == 0) {
                        hasEvenInEvenPosition = true;
                    }
                } else {
                    if ((index + 1) % 2 == 1) {
                        hasOddInOddPosition = true;
                    }
                }
            }

            if (len % 2 == 0) {
                output.append(hasEvenInEvenPosition ? "2\n" : "1\n");
            } else {
                output.append(hasOddInOddPosition ? "1\n" : "2\n");
            }
        }

        System.out.print(output);
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
