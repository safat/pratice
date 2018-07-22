package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B266 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int t = Integer.parseInt(input[1]);

        char[] queue = br.readLine().toCharArray();

        for (int i = 0; i < t; i++) {
            boolean hasTransformation = false;

            for (int j = 1; j < queue.length; j++) {
                if (queue[j] == 'G' && queue[j - 1] == 'B') {
                    queue[j] = 'B';
                    queue[j - 1] = 'G';

                    j++;
                    hasTransformation = true;
                }
            }

            if (!hasTransformation) {
                break;
            }
        }

        System.out.println(String.valueOf(queue));
    }
}
