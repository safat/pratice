package hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoyAndTrendingTopic {

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader();

        int n = reader.readInt();
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int[] input = reader.readIntArray();

            int id = input[0];
            BigInteger zVal = BigInteger.valueOf(input[2]).multiply(BigInteger.valueOf(50))
                    .add(BigInteger.valueOf(input[3]).multiply(BigInteger.valueOf(5)))
                    .add(BigInteger.valueOf(input[4]).multiply(BigInteger.valueOf(10)))
                    .add(BigInteger.valueOf(input[5]).multiply(BigInteger.valueOf(20)));

            BigInteger zDelta = zVal.subtract(BigInteger.valueOf(input[1]));

            items.add(new Item(id, zVal, zDelta));
        }

        Collections.sort(items);

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            output.append(items.get(i).id).append(" ").append(items.get(i).zVal).append("\n");
        }

        System.out.println(output);
    }

    static class Item implements Comparable<Item> {
        int id;
        BigInteger zDelta;
        BigInteger zVal;

        public Item(int id, BigInteger zVal, BigInteger zDelta) {
            this.id = id;
            this.zVal = zVal;
            this.zDelta = zDelta;
        }

        @Override
        public int compareTo(Item o) {
            return o.zDelta.equals(this.zDelta) ? o.id - this.id : o.zDelta.compareTo(zDelta);
        }
    }

    static class InputReader {
        private BufferedReader br;

        public InputReader() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String readLine() throws IOException {
            return br.readLine();
        }

        int readInt() throws IOException {
            return Integer.parseInt(br.readLine());
        }

        public int[] readIntArray() throws IOException {
            String[] input = br.readLine().split(" ");

            int[] data = new int[input.length];

            for (int i = 0; i < input.length; i++) {
                data[i] = Integer.parseInt(input[i]);
            }

            return data;
        }
    }
}
