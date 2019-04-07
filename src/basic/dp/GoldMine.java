package basic.dp;

import java.util.Arrays;

public class GoldMine {
    public static void main(String[] args) {
        int[][] mine = new int[][]{{10, 33, 13, 15},
                {22, 21, 4, 1},
                {5, 0, 2, 3},
                {0, 6, 14, 2}};

        int[][] dp = new int[mine.length][mine.length];

        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        int maxMine = findMaxMine(1, 0, mine, dp);

        System.out.println(maxMine);
    }

    private static int findMaxMine(int i, int j, int[][] mine, int[][] dp) {
        if (i < 0 || j < 0 || i == mine.length || j == mine[0].length) {
            return 0;
        }

        if (dp[i][j] > 0) {
            return dp[i][j];
        }

        return (dp[i][j] = mine[i][j] + maxX(findMaxMine(i - 1, j + 1, mine, dp),
                findMaxMine(i, j + 1, mine, dp), findMaxMine(i + 1, j + 1, mine, dp)));
    }

    private static int maxX(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }
}
