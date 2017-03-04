package es.rachelcarmena.unit;

import es.rachelcarmena.AnnualGrossSalary;
import es.rachelcarmena.MonthlyGrossSalary;
import es.rachelcarmena.MonthlyGrossSalaryCalculator;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MonthlyGrossSalaryCalculatorShould {

    @Test
    public void calculate_monthly_gross_salary_for_gross_annual_salary() {
        MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator = new MonthlyGrossSalaryCalculator();
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(24000);
        MonthlyGrossSalary monthlyGrossSalary = new MonthlyGrossSalary(2000);
        assertThat(monthlyGrossSalaryCalculator.calculate(annualGrossSalary), is(monthlyGrossSalary));
    }
}
