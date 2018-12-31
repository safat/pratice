//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B796 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input[] = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);

        input = br.readLine().split(" ");

        boolean holes[] = new boolean[n + 1];
        Arrays.stream(input).map(Integer::parseInt).forEach(h -> holes[h] = true);

        int poi = 1;

        for (int i = 0; i < k; i++) {
            if (holes[poi]) {
                break;
            }

            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            if (u == poi) {
                poi = v;
            } else if (v == poi) {
                poi = u;
            }
        }

        System.out.println(poi);
    }
}
