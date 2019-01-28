package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B1011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        int[] supplyFreq = new int[101];

        for (int i = 0; i < input.length; i++) {
            int s = Integer.parseInt(input[i]);
            supplyFreq[s]++;
        }

        Arrays.sort(supplyFreq);
        int[] takenSupplies = new int[n];
        int i = takenSupplies.length - 1;
        int j = 100;

        while (i >= 0 && j >= 0) {
            if (supplyFreq[j] > 0) {
                takenSupplies[i] = supplyFreq[j];
                i--;
            }

            j--;
        }

        int min = takenSupplies[0];
        int max = takenSupplies[takenSupplies.length-1];
        int result = min;


        for (i = min + 1; i <= max; i++) {
            int[] suppliesTakenBk = new int[takenSupplies.length];
            System.arraycopy(takenSupplies, 0, suppliesTakenBk, 0, takenSupplies.length);

            boolean canUpdate = false;

            for (j = 0; j < suppliesTakenBk.length - 1; j++) {

                canUpdate = true;

                for (int k = suppliesTakenBk.length - 1; k > j; k--) {
                    if (suppliesTakenBk[j] < i && (suppliesTakenBk[k] - i) >= i) {
                        suppliesTakenBk[j] = i;
                        suppliesTakenBk[k] -= i;
                        break;
                    }
                }

                if (suppliesTakenBk[j] < i) {
                    canUpdate = false;
                    break;
                }
            }

            if (!canUpdate) {
                result = i - 1;
                break;
            } else {
                result = i;
            }
        }

        System.out.println(result);
    }
}
