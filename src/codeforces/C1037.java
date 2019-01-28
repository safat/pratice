package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C1037 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();
        int cost = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) {
                continue;
            }

            if (i < a.length - 1 && a[i + 1] != b[i + 1] && a[i + 1] == b[i]) {
                i++;
            }

            cost++;
        }

        System.out.println(cost);
    }
}
