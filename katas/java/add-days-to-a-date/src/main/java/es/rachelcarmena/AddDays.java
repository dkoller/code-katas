package es.rachelcarmena;

import java.lang.Integer;

public class AddDays {
    
    public static void main(String argv[]) throws Exception {

        if (argv.length < 4) {
            System.out.println("\nOooops! Use:\n");
            System.out.println("\tjava -jar AddDays.jar [year] [month] [dayOfMonth] [daysToAdd]\n");
            System.exit(0);
        }
        
        int year = Integer.valueOf(argv[0]);
        int month = Integer.valueOf(argv[1]);
        int dayOfMonth = Integer.valueOf(argv[2]);
        int daysToAdd = Integer.valueOf(argv[3]);

        if ((month > 12) || (dayOfMonth > DateUtils.daysOfAMonthInAYear(month, year))) {
            System.out.println("\nOooops! Check parameters\n");
            System.exit(0);
        }

        Date myDate = new Date(year, month, dayOfMonth);
        myDate.addDays(daysToAdd);
        System.out.println(myDate.toString());
    }
}
