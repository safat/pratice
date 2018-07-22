package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String src = br.readLine();
        String dest = br.readLine();

        int srcRow = src.charAt(1);
        int srcCol = src.charAt(0);

        int destRow = dest.charAt(1);
        int destCol = dest.charAt(0);

        StringBuilder output = new StringBuilder();

        int distance = getDistance(srcRow, srcCol, destRow, destCol);
        int moveCount = 0;

        while (distance != 0) {
            moveCount++;

            String optimalMove;
            int minDistance;
            int rowDelta = 0;
            int colDelta = 0;

            // R
            minDistance = getDistance(srcRow, srcCol + 1, destRow, destCol);
            colDelta = 1;
            rowDelta = 0;
            optimalMove = "R";

            // L
            int distanceL = getDistance(srcRow, srcCol - 1, destRow, destCol);
            if (distanceL < minDistance) {
                colDelta = -1;
                rowDelta = 0;
                optimalMove = "L";
                minDistance = distanceL;
            }

            // U
            int distanceU = getDistance(srcRow + 1, srcCol, destRow, destCol);
            if (distanceU < minDistance) {
                rowDelta = 1;
                colDelta = 0;
                optimalMove = "U";
                minDistance = distanceU;
            }

            // D
            int distanceD = getDistance(srcRow - 1, srcCol, destRow, destCol);
            if (distanceD < minDistance) {
                rowDelta = -1;
                colDelta = 0;
                optimalMove = "D";
                minDistance = distanceD;
            }

            //RD
            int distanceRD = getDistance(srcRow - 1, srcCol + 1, destRow, destCol);
            if (distanceRD < minDistance) {
                rowDelta = -1;
                colDelta = 1;
                optimalMove = "RD";
                minDistance = distanceRD;
            }

            //RU
            int distanceRU = getDistance(srcRow + 1, srcCol + 1, destRow, destCol);
            if (distanceRU < minDistance) {
                rowDelta = 1;
                colDelta = 1;
                optimalMove = "RU";
                minDistance = distanceRU;
            }

            //LU
            int distanceLU = getDistance(srcRow + 1, srcCol - 1, destRow, destCol);
            if (distanceLU < minDistance) {
                rowDelta = 1;
                colDelta = -1;
                optimalMove = "LU";
                minDistance = distanceLU;
            }

            //LD
            int distanceLD = getDistance(srcRow - 1, srcCol - 1, destRow, destCol);
            if (distanceLD < minDistance) {
                rowDelta = -1;
                colDelta = -1;
                optimalMove = "LD";
                minDistance = distanceLD;
            }

            distance = minDistance;
            output.append(optimalMove).append("\n");

            srcRow += rowDelta;
            srcCol += colDelta;
        }

        System.out.println(moveCount);
        System.out.print(output);
    }

    private static int getDistance(int srcRow, int srcCol, int destRow, int destCol) {
        return Math.abs(srcRow - destRow) + Math.abs(srcCol - destCol);
    }

    /*
        String s = reader.readLine();
        String t = reader.readLine();
        int c = s.charAt(0) - t.charAt(0);
        int r = s.charAt(1) - t.charAt(1);

        System.out.println(Math.max(Math.abs(c), Math.abs(r)));

        String x;
        while (c != 0 || r != 0) {
            x = "";
            if (c > 0) {
                x += "L";
                c--;
            }
            if (c < 0) {
                x += "R";
                c++;
            }
            if (r > 0) {
                x += "D";
                r--;
            }
            if (r < 0) {
                x += "U";
                r++;
            }

            System.out.println(x);
        }
    */
}
