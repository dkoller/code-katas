package es.rachelcarmena.unit;

import es.rachelcarmena.calculator.TaxableIncomeCalculator;
import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.AnnualGrossSalary;
import es.rachelcarmena.model.MonthlyGrossSalary;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class TaxableIncomeCalculatorShould {

    private TaxableIncomeCalculator taxableIncomeCalculator = new TaxableIncomeCalculator();

    @Test
    public void calculate_taxable_income_for_no_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(5000);
        MonthlyGrossSalary monthlyGrossSalary = annualGrossSalary.toMonthlyGrossSalary();

        assertEquals(new Amount(0), taxableIncomeCalculator.calculate(monthlyGrossSalary, monthlyGrossSalary));
    }

    @Test
    public void calculate_taxable_income_for_basic_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(9060);
        MonthlyGrossSalary monthlyGrossSalary = annualGrossSalary.toMonthlyGrossSalary();

        assertEquals(new Amount(0), taxableIncomeCalculator.calculate(monthlyGrossSalary, monthlyGrossSalary));
    }

    @Test
    public void calculate_taxable_income_for_higher_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(45000);
        MonthlyGrossSalary monthlyGrossSalary = annualGrossSalary.toMonthlyGrossSalary();

        assertEquals(new Amount("2833.33"), taxableIncomeCalculator.calculate(monthlyGrossSalary, new Amount("916.67")));
    }
}
