package spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GSS1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] data = new int[n];

        String[] input = br.readLine().split(" ");

        for (int i = 0; i < data.length; i++) {
            data[i] = Integer.parseInt(input[i]);
        }

        int q = Integer.parseInt(br.readLine());

        int[] start = new int[q];
        int[] end = new int[q];

        for (int i = 0; i < q; i++) {
            input = br.readLine().split(" ");

            start[i] = Integer.parseInt(input[0]);
            end[i] = Integer.parseInt(input[1]);
        }

        Node[] segmentTree = buildSegmentTree(data);

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < q; i++) {
            output.append(rangeQuery(start[i], end[i], segmentTree, data)).append("\n");
        }

        System.out.print(output);
    }

    private static long rangeQuery(int start, int end, Node[] segmentTree, int[] data) {
        return queryUtil(1, start - 1, end - 1, segmentTree, 0, data.length - 1).maxSum;
    }

    private static Node queryUtil(int currentNode, int start, int end, Node[] segmentTree, int dataStart, int dataEnd) {
        if (start == dataStart && end == dataEnd) {
            return segmentTree[currentNode];
        }

        int mid = (dataStart + dataEnd) / 2;

        if (start <= mid && end <= mid) {
            return queryUtil(2 * currentNode, start, end, segmentTree, dataStart, mid);
        } else if (start > mid && end > mid) {
            return queryUtil(2 * currentNode + 1, start, end, segmentTree, mid + 1, dataEnd);
        }

        Node left = queryUtil(2 * currentNode, start, mid, segmentTree, dataStart, mid);
        Node right = queryUtil(2 * currentNode + 1, mid + 1, end, segmentTree, mid + 1, dataEnd);


        long prefixSum = max(left.prefixSum, left.sum, left.sum + right.prefixSum, left.sum + right.sum);

        long suffixSum = max(right.suffixSum, right.sum,
                right.sum + left.suffixSum, right.sum + left.sum);

        long sum = left.sum + right.sum;

        long maxSum = max(prefixSum, suffixSum, sum, left.suffixSum + right.prefixSum, left.maxSum, right.maxSum);

        return new Node(sum, prefixSum, suffixSum, maxSum);
    }

    private static Node[] buildSegmentTree(int[] data) {
        int size = 2 * (int) Math.pow(2, Math.ceil(Math.log10(data.length) / Math.log10(2)));

        Node[] segmentTree = new Node[size];

        segmentTreeBuildUtil(segmentTree, 1, 0, data.length - 1, data);

        return segmentTree;
    }

    private static void segmentTreeBuildUtil(Node[] segmentTree, int currentNode, int start, int end, int[] data) {
        if (start == end) {
            segmentTree[currentNode] = new Node(data[start], data[start], data[start], data[start]);
            return;
        }

        int mid = (start + end) / 2;

        // build left tree
        segmentTreeBuildUtil(segmentTree, 2 * currentNode, start, mid, data);

        // build right tree
        segmentTreeBuildUtil(segmentTree, 2 * currentNode + 1, mid + 1, end, data);

        Node left = segmentTree[2 * currentNode];
        Node right = segmentTree[2 * currentNode + 1];

        long prefixSum = max(left.prefixSum, left.sum, left.sum + right.prefixSum, left.sum + right.sum);

        long suffixSum = max(right.suffixSum, right.sum,
                right.sum + left.suffixSum, right.sum + left.sum);

        long sum = left.sum + right.sum;

        long maxSum = max(prefixSum, suffixSum, sum, left.suffixSum + right.prefixSum, left.maxSum, right.maxSum);

        Node cNode = new Node(sum, prefixSum, suffixSum, maxSum);

        segmentTree[currentNode] = cNode;
    }

    private static long max(Long... numbers) {
        Arrays.sort(numbers);

        return numbers[numbers.length - 1];
    }

    private static class Node {
        long sum;
        long prefixSum;
        long suffixSum;
        long maxSum;

        public Node(long sum, long prefixSum, long suffixSum, long maxSum) {
            this.sum = sum;
            this.prefixSum = prefixSum;
            this.suffixSum = suffixSum;
            this.maxSum = maxSum;
        }
    }
}
