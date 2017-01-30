package es.rachelcarmena;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DateTest {

    @Test 
    public void addDaysAtTheEndOfAYear() {
        Date fromDate = new Date(2016, 12, 31);
        fromDate.addDays(1);
        assertThat(fromDate).isEqualToComparingFieldByField(new Date(2017, 1, 1));
    }

    @Test 
    public void addDaysAtTheEndOfFebruary() {
        Date fromDate = new Date(2016, 2, 24);
        fromDate.addDays(6);
        assertThat(fromDate).isEqualToComparingFieldByField(new Date(2016, 3, 1));
    }

    @Test 
    public void addTwoYears() {
        Date fromDate = new Date(2016, 8, 24);
        fromDate.addDays(365*2);
        assertThat(fromDate).isEqualToComparingFieldByField(new Date(2018, 8, 24));
    }

    @Test 
    public void addMoreThanTwoYears() {
        Date fromDate = new Date(2016, 8, 24);
        fromDate.addDays(365*2 + 8);
        assertThat(fromDate).isEqualToComparingFieldByField(new Date(2018, 9, 1));
    }

    @Test 
    public void addDaysAtTheEndOfAMonth() {
        Date fromDate = new Date(2016, 1, 31);
        fromDate.addDays(1);
        assertThat(fromDate).isEqualToComparingFieldByField(new Date(2016, 2, 1));
    }

    @Test 
    public void addDaysInsideAMonth() {
        Date fromDate = new Date(2016, 6, 1);
        fromDate.addDays(29);
        assertThat(fromDate).isEqualToComparingFieldByField(new Date(2016, 6, 30));
    }

    @Test 
    public void addDaysFromJuneToJuly() {
        Date fromDate = new Date(2016, 6, 15);
        fromDate.addDays(30);
        assertThat(fromDate).isEqualToComparingFieldByField(new Date(2016, 7, 15));
    }
}
