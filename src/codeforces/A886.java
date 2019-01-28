//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A886 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int[] scores = new int[input.length];

        int sum = 0;

        for (int i = 0; i < input.length; i++) {
            scores[i] = Integer.parseInt(input[i]);
            sum += scores[i];
        }

        double teamScore = sum / 2.0;
        boolean possible = false;

        for (int i = 0; i < scores.length - 2; i++) {
            for (int j = i + 1; j < scores.length - 1; j++) {
                for (int k = j + 1; k < scores.length; k++) {
                    if (scores[i] + scores[j] + scores[k] == teamScore) {
                        possible = true;
                        break;
                    }
                }
            }
        }

        System.out.println(possible ? "YES" : "NO");
    }
}
