package basic;

import java.util.*;

public class Generics {
    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        set1.add("test");
        set1.add("test2");

        Set<String> set2 = new HashSet<>();
        set2.add("test");
        set2.add("test3");

        System.out.println(commonItemCount(set1, set2));

        Set<?> testx = new HashSet<>();

        List<String> test = new ArrayList<>();
        test.toArray();
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
}