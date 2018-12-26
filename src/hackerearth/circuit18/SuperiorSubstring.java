package hackerearth.circuit18;

public class SuperiorSubstring {

    public static void main(String[] args) {
        System.out.println(superiorSubStringLen("ababbbacbcbcca"));
    }

    public static int superiorSubStringLen(String str) {
        boolean[][] covered = new boolean['z' + 1][str.length() + 1];
        int[] coveredLen = new int['z' + 1];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < str.length(); i++) {
            int ch = str.charAt(i);

            covered[ch][i] = true;

            if (i > 1 && !covered[ch][i - 1] && covered[ch][i - 2]) {
                covered[ch][i - 1] = true;
                coveredLen[ch]++;
            } else {
                covered[ch][i + 1] = true;
            }

            for (char c = 'a'; c <= 'z'; c++) {
                if (!covered[c][i] && (i > 0 && !covered[c][i - 1])) {
                    coveredLen[c] = 0;
                } else if (covered[c][i]) {
                    coveredLen[c]++;
                    max = Math.max(max, coveredLen[c]);
                }
            }
        }

        return Math.min(max + 1, str.length());
    }
}
