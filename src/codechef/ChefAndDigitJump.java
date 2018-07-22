package codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChefAndDigitJump {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(findShortestJump(br.readLine()));
    }

    private static int findShortestJump(String digits) {
        Map<Character, List<Integer>> characterToIndexMap = new HashMap<>();

        char[] digitArray = digits.toCharArray();

        for (int i = 0; i < digitArray.length; i++) {
            char ch = digitArray[i];
            List<Integer> indexList = characterToIndexMap.get(ch);

            if (indexList == null) {
                indexList = new ArrayList<>();
            }

            indexList.add(i);

            characterToIndexMap.put(ch, indexList);
        }

        int[] distance = new int[digits.length()];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int index : characterToIndexMap.get(digitArray[digitArray.length - 1])) {
            distance[index] = 1;
        }

        distance[digitArray.length - 1] = 0;

        if (distance[0] == 0) {
            return 1;
        }

        for (int i = digitArray.length - 2; i >= 0; i--) {
            int rightDistance = distance[i + 1];
            int leftDistance = (i - 1) > 0 ? distance[i - 1] : Integer.MAX_VALUE;

            int min = Math.min(rightDistance, leftDistance);

            List<Integer> indexList = characterToIndexMap.get(digitArray[i]);

            for (int index : indexList) {
                min = Math.min(distance[index], min);
            }

            distance[i] = min + 1;

            int c = 1;
            for (int k = i + 1; k < digitArray.length; k++) {
                if (distance[k] > (distance[i] + c)) {
                    distance[k] = (distance[i] + c);
                } else {
                    break;
                }

                c++;
            }
        }

//        for (int i = 0; i < digitArray.length; i++) {
//            System.out.println(digitArray[i] + " : " + distance[i]);
//        }

        return distance[0];
    }
}
