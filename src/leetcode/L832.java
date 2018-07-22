package leetcode;

public class L832 {
    public static void main(String[] args) {
        System.out.println(judgeCircle("LDU"));
        System.out.println(judgeCircle("LR"));
    }

//    public static boolean judgeCircle(String moves) {
//        int row = 0;
//        int col = 0;
//
//        for (char ch : moves.toCharArray()) {
//            switch (ch) {
//                case 'U':
//                    row--;
//                    break;
//
//                case 'D':
//                    row++;
//                    break;
//
//                case 'L':
//                    col--;
//                    break;
//
//                case 'R':
//                    col++;
//                    break;
//
//            }
//        }
//
//        return row == 0 && col == 0;
//
//    }

    public static boolean judgeCircle(String moves) {
        int[] hash = new int[26];

        for (char ch : moves.toCharArray()) {
            hash[ch - 'A']++;
        }

        return hash['L' - 'A'] == hash['R' - 'A'] && hash['U' - 'A'] == hash['D' - 'A'];
    }
}
