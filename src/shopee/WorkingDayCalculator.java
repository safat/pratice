package shopee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class WorkingDayCalculator {
    public static void main(String[] args) {
        Date start = convertToDate("2020-03-06");
        Date end = convertToDate("2020-03-09");

        System.out.println(calculateWorkingDays(start, end));

    }

    public static int calculateWorkingDays(Date fromDate, Date endDate) {
        fromDate = truncDate(fromDate);
        endDate = truncDate(endDate);

        if (fromDate.equals(endDate)) {
            return 0;
        }

        List<Date> offDays = constructOffDays();
        int count = 0;

        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(fromDate);

        Calendar toCal = Calendar.getInstance();
        fromCal.setTime(endDate);

        if (fromCal.get(Calendar.DAY_OF_YEAR) >= toCal.get(Calendar.DAY_OF_YEAR)) {
            return 0;
        }

        if (offDays.contains(fromDate)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fromDate);
            cal.add(Calendar.DATE, 1);
            fromDate = cal.getTime();
        }

        while (!fromDate.equals(endDate)) {
            if (!offDays.contains(endDate)) {
                count++;
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            cal.add(Calendar.DATE, -1);
            endDate = cal.getTime();

            if (count > 40) {
                System.out.println(fromCal.toString());
                System.out.println(toCal.toString());
            }
        }


        return count + 1;
    }

    private static List<Date> constructOffDays() {

        List<Date> offDays = new ArrayList<>();
        //holidays
        offDays.add(convertToDate("2020-03-08"));
        offDays.add(convertToDate("2020-03-25"));
        offDays.add(convertToDate("2020-03-30"));
        offDays.add(convertToDate("2020-03-31"));

        // weekends
        offDays.add(convertToDate("2020-03-01"));
        offDays.add(convertToDate("2020-03-08"));
        offDays.add(convertToDate("2020-03-15"));
        offDays.add(convertToDate("2020-03-22"));
        offDays.add(convertToDate("2020-03-29"));

        offDays.add(convertToDate("2020-04-05"));

        return offDays;
    }

    public static Date convertToDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date truncDate(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}  