package basic;


import java.util.ArrayList;
import java.util.List;

public class SegmentTreeOfAnd {
    public static void main(String[] args) {
        int[] items = {1, 3, 5, 7, 9, 11};

        int[] segmentTree = buildSegmentTree(items);

        List<Range> rangeList = new ArrayList<>();
        rangeList.add(new Range(1, 3));
        rangeList.add(new Range(1, 4));
        rangeList.add(new Range(3, 4));
        rangeList.add(new Range(1, 6));

        for (Range range : rangeList) {
            System.out.println(range + ": " + query(range.l, range.r, items.length, segmentTree));
        }
    }

    private static int query(int l, int r, int dataLength, int[] segmentTree) {
        return queryUtil(1, l - 1, r - 1, 0, dataLength - 1, segmentTree);
    }

    private static int queryUtil(int node, int l, int r, int dataStart, int dataEnd, int[] segmentTree) {
        if (l == dataStart && r == dataEnd) {
            return segmentTree[node];
        }

        int mid = (dataStart + dataEnd) / 2;

        if (l >= dataStart && r <= mid) {
            return queryUtil(2 * node, l, r, dataStart, mid, segmentTree);
        }

        if (l > mid && r <= dataEnd) {
            return queryUtil(2 * node + 1, l, r, mid + 1, dataEnd, segmentTree);
        }

        return queryUtil(2 * node, l, mid, dataStart, mid, segmentTree) &
                queryUtil(2 * node + 1, mid + 1, r, mid + 1, dataEnd, segmentTree);
    }

    public static int[] buildSegmentTree(int[] data) {
        int segmentTreeSize = (int) (2 * Math.pow(2, Math.ceil((Math.log10(data.length) / Math.log10(2)))));
        int[] segmentTree = new int[segmentTreeSize];

        segmentTreeBuildUtil(1, 0, data.length - 1, data, segmentTree);

        return segmentTree;
    }

    public static void segmentTreeBuildUtil(int node, int start, int end, int[] data, int[] segmentTree) {
        if (start == end) {
            segmentTree[node] = data[start];
            return;
        }

        int mid = (start + end) / 2;

        segmentTreeBuildUtil(2 * node, start, mid, data, segmentTree);
        segmentTreeBuildUtil(2 * node + 1, mid + 1, end, data, segmentTree);

        segmentTree[node] = segmentTree[2 * node] & segmentTree[2 * node + 1];
    }

    private static class Range {
        int l, r;

        public Range(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public String toString() {
            return "Range{" +
                    "l=" + l +
                    ", r=" + r +
                    '}';
        }
    }
}
