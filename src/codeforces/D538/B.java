package codeforces.D538;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class B {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        long n = fs.nextInt();
        int m = fs.nextInt();
        int k = fs.nextInt();

        List<Item> itemList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            itemList.add(new Item(fs.nextInt(), i));
        }

        itemList.sort((i1, i2) -> i2.value - i1.value);

        BigInteger result = BigInteger.ZERO;

        List<Item> filteredItems = new ArrayList<>();

        for (int i = 0; i < m * k; i++) {
            result = result.add(BigInteger.valueOf(itemList.get(i).value));
            filteredItems.add(itemList.get(i));
        }

        filteredItems.sort(Comparator.comparingInt(i -> i.index));

        StringBuilder output = new StringBuilder();

        for (int i = m - 1, count = 0; count < k - 1; i += m, count++) {
            output.append(filteredItems.get(i).index).append(" ");
        }

        System.out.println(result.toString());
        System.out.println(output);
    }

    static class Item {
        int value, index;

        public Item(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    static class FastScanner {
        private BufferedReader reader = null;
        private StringTokenizer tokenizer = null;

        public FastScanner(InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        public String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
