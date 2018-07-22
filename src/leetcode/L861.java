package leetcode;

public class L861 {

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0}
        };

        int score = new L861().matrixScore(matrix);

        System.out.println(score);
    }

    public int matrixScore(int[][] a) {
        int row = a.length;
        int col = a[0].length;

        int result = (1 << (col - 1)) * row;

        for (int i = 1; i < col; i++) {
            int currentOneCount = 0;

            for (int j = 0; j < row; j++) {
                if (a[j][i] == a[j][0]) {
                    currentOneCount++;
                }
            }

            int onesCount = Math.max(currentOneCount, row - currentOneCount);

            result += ((1 << (col - i - 1)) * onesCount);
        }

        return result;
    }
}

