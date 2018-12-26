package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A507 {

    static class Pair implements Comparable<Pair> {
        int day, index;

        public Pair(int day, int index) {
            this.day = day;
            this.index = index;
        }

        @Override
        public int compareTo(Pair o1) {
            return this.day - o1.day;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "day=" + day +
                    ", index=" + index +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputMeta = br.readLine().split(" ");

        int n = Integer.parseInt(inputMeta[0]);
        int k = Integer.parseInt(inputMeta[1]);

        List<Pair> instruments = new ArrayList<>();

        inputMeta = br.readLine().split(" ");

        for (int i = 0; i < inputMeta.length; i++) {
            instruments.add(new Pair(Integer.parseInt(inputMeta[i]), i + 1));
        }

        Collections.sort(instruments);

        int instrumentCount = 0;
        StringBuilder indexes = new StringBuilder();

        for (Pair cInstrument : instruments) {
            if (cInstrument.day <= k) {
                instrumentCount++;
                indexes.append(cInstrument.index).append(" ");

                k -= cInstrument.day;
            }
        }

        System.out.println(instrumentCount);

        if (indexes.length() > 0) {
            System.out.println(indexes.substring(0, indexes.length() - 1));
        }
    }
}
