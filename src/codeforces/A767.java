package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A767 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] items = br.readLine().split(" ");
        int[] snacks = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            snacks[i] = Integer.parseInt(items[i]);
        }

        boolean[] seen = new boolean[n + 1];

        int top = snacks.length;

        StringBuilder output = new StringBuilder();

        for (int snack : snacks) {
            StringBuilder row = new StringBuilder();

            if (snack == top) {
                row = new StringBuilder().append(top);

                top -= 1;

                while (seen[top]) {
                    row.append(" ").append(top);

                    top--;
                }
            } else {
                seen[snack] = true;
            }

            output.append(row).append("\n");
        }

        System.out.println(output);
    }

}
