package codeforces;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.TreeMap;
 
public class ApproximatingAConstantRange {
    public static void main(String[] args) {
        FasterScanner sc = new FasterScanner();
        
        int N = sc.nextInt();
        int[] A = sc.nextIntArray(N);
        
        CountMap cm = new CountMap();
        cm.addCount(A[0], 1);
        cm.addCount(A[1], 1);
        int best = 2;
        int prev = 0;
        for (int i = 2; i < N; i++) {
            cm.addCount(A[i], 1);
            while (cm.lastKey() - cm.firstKey() > 1) {
                cm.addCount(A[prev], -1);
                prev++;
            }
            best = Math.max(best, cm.total);
        }
        System.out.println(best);
    }
    
    public static class CountMap extends TreeMap<Integer, Integer> {
        private static final long serialVersionUID = 4000091495463636444L;
        
        public int total = 0;
 
        public int getCount(int k) {
            return this.containsKey(k) ? this.get(k) : 0;
        }
        
        public void addCount(int k, int v) {
            this.total += v;
            int val = this.getCount(k) + v;
            if (val == 0) {
                this.remove(k);
            } else {
                this.put(k, val);
            }
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