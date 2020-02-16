package spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muhossain
 * @since 2020-02-04
 */

public class NiceBTreeShorter {
    private static int idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<String> inputs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            inputs.add(br.readLine());
        }

        StringBuilder output = new StringBuilder();

        for (String input : inputs) {
            idx = 0;

            output.append(depth(input)).append("\n");
        }

        System.out.print(output);
    }

    private static int depth(String input) {
        if (input.charAt(idx++) == 'l') {
            return 0;
        }

        return Math.max(depth(input), depth(input)) + 1;
    }
}
