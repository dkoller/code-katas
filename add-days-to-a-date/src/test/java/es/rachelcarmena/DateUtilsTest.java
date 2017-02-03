package es.rachelcarmena;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class DateUtilsTest {

    @Test
    @Parameters({"1972", "1976", "1980", "1984", "1988", "1992", "1996"}) 
    public void getDaysOfALeapYear(int year) {
        assertEquals(366, DateUtils.daysOfAYear(year));
    }

    @Test
    @Parameters({"1900", "1989", "1990", "1991", "1993", "1994", "1995"})
    public void getDaysOfANotLeapYear(int year) {
        assertEquals(365, DateUtils.daysOfAYear(year));
    }

    @Test
    @Parameters({"11, 2015, 30", "12, 2015, 31", "2, 1988, 29", "2, 1991,28", "8, 2015, 31"})
    public void getDaysOfAMonthInAYear(int month, int year, int days) {
        assertEquals(days, DateUtils.daysOfAMonthInAYear(month, year));
    }
}
