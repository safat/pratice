package codeforces.D666;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-08-14
 */

public class D {

    public static void main(String[] args) {
        FastReader fs = new FastReader();

        int testCases = fs.nextInt();
        StringBuilder output = new StringBuilder();

        for (int t = 0; t < testCases; t++) {
            int n = fs.nextInt();

            Queue<Integer> pQueue = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < n; i++) {
                pQueue.add(fs.nextInt());
            }

            boolean aWins = calculate(pQueue);

            output.append(aWins ? "T" : "HL").append("\n");
        }

        System.out.print(output);
    }

    private static boolean calculate(Queue<Integer> pQueue) {
        int aPick = pQueue.poll();
        int bPick = -1;
        aPick--;

        if (pQueue.isEmpty()) {
            return true;
        }

        boolean aTurn = false;

        while (true) {
            if (!aTurn) { // b turn
                if (pQueue.isEmpty()) {
                    return true;
                }

                bPick = pQueue.poll();
                bPick--;

                if (aPick > 0) {
                    pQueue.add(aPick);
                }

                aTurn = true;
            } else { // a turn
                if (pQueue.isEmpty()) {
                    return false;
                }

                aPick = pQueue.poll();
                aPick--;

                if (bPick > 0) {
                    pQueue.add(bPick);
                }

                aTurn = false;
            }
        }
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
