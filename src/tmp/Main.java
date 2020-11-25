package tmp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {

    Map<String, Integer> map = new HashMap<>();
    static int k = 1;
    static int n = 10;

    public static void main(String[] args) throws IOException {
        StringBuilder output = new StringBuilder("10 1\n");

        int node = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        boolean allGood = false;

        while (true) {
            int cNode = queue.poll();
            for (int j = 1; j <= k; j++) {
                output.append(cNode + " " + (++node) + "\n");
                queue.add(node);

                if (node == n) {
                    allGood = true;
                    break;
                }
            }

            if (allGood) {
                break;
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"));
        writer.write(String.valueOf(output));
        writer.close();
    }
}
