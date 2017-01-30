package es.rachelcarmena;

public class DateUtils {

    public static int daysOfAYear(int year) {
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
            return 366;
        return 365;
    }

    public static int daysOfAMonthInAYear(int month, int year) {
        if (month == 2)
            return (daysOfAYear(year) == 366)? 29: 28;
        if (month <= 7) 
            return (month % 2 == 0)? 30: 31;
        return (month % 2 == 0)? 31: 30;
    }
}
