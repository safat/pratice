package spoj;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author safat
 * @since 2020-05-03
 */

public class SolveLock {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int totalRules = 5;
        Integer[] input = {9, 6, 4, 2, 8, 6, 1, 4, 7, 1, 8, 9, 5, 2, 3};
        Set<Integer> numberSet = new HashSet<>(Arrays.asList(input));
        List<Integer> numbers = new ArrayList<>(numberSet);
        Triple answer = null;

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                for (int k = 0; k < numbers.size(); k++) {
                    if (i != j && j != k) {
                        List<Integer> currentPattern = Arrays.asList(numbers.get(i), numbers.get(j), numbers.get(k));

                        boolean expectedPattern = true;

                        for (int r = 1; r <= totalRules; r++) {
                            if (!validateRule(currentPattern, r)) {
                                expectedPattern = false;
                                break;
                            }
                        }

                        if (expectedPattern) {
                            answer = new Triple(currentPattern.get(0), currentPattern.get(1), currentPattern.get(2));
                        }

                    }
                }
            }
        }

        System.out.println(answer);
        System.out.println("Execution time in ms: " + (System.currentTimeMillis() - start));
    }

    private static boolean validateRule(List<Integer> pivotItems, int r) {
        switch (r) {
            case 1:
                List<Integer> ruleItems = Arrays.asList(9, 6, 4);
                List<Integer> matchIndexes = findMatchIndexes(pivotItems, ruleItems);

                if (matchIndexes.size() != 2) {
                    return false;
                }

                return !ruleItems.get(matchIndexes.get(0)).equals(pivotItems.get(matchIndexes.get(0)))
                        && !ruleItems.get(matchIndexes.get(1)).equals(pivotItems.get(matchIndexes.get(1)));
            case 2:
                ruleItems = Arrays.asList(2, 8, 6);
                matchIndexes = findMatchIndexes(pivotItems, ruleItems);

                return matchIndexes.size() == 1
                        && !ruleItems.get(matchIndexes.get(0)).equals(pivotItems.get(matchIndexes.get(0)));

            case 3:
                ruleItems = Arrays.asList(1, 4, 7);
                matchIndexes = findMatchIndexes(pivotItems, ruleItems);

                return matchIndexes.size() == 1
                        && !ruleItems.get(matchIndexes.get(0)).equals(pivotItems.get(matchIndexes.get(0)));

            case 4:
                ruleItems = Arrays.asList(1, 8, 9);
                matchIndexes = findMatchIndexes(pivotItems, ruleItems);

                return matchIndexes.size() == 1
                        && ruleItems.get(matchIndexes.get(0)).equals(pivotItems.get(matchIndexes.get(0)));

            case 5:
                ruleItems = Arrays.asList(5, 2, 3);
                matchIndexes = findMatchIndexes(pivotItems, ruleItems);

                return matchIndexes.size() == 0;

        }

        throw new IllegalStateException("Undefined rule");
    }

    private static List<Integer> findMatchIndexes(List<Integer> pivotItems, List<Integer> ruleItems) {
        assert ruleItems.size() == pivotItems.size() : "game rule";

        List<Integer> matchIndexes = new ArrayList<>();

        for (int i = 0; i < ruleItems.size(); i++) {
            if (ruleItems.contains(pivotItems.get(i))) {
                matchIndexes.add(i);
            }
        }

        return matchIndexes;
    }

    private static class Triple {
        int x, y, z;

        public Triple(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            return "Triple{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }
    }
}
