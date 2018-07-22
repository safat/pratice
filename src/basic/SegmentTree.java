package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SegmentTree {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int q = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");

        int data[] = new int[n];

        for (int i = 0; i < input.length; i++) {
            data[i] = Integer.parseInt(input[i]);
        }

        List<Query> queryList = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            input = br.readLine().split(" ");

            Query query = new Query();

            query.type = Type.valueOf(input[0]);
            query.v1 = Integer.parseInt(input[1]);
            query.v2 = Integer.parseInt(input[2]);

            queryList.add(query);
        }

        int memSize = (int) (2 * Math.pow(2, Math.ceil(Math.log10(data.length) / Math.log10(2))));
        int segmentTree[] = new int[memSize];

        buildSegmentTree(data, segmentTree);

        StringBuilder output = new StringBuilder();

        for (Query query : queryList) {
            switch (query.type) {
                case u:
                    updateSegmentTree(data, query.v1 - 1, query.v2, segmentTree);
                    break;

                case q:
                    output.append(querySegmentTree(data, query.v1 - 1, query.v2 - 1, segmentTree)).append("\n");
                    break;

            }
        }

        System.out.print(output);
    }

    public static void buildSegmentTree(int[] data, int[] segmentTree) {
        segmentTreeBuildUtil(data, 1, 0, data.length - 1, segmentTree);
    }

    private static void segmentTreeBuildUtil(int[] data, int node, int start, int end, int[] segmentTree) {
        if (start == end) {
            segmentTree[node] = data[start];
            return;
        }

        int mid = (end + start) / 2;

        segmentTreeBuildUtil(data, 2 * node, start, mid, segmentTree);
        segmentTreeBuildUtil(data, 2 * node + 1, mid + 1, end, segmentTree);

        segmentTree[node] = Math.min(segmentTree[2 * node], segmentTree[2 * node + 1]);
    }

    public static int querySegmentTree(int[] data, int l, int h, int[] segmentTree) {
        return querySegmentTreeUtil(data, l, h, 0, data.length - 1, segmentTree, 1);
    }

    private static int querySegmentTreeUtil(int[] data, int l, int h, int start, int end, int[] segmentTree, int node) {
        if (l == start && h == end) {
            return segmentTree[node];
        }

        int m = (l + h) / 2;
        int mid = (start + end) / 2;

        if (l >= start && h <= mid) {
            return querySegmentTreeUtil(data, l, h, start, mid, segmentTree, node * 2);
        }

        if (l > mid && h <= end) {
            return querySegmentTreeUtil(data, l, h, mid + 1, end, segmentTree, node * 2 + 1);
        }

        return Math.min(querySegmentTreeUtil(data, l, mid, start, mid, segmentTree, node * 2),
                querySegmentTreeUtil(data, mid + 1, h, mid + 1, end, segmentTree, node * 2 + 1));
    }

    public static void updateSegmentTree(int[] data, int idx, int updatedValue, int[] segmentTree) {
        int nodeNum = 1;
        int start = 0, end = data.length - 1;

        while (true) {
            if (start == end && idx == start) {
                break;
            }

            int mid = (start + end) / 2;

            if (idx >= start && idx <= mid) {
                nodeNum = nodeNum * 2;
                end = mid;
            } else {
                start = mid + 1;
                nodeNum = nodeNum * 2 + 1;
            }
        }

        segmentTree[nodeNum] = updatedValue;
        data[idx] = updatedValue;

        while (nodeNum != 1) {
            int parent = nodeNum / 2;

            boolean isLeft = (parent * 2 == nodeNum);


            int leftChild, rightChild;

            if (isLeft) {
                leftChild = segmentTree[nodeNum];
                rightChild = segmentTree[nodeNum + 1];
            } else {
                leftChild = segmentTree[nodeNum - 1];
                rightChild = segmentTree[nodeNum];
            }

            segmentTree[parent] = Math.min(leftChild, rightChild);

            nodeNum /= 2;
        }

    }

    static class Query {
        Type type;
        int v1, v2;
    }

    enum Type {
        q, u
    }
}
