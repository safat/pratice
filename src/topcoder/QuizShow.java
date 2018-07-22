package topcoder;


public class QuizShow {
    public int wager(int[] scores, int wager1, int wager2) {
        int bestScore = 0;
        int bestW = 0;

        for (int i = 0; i <= scores[0]; i++) {
            int score = 0;

            for (int mask = 0; mask < 8; mask++) {
                int s1 = ((mask & 1) > 0 ? 1 : -1) * i + scores[0];
                int s2 = ((mask & 2) > 0 ? 1 : -1) * wager1 + scores[1];
                int s3 = ((mask & 4) > 0 ? 1 : -1) * wager2 + scores[2];

                if (s1 > s2 && s1 > s3) {
                    score++;
                }
            }

            if (score > bestScore) {
                bestScore = score;
                bestW = i;
            }
        }

        return bestW;
    }
}