package basic.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoiceOfArea {
    public static void main(String[] args) {
        int a = 21;
        int b = 12;

        Option x = new Option(7, 7);
        Option y = new Option(-4, -10);
        Option z = new Option(-20, 5);

        List<Option> options = Arrays.asList(x, y, z);

        int maxSurvival = Integer.MIN_VALUE;

        Map<String, Integer> memoization = new HashMap<>();
        long start = System.currentTimeMillis();

        for (int i = 0; i < options.size(); i++) {
            maxSurvival = Math.max(maxSurvival,
                    findSurvival(i, options, a + options.get(i).deltaA, b + options.get(i).deltaB, 1, memoization));
        }

        System.out.println(maxSurvival + " took: " + (System.currentTimeMillis() - start) + " milli second");
    }

    private static int findSurvival(int currentOption, List<Option> options, int a, int b, int survival, Map<String, Integer> memoization) {
        String key = currentOption + ":" + a + ":" + "b";

        if (memoization.containsKey(key)) {
            return memoization.get(key);
        }

        if (a <= 0 || b <= 0) {
            return survival - 1;
        }

        int maxSurvival = Integer.MIN_VALUE;

        for (int i = 0; i < options.size(); i++) {
            if (i == currentOption) {
                continue;
            }

            maxSurvival = Math.max(maxSurvival,
                    findSurvival(i, options, a + options.get(i).deltaA, b + options.get(i).deltaB, survival + 1, memoization));
        }

        memoization.put(key, maxSurvival);

        return maxSurvival;
    }

    static class Option {
        int deltaA;
        int deltaB;

        public Option(int deltaA, int deltaB) {
            this.deltaA = deltaA;
            this.deltaB = deltaB;
        }
    }
}
