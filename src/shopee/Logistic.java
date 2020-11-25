//package shopee;
//
//import com.opencsv.CSVReader;
//import com.opencsv.CSVWriter;
//import com.opencsv.exceptions.CsvException;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.nio.file.Files;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.StringTokenizer;
//import java.util.TimeZone;
//
///**
// * @authod Rashedul Hasan Rijul
// */
//public class Logistic {
//
//    FastReader fastReader;
//    String METRO_MANILA = "METRO MANILA";
//    String METRO_MANILLA_1 = "METROMANILA";
//    String LUZON = "LUZON";
//    String VISAYAS = "VISAYAS";
//    String MINDANAO = "MINDANAO";
//
//    int[][] slaMat = new int[4][4];
//    Calendar holiday1;
//    Calendar holiday2;
//    Calendar holiday3;
//    Calendar holiday4;
//    boolean print = true;
//
//    public Logistic() {
//        slaMat[0][0] = 3;
//        slaMat[0][1] = 5;
//        slaMat[0][2] = 7;
//        slaMat[0][3] = 7;
//
//        slaMat[1][0] = 5;
//        slaMat[1][1] = 5;
//        slaMat[1][2] = 7;
//        slaMat[1][3] = 7;
//
//        slaMat[2][0] = 7;
//        slaMat[2][1] = 7;
//        slaMat[2][2] = 7;
//        slaMat[2][3] = 7;
//
//        slaMat[3][0] = 7;
//        slaMat[3][1] = 7;
//        slaMat[3][2] = 7;
//        slaMat[3][3] = 7;
//
//        holiday1 = Calendar.getInstance();
//        holiday1.set(2020, Calendar.MARCH, 8);
//
//
//        holiday2 = Calendar.getInstance();
//        holiday2.set(2020, Calendar.MARCH, 25);
//
//        holiday3 = Calendar.getInstance();
//        holiday3.set(2020, Calendar.MARCH, 30);
//
//        holiday4 = Calendar.getInstance();
//        holiday4.set(2020, Calendar.MARCH, 31);
//    }
//
//    public Logistic(FastReader fastReader) {
//        this.fastReader = fastReader;
//    }
//
//    //String[]
//
//    public static void main(String args[]) throws IOException, ParseException {
//
//        Logistic soln = new Logistic();
//        soln.solve();
//    }
//
//    public static boolean isWithinOneHour(Calendar date1, Calendar date2) throws ParseException {
//
//        long differenceInMillis = date2.getTimeInMillis() - date1.getTimeInMillis();
//        return differenceInMillis <= 3600000L;
//
//    }
//
//    class Order {
//        public String orderId;
//        public Date pickUp;
//        public Date att1;
//        public Date att2;
//        public int sla;
//
//        @Override
//        public String toString() {
//            return "Order{" + "orderId='" + orderId + '\'' + ", pickUp=" + pickUp + ", att1=" + att1 + ", att2=" +
//                    att2 + ", slaDate=" + sla + '}';
//        }
//    }
//
//    private void solve() throws IOException, ParseException {
//        BufferedReader bufferedReader = Files.newBufferedReader(new File("delivery_orders_march.csv").toPath());
//        //BufferedReader bufferedReader = Files.newBufferedReader(new File("test1.csv").toPath());
//
//        print = false;
//
//        List<String[]> strings = readAll(bufferedReader);
//        List<String[]> answerStrings = new ArrayList<>();
//        answerStrings.add(new String[]{"orderid", "is_late"});
//        CSVWriter writer = new CSVWriter(new FileWriter("solution.csv"));
//
//
//        //separateRows(strings, answerStrings);
//
//        int i = 0;
//        int buyer = -1;
//        int seller = -1;
//        for (String[] strs : strings) {
//            if (i == 0) {
//                i++;
//                continue;
//            }
//            Order order = new Order();
//
//            order.orderId = strs[0];
//            order.pickUp = new Date(Long.parseLong(strs[1]) * 1000);
//
//            // cal attmpt1
//            double date = Double.parseDouble(strs[2]) * 1000;
//            order.att1 = new Date((long) date);
//
//            // cal attmpt2
//            if (strs[3] != null && strs[3].length() > 0) {
//                date = Double.parseDouble(strs[3]) * 1000;
//                order.att2 = new Date((long) date);
//            }
//
//            buyer = getAddressId(strs[4]);
//
//            seller = getAddressId(strs[5]);
//
//            order.sla = slaMat[seller][buyer];
//
//            String answer = getResult(order);
//
//            answerStrings.add(new String[]{strs[0], answer});
//        }
//
//        writer.writeAll(answerStrings);
//        writer.close();
//
//        System.out.println("done..");
//    }
//
//    private String getResult(Order order) {
//
//        if (print) {
//            System.out.println(order);
//        }
//
//        int firstDay = calcuateWorkingDays(order.pickUp, order.att1);
//        if (firstDay > order.sla) {
//            return "1";
//        }
//
//        if (order.att2 != null) {
////            Calendar cal = Calendar.getInstance();
////            cal.setTime(order.att1);
////            cal.add(Calendar.DATE, 1);
////            order.att1 = cal.getTime();
//
//            int secondDay = calcuateWorkingDays(order.att1, order.att2);
//            if (secondDay > 3) {
//                return "1";
//            }
//        }
//
//        return "0";
//    }
//
//    private int calcuateWorkingDays(Date fromDate, Date endDate) {
//
//        if (fromDate.compareTo(endDate) > 0) {
//            return 0;
//        }
//        Date tmpDate = (Date) fromDate.clone();
//        Calendar fromCal = Calendar.getInstance();
//        Calendar endCal = Calendar.getInstance();
//
//        fromCal.setTime(tmpDate);
//        endCal.setTime(endDate);
//
//        boolean sameDay = fromCal.get(Calendar.DAY_OF_YEAR) == endCal.get(Calendar.DAY_OF_YEAR) &&
//                fromCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR);
//
//        if (sameDay) {
//            return 1;
//        }
//        fromCal.add(Calendar.DATE, 1);
//
//        int workingDay = 0;
//        while (true) {
//            if (isWorkingDay(fromCal)) {
//                workingDay++;
//            }
//            sameDay = fromCal.get(Calendar.DAY_OF_YEAR) == endCal.get(Calendar.DAY_OF_YEAR) &&
//                    fromCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR);
//            if (sameDay) {
//                if (!isWorkingDay(fromCal)) {
//                    workingDay++;
//                }
//                break;
//            }
//            fromCal.add(Calendar.DATE, 1);
//        }
//
//        if (print) {
//            System.out.println(workingDay);
//        }
//        return workingDay;
//    }
//
//
//    private boolean isWorkingDay(Calendar fromCal) {
//        if (fromCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//            return false;
//        }
//        if (fromCal.get(Calendar.DAY_OF_YEAR) == holiday1.get(Calendar.DAY_OF_YEAR) &&
//                fromCal.get(Calendar.YEAR) == holiday1.get(Calendar.YEAR)) {
//            return false;
//        }
//        if (fromCal.get(Calendar.DAY_OF_YEAR) == holiday2.get(Calendar.DAY_OF_YEAR) &&
//                fromCal.get(Calendar.YEAR) == holiday2.get(Calendar.YEAR)) {
//            return false;
//        }
//        if (fromCal.get(Calendar.DAY_OF_YEAR) == holiday3.get(Calendar.DAY_OF_YEAR) &&
//                fromCal.get(Calendar.YEAR) == holiday3.get(Calendar.YEAR)) {
//            return false;
//        }
//        if (fromCal.get(Calendar.DAY_OF_YEAR) == holiday4.get(Calendar.DAY_OF_YEAR) &&
//                fromCal.get(Calendar.YEAR) == holiday4.get(Calendar.YEAR)) {
//            return false;
//        }
//
//        return true;
//    }
//
//
//    private int getAddressId(String address) {
//
//        address = address.toUpperCase().trim();
//
////        int count = 0;
////
////        if (address.contains(METRO_MANILA) || address.contains(METRO_MANILLA_1)) {
////            count++;
////        }
////
////        if (address.contains(LUZON)) {
////            count++;
////        }
////
////        if (address.contains(VISAYAS)) {
////            count++;
////        }
////
////        if (address.contains(MINDANAO)) {
////            count++;
////        }
////
////        if (count > 1) {
////            System.out.println(address);
////        }
//
//        if (address.endsWith(METRO_MANILA) || address.endsWith(METRO_MANILLA_1)) {
//            return 0;
//        }
//        if (address.endsWith(LUZON)) {
//            return 1;
//
//        }
//        if (address.endsWith(VISAYAS)) {
//            return 2;
//        }
//        if (address.endsWith(MINDANAO)) {
//            return 3;
//        }
//        return -1;
//    }
//
//
//    public List<String[]> readAll(Reader reader) throws IOException {
//
//        CSVReader csvReader = new CSVReader(reader);
//        List<String[]> strings = null;
//        try {
//            strings = csvReader.readAll();
//        } catch (CsvException e) {
//
//
//        }
//        reader.close();
//        csvReader.close();
//        return strings;
//    }
//
//
//    static class FastReader {
//        BufferedReader br;
//        StringTokenizer st;
//
//        public FastReader() {
//            br = new BufferedReader(new InputStreamReader(System.in));
//        }
//
//        public static FastReader getFileReader(String fileName) throws FileNotFoundException {
//            return new FastReader(new InputStreamReader(new FileInputStream(new File(fileName))));
//        }
//
//        public static FastReader getDefaultReader() throws FileNotFoundException {
//            return new FastReader();
//        }
//
//        public FastReader(InputStreamReader inputStreamReader) {
//            br = new BufferedReader(inputStreamReader);
//        }
//
//        String next() {
//            while (st == null || !st.hasMoreElements()) {
//                try {
//                    st = new StringTokenizer(br.readLine());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return st.nextToken();
//        }
//
//        int nextInt() {
//            return Integer.parseInt(next());
//        }
//
//        long nextLong() {
//            return Long.parseLong(next());
//        }
//
//        double nextDouble() {
//            return Double.parseDouble(next());
//        }
//
//        String nextLine() {
//            String str = "";
//            try {
//                str = br.readLine();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return str;
//        }
//    }
//
//}