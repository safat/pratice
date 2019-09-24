package basic.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueen {
    private static int N = 8;

    public static void main(String[] args) {
        int[][] board = new int[N + 1][N + 1];
        boolean[][] visited = new boolean[N + 1][N + 1];

        List<List<Index>> result = new ArrayList<>();

        solve(board, 1, visited, result, new ArrayList<Index>());
    }

    private static void solve(int[][] board, int currentCol, boolean[][] visited, List<List<Index>> results,
                              ArrayList<Index> indices) {

        if (currentCol == N + 1) {
            System.out.println("Found A Solution");
            results.add(copy(indices));
        }

        for (int i = 1; i <= N; i++) {
            visited[i][currentCol] = true;

            if (isSafe(visited, currentCol)) {
                indices.add(new Index(i, currentCol));
                solve(board, currentCol + 1, visited, results, indices);
            }

            visited[i][currentCol] = false;
        }
    }

    private static List<Index> copy(ArrayList<Index> indices) {
        return new ArrayList<>(indices);
    }

    private static boolean isSafe(boolean[][] visited, int currentCol) {
        for (int i = 1; i <= N; i++) {
            for (int j = currentCol; j >= 1; j--) {
                if (visited[i][currentCol]) {
                    // check same row
                    for (int k = 1; k < j; k++) {
                        if (visited[i][k]) {
                            return false;
                        }
                    }


                    // upper left diagonal
                    int upperLeftX = i;
                    int upperLeftY = currentCol;

                    while (upperLeftX >= 1 && upperLeftY >= 1) {
                        if (visited[upperLeftX][upperLeftY]) {
                            return false;
                        }

                        upperLeftX--;
                        upperLeftY--;
                    }

                    int lowerLeftX = i;
                    int lowerLeftY = currentCol;

                    // upper right diagonally

                    while (lowerLeftX >= 1 && lowerLeftY >= 1) {
                        if (visited[lowerLeftX][lowerLeftX]) {
                            return false;
                        }

                        lowerLeftX--;
                        lowerLeftY++;
                    }

                }
            }
        }

        return true;
    }

    private static class Index {
        int x;
        int y;

        public Index(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
