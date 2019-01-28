package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class C978 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();

        String[] input = br.readLine().split(" ");

        long[] rooms = new long[input.length];
        rooms[0] = Long.parseLong(input[0]);


        for (int i = 1; i < input.length; i++) {
            rooms[i] = Long.parseLong(input[i]) + rooms[i - 1];
        }

        input = br.readLine().split(" ");

        long[] letters = new long[input.length];

        for (int i = 0; i < letters.length; i++) {
            letters[i] = Long.parseLong(input[i]);
        }

        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < letters.length; i++) {
            int position = Arrays.binarySearch(rooms, letters[i]);

            if (position < 0) {
                position = Math.abs(position) - 1;
            }

            long letterBox = letters[i];

            if (position > 0) {
                letterBox = letters[i] - rooms[position - 1];
            }

            output.append(position + 1).append(" ").append(letterBox).append("\n");
        }

        System.out.print(output);
    }
}
