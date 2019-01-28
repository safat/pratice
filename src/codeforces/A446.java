//package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A446 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        String[] input = br.readLine().split(" ");
        int[][] items = new int[input.length][2];

        for (int i = 0; i < input.length; i++) {
            items[i][0] = Integer.parseInt(input[i]);
        }

        int increasingSeq = 1;
        int max = Integer.MIN_VALUE;
        int incrAfterAlt = 0;
        int altIndex = -2;
        boolean altered = false;
//        7 2 3 4 5 1 2 3 4 5 1 7 8 9 10 11 12 13
        for (int i = 1; i < items.length; i++) {
            if ((items[i][0] > items[i - 1][0]) || (altered && i == altIndex + 1 && items[i][0] > items[i - 1][1])) {
                increasingSeq++;

                if (altered) {
                    incrAfterAlt++;
                }
            } else {
                if (altered) {
                    increasingSeq = 1;
                    i = Math.max(altIndex, 1);
                    altered = false;
                } else {
                    if (i == 1) {
                        altIndex = 0;
                    } else {
                        items[i][1] = items[i - 1][0] + 1;
                        altIndex = i;
                    }

                    altered = true;

                    increasingSeq++;
                }
            }

            max = Math.max(max, increasingSeq);
        }

        if (!altered && max < items.length) {
            max++;
        }

        System.out.println(max);
    }
}
