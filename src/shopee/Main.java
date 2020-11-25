package shopee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author muhossain
 * @since 2020-07-25
 */

public class Main {
    public static void main(String[] args) {
        FastReader fs = new FastReader();
        int q = fs.nextInt();
        int n = fs.nextInt();

        int[] parent = new int[n + 1];
        int[] size = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            size[i] = 1;
            parent[i] = i;
        }

        int ccSize = n;

        Stack<Operation> operationStack = new Stack<>();

        StringBuilder output = new StringBuilder();

        Map<Integer, Map<Integer, Integer>> adjacencyMap = new HashMap<>();

        for (int i = 1; i <= q; i++) {
            String[] input = fs.nextLine().split(" ");

            Operation operation;

            if ("PUSH".equals(input[0])) {
                operation = new Operation(OP.valueOf(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]));
            } else {
                operation = new Operation(OP.valueOf(input[0]));
            }

            if (operation.op == OP.PUSH) {
                operationStack.push(operation);

                int u = operation.u;
                int v = operation.v;

                int pU = findParent(u, parent, size, true);
                int pV = findParent(v, parent, size, true);

                if (pU != pV) {
                    unionNodes(u, v, parent, size);
                    ccSize--;
                }

                output.append(ccSize).append("\n");

                adjacencyMap.putIfAbsent(u, new HashMap<>());
                adjacencyMap.putIfAbsent(v, new HashMap<>());

                int uToV = adjacencyMap.get(u).getOrDefault(v, 0);
                adjacencyMap.get(u).put(v, uToV + 1);

                int vToU = adjacencyMap.get(v).getOrDefault(u, 0);
                adjacencyMap.get(v).put(u, vToU + 1);

            } else if (operation.op == OP.POP) {
                Operation prevOp = operationStack.pop();

                int u = prevOp.u;
                int v = prevOp.v;

                int uToV = adjacencyMap.get(u).getOrDefault(v, 0);
                adjacencyMap.get(u).put(v, uToV - 1);

                if (adjacencyMap.get(u).get(v) <= 0) {
                    adjacencyMap.get(u).remove(v);
                }

                int vToU = adjacencyMap.get(v).getOrDefault(u, 0);
                adjacencyMap.get(v).put(u, vToU - 1);

                if (adjacencyMap.get(v).get(u) <= 0) {
                    adjacencyMap.get(v).remove(u);
                }

                int uTv = adjacencyMap.get(u).getOrDefault(v, 0);

                if (uTv > 0) {
                    output.append(ccSize).append("\n");
                } else {
                    if (adjacencyMap.get(u).size() == 0) {
                        parent[u] = u;
                    } else {
                        Iterator<Integer> it = adjacencyMap.get(u).keySet().iterator();
                        int next = it.next();
                        parent[u] = parent[next];

                    }

                    if (adjacencyMap.get(v).size() == 0) {
                        parent[v] = v;
                    } else {
                        Iterator<Integer> it = adjacencyMap.get(v).keySet().iterator();
                        parent[v] = parent[it.next()];
                    }
                }

                int pU = findParent(u, parent, size, false);
                int pV = findParent(v, parent, size, false);

                if (pU != pV) {
                    size[u]--;
                    size[v]--;

                    ccSize++;
                }

                output.append(ccSize).append("\n");
            }
        }

        System.out.print(output);
    }

    private static int findParent(int node, int[] parent, int[] size, boolean add) {
        if (parent[parent[node]] != parent[node]) {
            parent[node] = findParent(parent[node], parent, size, add);

            if (add) {
                size[node] = Math.max(size[node], size[parent[node]]);
            } else {
                size[node] = Math.min(size[node], size[parent[node]]);
            }
        }
        return parent[node];
    }

    private static void unionNodes(int u, int v, int[] parent, int[] size) {
        int parentU = findParent(u, parent, size, true);
        int parentV = findParent(v, parent, size, true);

        if (parentU == parentV) {
            return;
        }
        if (size[parentU] >= size[parentV]) {
            parent[parentV] = parentU;
        } else {
            parent[parentU] = parentV;
        }

        size[parentV] += size[parentU];
        size[parentU] = size[parentV];
    }

    private static class Operation {
        OP op;
        int u;
        int v;

        public Operation(OP op, int u, int v) {
            this.op = op;
            this.u = u;
            this.v = v;
        }

        public Operation(OP op) {
            this.op = op;
        }
    }

    enum OP {
        PUSH,
        POP
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public static FastReader getFileReader(String fileName) throws FileNotFoundException {
            return new FastReader(new InputStreamReader(new FileInputStream(new File(fileName))));
        }

        public static FastReader getDefaultReader() throws FileNotFoundException {
            return new FastReader();
        }

        public FastReader(InputStreamReader inputStreamReader) {
            br = new BufferedReader(inputStreamReader);
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
