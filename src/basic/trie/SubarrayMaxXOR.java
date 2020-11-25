package basic.trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubarrayMaxXOR {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("he_fight_in_ninjaworld.txt")));
        String input = br.readLine();

        String[] splitedInput = input.split(" ");

        ArrayList<Long> numbers = new ArrayList<>();

        for (String s : splitedInput) {
            numbers.add(Long.parseLong(s));
        }

        System.out.println("input size : " + numbers.size());

        long startOptimized = System.currentTimeMillis();
        long maxSubArrayXOROptimized = findMaxSubArrayXOROptimized(numbers);
        System.out.println(maxSubArrayXOROptimized + " took " + (System.currentTimeMillis() - startOptimized) + " ms");

        long startAdhoc = System.currentTimeMillis();

        long maxSubArrayXor = findMaxSubArrayXOR(numbers);
        System.out.println(maxSubArrayXor + " took " + (System.currentTimeMillis() - startAdhoc) + " ms");
    }

    private static long findMaxSubArrayXOROptimized(List<Long> numbers) {
        long maxNumber = numbers.stream().reduce(Long::max).get();
        int bitCount = (int) (Math.log10(maxNumber) / Math.log10(2)) + 1;
        long maxXOR = 0;

        long cumulativeXOR = 0;

        TrieNode root = new TrieNode(null);

        addToTrie(0, root, bitCount);

        for (long n : numbers) {
            cumulativeXOR ^= n;

            addToTrie(cumulativeXOR, root, bitCount);

            maxXOR = Math.max(maxXOR, findMaxXOR(cumulativeXOR, root, bitCount));
        }

        return maxXOR;
    }

    private static void addToTrie(long n, TrieNode currentNode, int trieLen) {
        int len;

        if (n == 0) {
            len = trieLen;
        } else {
            len = (int) (Math.log10(n) / Math.log10(2)) + 1;
        }

        int zeroNode = trieLen - len;

        while (zeroNode-- > 0) {
            currentNode = currentNode.addLeft((byte) 0);
        }

        long mask = 1L << (len - 1);

        while (mask > 0) {
            if ((mask & n) > 0) {
                currentNode = currentNode.addRight((byte) 1);
            } else {
                currentNode = currentNode.addLeft((byte) 0);
            }

            mask >>= 1;
        }
    }

    private static long findMaxXOR(long n, TrieNode currentNode, int trieLen) {
        long result = 0;

        int len = (int) (Math.log10(n) / Math.log10(2)) + 1;
        int zeroNode = trieLen - len;

        while (zeroNode-- > 0 && currentNode != null) {
            result <<= 1;

            if (currentNode.right != null) {
                result |= 1;
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
        }

        long mask = 1L << (len - 1);

        while (mask > 0 && currentNode != null) {
            result <<= 1;

            if ((mask & n) > 0) {
                if (currentNode.left != null) {
                    result |= 1;
                    currentNode = currentNode.left;
                } else {
                    currentNode = currentNode.right;
                }
            } else {
                if (currentNode.right != null) {
                    result |= 1;
                    currentNode = currentNode.right;
                } else {
                    currentNode = currentNode.left;
                }
            }

            mask >>= 1;
        }

        return result;
    }

    private static long findMaxSubArrayXOR(List<Long> numbers) {

        long result;
        long maxSubArrayXOR = numbers.get(0);

        for (int i = 0; i < numbers.size(); i++) {
            result = numbers.get(i);
            maxSubArrayXOR = Math.max(maxSubArrayXOR, result);

            for (int j = i + 1; j < numbers.size(); j++) {
                result = result ^ numbers.get(j);
                maxSubArrayXOR = Math.max(maxSubArrayXOR, result);

            }
        }

        return maxSubArrayXOR;
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
