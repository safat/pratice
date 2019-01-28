//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A1102 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n % 4 == 0 || (n + 1) % 4 == 0) {
            System.out.println(0);
        } else {
            System.out.println(1);
        }
    }
}
