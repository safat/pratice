package basic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

public class Main {

//    public static void test(String test) {
//
//    }
//
//    public static void test(Integer test) {
//
//    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        final String pattern = ".####";
        //final String pattern = "###,###,##0.00";
        String output = new DecimalFormat(".####").format(12.22);
//        final String output = myFormatter.format(12.12123123);

        System.out.println(output);

        int[] numbers = {1, 10, 20, 50, 100, 180};

        int index = BinarySearch.binarySearch(numbers,  180);

        System.out.println(index);

        String s = "অক্ষর";

        PrintWriter success = new PrintWriter("abc.txt", "UTF-16");

        success.println("অক্ষর");
        success.close();

//        new Main().test(null);
    }
}
