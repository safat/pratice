package basic.trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MaxXOR {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("he_fight_in_ninjaworld.txt")));

        String input = br.readLine();
        String[] splitedInput = input.split(" ");

        List<Long> data1 = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            String s = splitedInput[i];
            data1.add(Long.parseLong(s));
        }

//        data1 = Arrays.asList(120126760000L, 1L);

        System.out.println("working with input size of : " + data1.size());

        long adhocStart = System.currentTimeMillis();
        long resultAdhoc = maxXORAdhoc(data1);

        System.out.println("adhoc: " + resultAdhoc + " took " + (System.currentTimeMillis() - adhocStart) + " ms");

        long optimizedStart = System.currentTimeMillis();

        long maxNumber = data1.stream().reduce(Long::max).get();

        long trieLen = (long) (Math.log10(maxNumber) / Math.log10(2)) + 1;

        TrieNode root = new TrieNode(null);
        long resultOptimized = 0;

        for (long n : data1) {
            TrieNode cNode = root;
            long zeroNode = trieLen - ((long) (Math.log10(n) / Math.log10(2)) + 1);

            for (int j = 0; j < zeroNode; j++) {
                cNode = cNode.addLeft((byte) 0);
            }

            long mask = 1L << (trieLen - zeroNode - 1);

            while (mask != 0) {
                if ((mask & n) > 0) {
                    cNode = cNode.addRight((byte) 1);
                } else {
                    cNode = cNode.addLeft((byte) 0);
                }

                mask >>= 1;
            }

            cNode = root;

            long result = 0;

            for (int j = 0; j < zeroNode; j++) {
                result <<= 1;

                if (cNode.right != null) {
                    cNode = cNode.right;
                    result = result | 1;
                } else {
                    cNode = cNode.left;
                }
            }

            mask = 1L << (trieLen - zeroNode - 1);

            while (mask != 0 && cNode != null) {

                result <<= 1;

                if ((mask & n) > 0) {
                    if (cNode.left != null) {
                        cNode = cNode.left;
                        result = result | 1;
                    } else {
                        cNode = cNode.right;
                    }
                } else {
                    if (cNode.right != null) {
                        cNode = cNode.right;
                        result = result | 1;
                    } else {
                        cNode = cNode.left;
                    }
                }

                mask >>= 1;
            }

            resultOptimized = Math.max(result, resultOptimized);
        }

        System.out.println("trie: " + resultOptimized + " took " + (System.currentTimeMillis() - optimizedStart) + " ms");
    }

    private static long maxXORAdhoc(List<Long> data) {
        long result = 0;

        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                result = Math.max(result, data.get(i) ^ data.get(j));
            }
        }

        return result;
    }

    static class TrieNode {
        Byte value;
        TrieNode left;
        TrieNode right;

        public TrieNode(Byte value) {
            this.value = value;
        }

        public TrieNode addLeft(Byte value) {
            if (left == null) {
                left = new TrieNode(value);
            }

            return left;
        }

        public TrieNode addRight(Byte value) {
            if (right == null) {
                right = new TrieNode(value);
            }

            return right;
        }
    }
}
