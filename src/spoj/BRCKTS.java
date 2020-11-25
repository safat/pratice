//package spoj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-03-14
 */

public class BRCKTS {

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder output = new StringBuilder();

        List<Test> testList = new ArrayList<>();

        for (int t = 1; t <= 10; t++) {
            int n = fs.nextInt();
            String input = fs.nextLine();
            int m = fs.nextInt();

            List<Integer> queries = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                queries.add(fs.nextInt());
            }

            testList.add(new Test(n, input, queries));
        }

        for (int t = 1; t <= testList.size(); t++) {
            output.append("Test ").append(t).append(":\n");

            Test test = testList.get(t - 1);
            String input = test.input;
            List<Integer> queryList = test.queryList;

            Pair[] segmentTree = buildSegmentTree(input.toCharArray());

            for (int q : queryList) {
                if (q == 0) {
                    output.append(segmentTree[1].left == 0 && segmentTree[1].right == 0 ? "YES" : "NO").append("\n");
                } else {
                    updateSegmentTree(segmentTree, q, input.length());
                }
            }
        }

        System.out.print(output);
    }

    private static void updateSegmentTree(Pair[] segmentTree, int position, int size) {
        updateSegmentTreeUtil(segmentTree, 1, position - 1, 0, size - 1);
    }

    private static void updateSegmentTreeUtil(Pair[] segmentTree, int nodeToUpdate, int position, int start, int end) {
        if (start == end) {
            Pair pair = segmentTree[nodeToUpdate];

            int tmp = pair.left;
            pair.left = pair.right;
            pair.right = tmp;

            return;
        }

        int mid = (start + end) / 2;

        if (position <= mid) {
            updateSegmentTreeUtil(segmentTree, 2 * nodeToUpdate, position, start, mid);
        } else {
            updateSegmentTreeUtil(segmentTree, 2 * nodeToUpdate + 1, position, mid + 1, end);
        }

        Pair leftPair = segmentTree[2 * nodeToUpdate];
        Pair rightPair = segmentTree[2 * nodeToUpdate + 1];

        int l = Math.max(leftPair.left - rightPair.right, 0) + rightPair.left;
        int r = Math.max(rightPair.right - leftPair.left, 0) + leftPair.right;

        segmentTree[nodeToUpdate] = new Pair(l, r);
    }

    public static Pair[] buildSegmentTree(char[] data) {
        int segmentTreeSize = (int) (2 * Math.pow(2, Math.ceil((Math.log10(data.length) / Math.log10(2)))));
        Pair[] segmentTree = new Pair[segmentTreeSize];

        segmentTreeBuildUtil(1, 0, data.length - 1, data, segmentTree);

        return segmentTree;
    }

    public static void segmentTreeBuildUtil(int node, int start, int end, char[] data, Pair[] segmentTree) {
        if (start == end) {
            if (data[start] == '(') {
                segmentTree[node] = new Pair(1, 0);
            } else {
                segmentTree[node] = new Pair(0, 1);
            }
            return;
        }

        int mid = (start + end) / 2;

        segmentTreeBuildUtil(2 * node, start, mid, data, segmentTree);
        segmentTreeBuildUtil(2 * node + 1, mid + 1, end, data, segmentTree);

        Pair leftPair = segmentTree[2 * node];
        Pair rightPair = segmentTree[2 * node + 1];

        int l = Math.max(leftPair.left - rightPair.right, 0) + rightPair.left;
        int r = Math.max(rightPair.right - leftPair.left, 0) + leftPair.right;

        segmentTree[node] = new Pair(l, r);
    }


    static class FastScanner {
        private BufferedReader reader = null;
        private StringTokenizer tokenizer = null;

        public FastScanner(InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        public String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static class Pair {
        int left;
        int right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    private static class Test {
        int n;
        String input;
        List<Integer> queryList = new ArrayList<>();

        public Test(int n, String input, List<Integer> queryList) {
            this.n = n;
            this.input = input;
            this.queryList = queryList;
        }
    }

}
