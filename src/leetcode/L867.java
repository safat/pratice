package leetcode;

public class L867 {

    public static void main(String[] args) {

    }

    public int[][] transpose(int[][] a) {
        int transposed[][] = new int[a[0].length][a.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                transposed[j][i] = a[i][j];
            }
        }

        return transposed;
    }
}
