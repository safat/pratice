package codeforces.D1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<String> inputs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            br.readLine();
            inputs.add(br.readLine());
        }

        StringBuilder output = new StringBuilder();

        for (String number : inputs) {
            String firstPart = number.charAt(0) + "";
            String secondPart = number.substring(1).replaceAll("^0+", "");

            if (secondPart.length() <= 1) {
                if (secondPart.isEmpty() || Integer.parseInt(secondPart) <= Integer.parseInt(firstPart)) {
                    output.append("NO").append("\n");
                } else {
                    output.append("YES").append("\n")
                            .append("2").append("\n")
                            .append(firstPart).append(" ").append(number.substring(1))
                            .append("\n");
                }
            } else {
                output.append("YES").append("\n")
                        .append("2").append("\n")
                        .append(firstPart).append(" ").append(number.substring(1))
                        .append("\n");
            }
        }

        System.out.print(output);
    }
}
