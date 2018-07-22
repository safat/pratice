package hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Collections;
import java.util.PriorityQueue;

public class MonkAndMultiplication {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");

        StringBuilder output = new StringBuilder();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (String num : input) {
            priorityQueue.add(Integer.parseInt(num));

            if (priorityQueue.size() < 3) {
                output.append("-1");
            } else {
                Integer top1 = priorityQueue.poll();
                Integer top2 = priorityQueue.poll();
                Integer top3 = priorityQueue.poll();

                output.append(BigInteger.valueOf(top1).multiply(BigInteger.valueOf(top2))
                        .multiply(BigInteger.valueOf(top3)).toString());

                priorityQueue.add(top1);
                priorityQueue.add(top2);
                priorityQueue.add(top3);
            }

            output.append("\n");
        }

        System.out.println(output);
    }
}
