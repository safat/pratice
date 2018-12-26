//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B32 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int index = 0;
        StringBuilder output = new StringBuilder();

        while (index < input.length()) {
            if (input.charAt(index) == '.') {
                output.append("0");
                index++;
            } else {
                if (input.charAt(index + 1) == '.') {
                    output.append("1");
                    index += 2;
                } else {
                    output.append("2");
                    index += 2;
                }
            }
        }

        System.out.println(output);
    }
}
