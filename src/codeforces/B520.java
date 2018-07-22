package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class B520 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        Map<Integer, Integer> distanceMemorizationMap = new HashMap<>();
        Map<Integer, Boolean> visitedMap = new HashMap<>();

        int distance = dfs(n, m, 0, distanceMemorizationMap, visitedMap);

        System.out.println(distance);
    }

    private static int dfs(int source, int target, int distance, Map<Integer, Integer> distanceMemorizationMap,
                           Map<Integer, Boolean> visitedMap) {

        if (source >= target) {
            return distance + (source - target);
        }

//        if (source == 1 && target != 2) {
//            return Integer.MAX_VALUE;
//        }

        if (source <= 0) {
            return Integer.MAX_VALUE;
        }

        if (distanceMemorizationMap.containsKey(source)) {
            return distance + distanceMemorizationMap.get(source);
        }

        if (visitedMap.containsKey(source)) {
            return Integer.MAX_VALUE;
        }

        visitedMap.put(source, true);

        int left = dfs(source - 1, target, distance + 1, distanceMemorizationMap, visitedMap);
        int right = dfs(source * 2, target, distance + 1, distanceMemorizationMap, visitedMap);

        int distanceX = Math.min(left, right);

        distanceMemorizationMap.put(source, distanceX - distance);

        return distanceX;
    }
}
