package codeforces.D93;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-14
 */

public class D {
    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int r = fs.nextInt();
        int g = fs.nextInt();
        int b = fs.nextInt();

        List<Integer> reds = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            reds.add(fs.nextInt());
        }

        List<Integer> green = new ArrayList<>();

        for (int j = 0; j < g; j++) {
            green.add(fs.nextInt());
        }

        List<Integer> blue = new ArrayList<>();

        for (int k = 0; k < b; k++) {
            blue.add(fs.nextInt());
        }

        reds.sort(Collections.reverseOrder());
        green.sort(Collections.reverseOrder());
        blue.sort(Collections.reverseOrder());

        int[][][] dp = new int[r + 1][g + 1][b + 1];

        int ans = solve(0, 0, 0, reds, green, blue, dp);

        System.out.println(ans);
    }

    private static int solve(int r, int g, int b, List<Integer> reds, List<Integer> green,
                             List<Integer> blue, int[][][] dp) {

        if (dp[r][g][b] > 0) {
            return dp[r][g][b];
        }

        int totalOverflow = 0;

        if (r >= reds.size()) {
            totalOverflow++;
        }

        if (g >= green.size()) {
            totalOverflow++;
        }

        if (b >= blue.size()) {
            totalOverflow++;
        }

        if (totalOverflow >= 2) {
            return 0;
        }

        return dp[r][g][b] = Math.max(
                Math.max(
                        r < reds.size() && g < green.size() ? (reds.get(r) * green.get(g)) + solve(r + 1, g + 1, b, reds, green, blue, dp) : -1,
                        g < green.size() && b < blue.size() ? (green.get(g) * blue.get(b)) + solve(r, g + 1, b + 1, reds, green, blue, dp) : -1
                ),
                r < reds.size() && b < blue.size() ? (reds.get(r) * blue.get(b)) + solve(r + 1, g, b + 1, reds, green, blue, dp) : -1
        );
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public static FastReader getFileReader(String fileName) throws FileNotFoundException {
            return new FastReader(new InputStreamReader(new FileInputStream(new File(fileName))));
        }

        public static FastReader getDefaultReader() throws FileNotFoundException {
            return new FastReader();
        }

        public FastReader(InputStreamReader inputStreamReader) {
            br = new BufferedReader(inputStreamReader);
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
