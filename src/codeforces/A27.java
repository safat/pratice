//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A27 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       br.readLine();

        String[] input = br.readLine().split(" ");

        boolean[] seen = new boolean[3001];

        for (String numberStr : input) {
            seen[Integer.parseInt(numberStr)] = true;
        }

        int i = 1;

        for (i = 1; i < seen.length; i++) {
            if (!seen[i]) {
                break;
            }
        }

        System.out.println(i);
    }
}
