package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A909 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        StringBuilder output = new StringBuilder();
        output.append(input[0].charAt(0));

        int i = 1;

        while (i < input[0].length()) {
            if (input[0].charAt(i) >= input[1].charAt(0)) {
                break;
            }

            output.append(input[0].charAt(i));
            i++;
        }

        output.append(input[1].charAt(0));

        System.out.println(output);
    }
}
