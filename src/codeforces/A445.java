package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A445 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        char[][] board = new char[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        if (n == 1 && m == 1) {
            board[0][0] = 'B';
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '-') {
                    continue;
                }

                Character ch;

                //up
                ch = getPlacement(board, i - 1, j);

                if (ch != null) {
                    board[i][j] = ch;
                    continue;
                }

                //left
                ch = getPlacement(board, i, j - 1);

                if (ch != null) {
                    board[i][j] = ch;
                    continue;
                }

                //right
                ch = getPlacement(board, i, j + 1);

                if (ch != null) {
                    board[i][j] = ch;
                    continue;
                }

                //down
                ch = getPlacement(board, i + 1, j);

                if (ch != null) {
                    board[i][j] = ch;
                    continue;
                }

                board[i][j] = 'B';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(String.valueOf(board[i][j]));
            }

            System.out.println();
        }
    }

    private static boolean validMove(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    private static Character getPlacement(char[][] board, int i, int j) {
        if (!validMove(i, j, board.length, board[0].length)) {
            return null;
        }

        char ch = board[i][j];

        if (ch == 'W') {
            return 'B';
        } else if (ch == 'B') {
            return 'W';
        } else if (ch == '.') {
            return 'B';
        }

        return null;
    }
}
