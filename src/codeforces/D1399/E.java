package codeforces.D1399;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author muhossain
 * @since 2020-08-14
 */

public class E {

    static long total = 0;
    static Queue<EdgeWeight> priorityQueue = new PriorityQueue<>();

    public static void main(String[] args) {
        InputReader fs = new InputReader(System.in);

        int testCases = fs.nextInt();
        StringBuilder output = new StringBuilder();

        for (int t = 0; t < testCases; t++) {
            total = 0;

            Map<Edge, Long> weightMap = new HashMap<>();
            Map<Integer, List<Integer>> adjacencies = new HashMap<>();
            Map<Integer, Integer> pathCount = new HashMap<>();

            int n = fs.nextInt();
            long s = fs.nextLong();

            for (int i = 0; i < n - 1; i++) {
                int a = fs.nextInt();
                int b = fs.nextInt();
                long w = fs.nextLong();

                adjacencies.putIfAbsent(a, new ArrayList<>());
                adjacencies.putIfAbsent(b, new ArrayList<>());

                adjacencies.get(a).add(b);
                adjacencies.get(b).add(a);

                weightMap.put(new Edge(a, b), w);
                weightMap.put(new Edge(b, a), w);
            }

            Map<Integer, Boolean> visited = new HashMap<>();

            priorityQueue = new PriorityQueue<>();

            findPathCount(adjacencies, 1, -1, visited, pathCount, weightMap);


            long totalOperation = 0;

            while (total > s) {
                EdgeWeight eW = priorityQueue.poll();

                totalOperation++;

                total -= ((eW.weight + 1) / 2) * eW.frequency;
                eW.weight = eW.weight / 2;

                priorityQueue.add(eW);
            }

            output.append(totalOperation).append("\n");
        }

        System.out.print(output);
    }

    private static int findPathCount(Map<Integer, List<Integer>> adjacencies, int cNode, int parent,
                                     Map<Integer, Boolean> visited, Map<Integer, Integer> childCount,
                                     Map<Edge, Long> weightMap) {

        visited.put(cNode, true);

        if (adjacencies.get(cNode) == null || adjacencies.isEmpty()) {
            return 1;
        }

        int branch = 0;

        for (Integer node : adjacencies.get(cNode)) {
            if (visited.containsKey(node)) {
                continue;
            }

            branch += findPathCount(adjacencies, node, cNode, visited, childCount, weightMap);
        }

        childCount.put(cNode, branch == 0 ? 1 : branch);

        if (parent != -1) {
            long weight = weightMap.get(new Edge(cNode, parent));
            EdgeWeight ew = new EdgeWeight(weight, childCount.get(cNode));

            priorityQueue.add(ew);
            total += (ew.weight * ew.frequency);
        }

        return childCount.get(cNode);
    }

    static class EdgeWeight implements Comparable<EdgeWeight> {
        long weight;
        long frequency;

        public EdgeWeight(long weight, long frequency) {
            this.weight = weight;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(EdgeWeight o) {
            long diff2 = ((o.weight + 1) / 2) * o.frequency;
            long diff1 = ((this.weight + 1) / 2) * this.frequency;

            return Long.compare(diff2, diff1);
        }
    }

    static class Edge {
        int a;
        int b;

        public Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (a != edge.a) return false;
            return b == edge.b;

        }

        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            return result;
        }
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1 << 16];
        private int curChar;
        private int snumChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int snext() {
            if (snumChars == -1)
                throw new InputMismatchException();
            if (curChar >= snumChars) {
                curChar = 0;
                try {
                    snumChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (snumChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public long nextLong() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            boolean isSpaceChar(int ch);

        }

    }

}
