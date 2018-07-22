package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllValidIp {
    public static void main(String[] args) {
        String input = "1211121";

        List<String> validIps = findAllValidIp(input);

        System.out.println(validIps);
    }

    private static List<String> findAllValidIp(String input) {
        if (input.length() < 4) {
            return Collections.emptyList();
        }

        List<String> validIps = new ArrayList<>();

        for (int i = 1; i < input.length() - 2; i++) {
            for (int j = i + 1; j < input.length() - 1; j++) {
                for (int k = j + 1; k < input.length(); k++) {
                    String block1 = input.substring(0, i);
                    String block2 = input.substring(i, j);
                    String block3 = input.substring(j, k);
                    String block4 = input.substring(k, input.length());

                    boolean isValid = validateBlocks(block1, block2, block3, block4);

                    if (isValid) {
                        validIps.add(block1.concat(".").concat(block2).concat(".").concat(block3).concat(".").concat(block4));
                    }
                }
            }
        }

        return validIps;
    }

    private static boolean validateBlocks(String... blocks) {
        for (String block : blocks) {
            if (block.length() > 3) {
                return false;
            }

            if (block.startsWith("0") && block.length() > 1) {
                return false;
            }

            if (Integer.parseInt(block) > 255) {
                return false;
            }
        }

        return true;
    }
}
