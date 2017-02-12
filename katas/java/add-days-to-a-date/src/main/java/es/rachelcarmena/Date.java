package es.rachelcarmena;

public class Date {

    public int year;
    public int month;
    public int dayOfMonth;
    
    public Date(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    private void addAMonth() {
        if (this.month != 12) {
            this.month += 1;
            return;
        }
        this.month = 1;
        this.year += 1;
    }

    public void addDays(int numberOfDays) {
        int restOfDays = numberOfDays; 
        int daysOfMonth = 0;
        while (restOfDays > 0) {
            daysOfMonth = DateUtils.daysOfAMonthInAYear(this.month, this.year);         
            if ((this.dayOfMonth + restOfDays) <= daysOfMonth) {
                this.dayOfMonth += restOfDays;
                return;
            }
            restOfDays -= (daysOfMonth - this.dayOfMonth + 1);
            this.dayOfMonth = 1;
            addAMonth();
        }
    }

    public String toString() {
        return String.format("Result: year %d - month %d - dayOfMonth %d%n", this.year, this.month, this.dayOfMonth);
    }
}
