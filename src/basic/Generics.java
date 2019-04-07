package basic;

import java.math.BigInteger;
import java.util.*;

public class Generics {
    public static void main(String[] args) {
        Map<String, String> testMap = new HashMap<>();
        testMap.putIfAbsent("1", "2");
        testMap.putIfAbsent("3", "4");

        BigInteger x = BigInteger.valueOf(1);

        Set<String> set1 = new HashSet<>();
        set1.add("test");
        set1.add("test2");

        Set<String> set2 = new HashSet<>();
        set2.add("test");
        set2.add("test3");

        List<String> test = Arrays.asList("a", "b", "c");

        System.out.println(test.contains(null));

        System.out.println(commonItemCount(set1, set2));

        Set<?> testx = new HashSet<>();

        X xx = new X();
        xx.w = 10;

        List<X> xList = new ArrayList<>();
        xList.add(xx);

        List<X> cXList = new ArrayList<>();
        cXList.add(xList.get(0));

        cXList.get(0).w = 20;


        System.out.println(xList.get(0).w);
//
//        List<String>[] items = new ArrayList<String>[12];
    }

    private static int commonItemCount(Set<?> set1, Set<?> set2) {
        int count = 0;
        for (Object o : set1) {
            if ((set2.contains(o))) {
                count++;
            }
        }

        return count;
    }

    private static class X {
        int w;
    }
}