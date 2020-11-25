package codeforces.D1395;

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

        int n = fs.nextInt();
        int d = fs.nextInt();
        int m = fs.nextInt();

        List<Integer> funFactors = new ArrayList<Integer>();

        int limitBreach = 0;

        long total = 0;

        List<Integer> lowFun = new ArrayList<>();
        List<Integer> highFun = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int fun = fs.nextInt();

            if (fun > m) {
                highFun.add(fun);
            } else {
                lowFun.add(fun);
            }
        }

        Collections.sort(lowFun);
        Collections.sort(highFun);

        int[] lowFunCum = new int[lowFun.size() + 1];

        lowFunCum[1] = lowFun.size() > 0 ? lowFun.get(0) : 0;

        for (int i = 2; i <= lowFun.size(); i++) {
            lowFunCum[i] = lowFunCum[i - 1] + lowFun.get(i - 1);
        }

        int lowFunPointer = 0;
        long result = 0;

        for (int i = 0; i < highFun.size(); i++) {
            int currentHighFun = highFun.get(i);

            int replacement = lowFunCum[Math.min(lowFunPointer + d, lowFunCum.length - 1)] - lowFunCum[lowFunPointer];

            if (currentHighFun > replacement) {
                lowFunPointer = lowFunPointer + d;

                result += currentHighFun;
            }

            if (lowFunPointer >= lowFun.size()) {
                break;
            }
        }

        for (int i = lowFunPointer; i < lowFun.size(); i++) {
            result += lowFun.get(i);
        }

        System.out.print(result);
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
