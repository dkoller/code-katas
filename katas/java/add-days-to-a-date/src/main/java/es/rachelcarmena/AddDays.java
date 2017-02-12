package es.rachelcarmena;

import java.lang.Integer;

public class AddDays {
    
    public static void main(String argv[]) throws Exception {

        if (argv.length < 4) {
            System.out.format("%n%s%n%s%n", "Oooops! Use:", "java -jar AddDays.jar [year] [month] [dayOfMonth] [daysToAdd]");
            System.exit(0);
        }
        
        int year = Integer.valueOf(argv[0]);
        int month = Integer.valueOf(argv[1]);
        int dayOfMonth = Integer.valueOf(argv[2]);
        int daysToAdd = Integer.valueOf(argv[3]);

        if ((month > 12) || (dayOfMonth > DateUtils.daysOfAMonthInAYear(month, year))) {
            System.out.format("%n%s%n", "Oooops! Check parameters");
            System.exit(0);
        }

        Date myDate = new Date(year, month, dayOfMonth);
        myDate.addDays(daysToAdd);
        System.out.println(myDate.toString());
    }
}
