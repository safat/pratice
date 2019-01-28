//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class C276 {

    static class Range {
        int x, y;

        public Range(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Pair {
        int index, frequency;

        public Pair(int index, int frequency) {
            this.index = index;
            this.frequency = frequency;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int q = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");

        int[] numbers = new int[n];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        List<Range> ranges = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            input = br.readLine().split(" ");
            ranges.add(new Range(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        int[] frequency = new int[n + 2];

        for (int i = 0; i < ranges.size(); i++) {
            frequency[ranges.get(i).x]++;
            frequency[ranges.get(i).y + 1]--;
        }

        for (int i = 1; i < frequency.length; i++) {
            frequency[i] = frequency[i] + frequency[i - 1];
        }

        List<Pair> indexFreq = new ArrayList<>();

        for (int i = 1; i < frequency.length - 1; i++) {
            indexFreq.add(new Pair(i, frequency[i]));
        }

        indexFreq.sort((o1, o2) -> o2.frequency - o1.frequency);
        Arrays.sort(numbers);

        long sum = 0;

        for (int i = 0; i < indexFreq.size(); i++) {
            sum += ((long) indexFreq.get(i).frequency * (long) numbers[n - i - 1]);
        }

        System.out.println(sum);
    }
}
