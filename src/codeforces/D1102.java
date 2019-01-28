package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class D1102 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger test = null;

        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int[] freq = new int[4];
        int[] numbers = new int[n];

        int j = 0;
        int fi = n / 3;

        for (char c : s.toCharArray()) {
            numbers[j] = Integer.parseInt(String.valueOf(c));
            freq[numbers[j]]++;

            j++;
        }

        //forward 0
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 2 && freq[2] > fi && freq[0] < fi) {
                freq[2]--;
                freq[0]++;
                numbers[i] = 0;
            } else if (numbers[i] == 1 && freq[1] > fi && freq[0] < fi) {
                freq[1]--;
                freq[0]++;
                numbers[i] = 0;
            }
        }

        //forward 1
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 2 && freq[2] > fi && freq[1] < fi) {
                freq[2]--;
                freq[1]++;
                numbers[i] = 1;
            }

        }

        //backward 2
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] != 2 && freq[numbers[i]] > fi && freq[2] < fi) {
                freq[numbers[i]]--;
                freq[2]++;

                numbers[i] = 2;
            }
        }

        //backward 1

        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] == 0 && freq[0] > fi && freq[1] < fi) {
                freq[1]++;
                freq[0]--;
                numbers[i] = 1;
            }
        }

        StringBuilder output = new StringBuilder();

        for (int digit : numbers) {
            output.append(digit);
        }

        System.out.println(output);
    }
}
