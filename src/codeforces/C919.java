package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class C919 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);

        char[][] grid = new char[n][m];

        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        List<Integer> contSeats = findContinuousSeats(grid, k);
        int totalCombination = 0;

        for (int i = 0; i < contSeats.size(); i++) {
            int s = contSeats.get(i);

            if (s - k + 1 > 0) {
                totalCombination += (s - k + 1);
            }
        }

        System.out.println(k == 1 ? totalCombination / 2 : totalCombination);
    }

    private static List<Integer> findContinuousSeats(char[][] grid, int k) {
        List<Integer> contSeats = new ArrayList<>();

        // search row wide
        for (int i = 0; i < grid.length; i++) {
            int contSeat = 0;

            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '.') {
                    contSeat++;

                    if (j == grid[0].length - 1 && contSeat >= k) {
                        contSeats.add(contSeat);
                    }
                } else {
                    if (contSeat >= k) {
                        contSeats.add(contSeat);
                    }

                    contSeat = 0;
                }
            }
        }

        // search column wide
        for (int i = 0; i < grid[0].length; i++) {
            int contSeat = 0;

            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] == '.') {
                    contSeat++;

                    if (j == grid.length - 1 && contSeat >= k) {
                        contSeats.add(contSeat);
                    }
                } else {
                    if (contSeat >= k) {
                        contSeats.add(contSeat);
                    }

                    contSeat = 0;
                }
            }
        }


        return contSeats;
    }
}
