package es.rachelcarmena.unit;

import es.rachelcarmena.calculator.MonthlyGrossSalaryCalculator;
import es.rachelcarmena.model.AnnualGrossSalary;
import es.rachelcarmena.model.MonthlyGrossSalary;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class MonthlyGrossSalaryCalculatorShould {

    @Test
    @Parameters({"2000, 24000", "4100, 49200"})
    public void calculate_monthly_gross_salary_for_gross_annual_salary_without_decimal(int monthlyGrossAmount, int annualGrossAmount) {
        MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator = new MonthlyGrossSalaryCalculator();
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(annualGrossAmount);
        MonthlyGrossSalary monthlyGrossSalary = new MonthlyGrossSalary(monthlyGrossAmount);
        assertThat(monthlyGrossSalaryCalculator.calculate(annualGrossSalary), is(monthlyGrossSalary));
    }

    @Test
    public void calculate_monthly_gross_salary_for_gross_annual_salary_with_decimals() {
        MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator = new MonthlyGrossSalaryCalculator();
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary("49201.20");
        MonthlyGrossSalary monthlyGrossSalary = new MonthlyGrossSalary("4100.10");
        assertThat(monthlyGrossSalaryCalculator.calculate(annualGrossSalary), is(monthlyGrossSalary));
    }
}
