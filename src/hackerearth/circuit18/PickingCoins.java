package hackerearth.circuit18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PickingCoins {
    static class Data {
        BigInteger n, k;

        public Data(BigInteger n, BigInteger k) {
            this.n = n;
            this.k = k;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        List<Data> inputList = new ArrayList<>();

        for (int i = 0; i < t; i++) {
            String[] inputMeta = br.readLine().split(" ");
            BigInteger n = new BigInteger(inputMeta[0]);
            BigInteger k = new BigInteger(inputMeta[1]);
            inputList.add(new Data(n, k));
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < inputList.size(); i++) {
            Data data = inputList.get(i);
            output.append(aWins(data.n, data.k) ? "Alice" : "Bob").append("\n");
        }

        System.out.println(output);
    }

    private static boolean aWins(BigInteger n, BigInteger k) {
        if (k.equals(BigInteger.ONE)) {
            return !n.mod(BigInteger.TWO).equals(BigInteger.ZERO);
        }

        for (int i = 1; i < 60; i++) {
            BigInteger c = k.pow(i);

            n = n.subtract(c);

            if (n.compareTo(BigInteger.ZERO) < 0) {
                return false;
            }

            n = n.subtract(c);

            if (n.compareTo(BigInteger.ZERO) < 0) {
                return true;
            }
        }

        throw new IllegalStateException();
    }
}
