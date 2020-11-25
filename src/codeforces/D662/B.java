package codeforces.D662;


import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * @author muhossain
 * @since 2020-08-07
 */

public class B {

    public static void main(String[] args) {
        FasterScanner fs = new FasterScanner();
        int n = fs.nextInt();

        int[] freq = new int[100001];

        Map<Integer, Map<Integer, Boolean>> numMappings = new HashMap<>();
        numMappings.putIfAbsent(2, new HashMap<>());
        numMappings.putIfAbsent(4, new HashMap<>());
        numMappings.putIfAbsent(6, new HashMap<>());
        numMappings.putIfAbsent(8, new HashMap<>());

        for (int i = 0; i < n; i++) {
            int p = fs.nextInt();

            freqMapping(freq, numMappings, p, true);
        }

        int e = fs.nextInt();

        StringBuilder output = new StringBuilder();

        for (int i = 1; i <= e; i++) {
            String[] event = fs.nextLine().split(" ");
            String operation = event[0];
            int num = Integer.parseInt(event[1]);

            switch (operation) {
                case "+":
                    freqMapping(freq, numMappings, num, true);
                    break;

                case "-":
                    freqMapping(freq, numMappings, num, false);
            }

            if (solve(numMappings)) {
                output.append("YES\n");
            } else {
                output.append("NO\n");
            }
        }

        System.out.print(output);
    }

    private static boolean solve(Map<Integer, Map<Integer, Boolean>> numMappings) {
        Map<Integer, Boolean> eightMap = numMappings.get(8);

        if (eightMap.size() >= 1) {
            return true;
        }

        Map<Integer, Boolean> sixMap = numMappings.get(6);
        Map<Integer, Boolean> fourMap = numMappings.get(4);
        Map<Integer, Boolean> twoMap = numMappings.get(2);

        if (sixMap.size() >= 2) {
            return true;
        }

        if (sixMap.size() == 1) {
            if (fourMap.size() >= 1 || twoMap.size() >= 1) {
                return true;
            }
        }

        if (fourMap.size() >= 2) {
            return true;
        }

        if (fourMap.size() == 1) {
            return twoMap.size() >= 2;
        }

        return false;
    }

    private static void freqMapping(int[] freq, Map<Integer, Map<Integer, Boolean>> numMappings, int p, boolean add) {
        if (add) {
            freq[p]++;
        } else {
            freq[p]--;
        }


        for (Map.Entry<Integer, Map<Integer, Boolean>> entry : numMappings.entrySet()) {
            Map<Integer, Boolean> freqMap = entry.getValue();
            freqMap.remove(p);
        }


        if (freq[p] >= 8) {
            numMappings.get(8).put(p, true);
        } else if (freq[p] >= 6) {
            numMappings.get(6).put(p, true);
        } else if (freq[p] >= 4) {
            numMappings.get(4).put(p, true);
        } else if (freq[p] >= 2) {
            numMappings.get(2).put(p, true);
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
