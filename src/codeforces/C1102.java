//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C1102 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int x = Integer.parseInt(input[1]);
        int y = Integer.parseInt(input[2]);

        input = br.readLine().split(" ");

        if (x > y) {
            System.out.println(n);
            System.exit(0);
        }

        int cnt = 0;

        for (String in : input) {
            if (Integer.parseInt(in) <= x) {
                cnt++;
            }
        }

        System.out.println((cnt + 1) / 2);
    }
}
