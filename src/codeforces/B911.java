//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B911 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int a = Integer.parseInt(input[1]);
        int b = Integer.parseInt(input[2]);

        int minMax = 1;

        for (int i = 1; i <= n / 2; i++) {

            int da = i;
            int db = n - i;

            if (a / da > 0 && b / db > 0) {
                int min = Math.min(a / da, b / db);
                minMax = Math.max(minMax, min);
            }

            if (a / db > 0 && b / da > 0) {
                int min = Math.min(a / db, b / da);
                minMax = Math.max(minMax, min);
            }
        }

        System.out.println(minMax);
    }
}
