package leetcode;

public class L461 {

    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
        System.out.println(hammingDistance(10, 120123123));
    }
//
//    public static int hammingDistance(int a, int b) {
//        int hammingDistance = 0;
//
//        for (int i = 0; i < 32; i++) {
//            int lastBitA = a & 1;
//            int lastBitB = b & 1;
//
//            if (lastBitA != lastBitB) {
//                hammingDistance++;
//            }
//
//            a>>=1;
//            b>>=1;
//        }
//
//        return hammingDistance;
//    }

    public static int hammingDistance(int a, int b) {
        int hammingDistance = 0;
        int xor = a ^ b;

        for (int i = 0; i < 32; i++) {
            if ((xor & 1) == 1) {
                hammingDistance++;
            }

            xor>>=1;
        }

        return hammingDistance;
    }
}
