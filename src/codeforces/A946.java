package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A946 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();

        int sum = 0;

        for (String nStr : br.readLine().split(" ")) {
            sum += Math.abs(Integer.parseInt(nStr));
        }

        System.out.println(sum);
    }
}
