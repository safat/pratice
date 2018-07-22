package hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class CubeChallenge {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < n; i++) {
            BigInteger x = new BigInteger(br.readLine());
            BigInteger y = x.compareTo(BigInteger.valueOf(1)) > 0 ? x.subtract(BigInteger.valueOf(2)) : BigInteger.ZERO;
            BigInteger result = x.pow(3).subtract(y.pow(3));

            output.append(result).append("\n");
        }

        System.out.println(output);
    }
}
