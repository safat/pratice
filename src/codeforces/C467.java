package codeforces;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author muhossain
 * @since 2020-08-01
 */

public class C467 {

    public static void main(String[] args) throws IOException {
        Reader fs = new Reader();
        int n = fs.nextInt();
        int m = fs.nextInt();
        int k = fs.nextInt();

        long[] nums = new long[n];

        for (int i = 0; i < n; i++) {
            nums[i] = fs.nextLong();
        }

        long[][] dp = new long[k + 1][n];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        long[] cSum = new long[n + 1];
        cSum[0] = nums[0];
        for (int j = 1; j < n; j++) {
            cSum[j] = cSum[j - 1] + nums[j];
        }

        findMax(n - 1, m, k, cSum, dp);

//        for (int i = 1; i <= k; i++) {
//            for (int j = i * m; j <= n; j++) {
//                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - m] + cSum[j] - cSum[j - m]);
//            }
//        }

        System.out.println(dp[k][n - 1]);
    }

    private static long findMax(int position, int m, int k, long[] cSum, long[][] dp) {
        if (position >= 0 && dp[k][position] != -1) {
            return dp[k][position] == Integer.MIN_VALUE ? 0 : dp[k][position];
        }

        if (position - m + 1 < 0) {
            return k > 0 ? Integer.MIN_VALUE : 0;
        }

        if (k == 0 && position - m + 1 >= 0) {
            return 0;
        }

        long sumIfTaken = cSum[position] - (position - m < 0 ? 0 : cSum[position - m]);

        long left = findMax(position - m, m, k - 1, cSum, dp);
        long right = findMax(position - 1, m, k, cSum, dp);

        dp[k][position] = Math.max(
                left == Integer.MIN_VALUE ? left : left + sumIfTaken,
                right
        );


        return dp[k][position];
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}

