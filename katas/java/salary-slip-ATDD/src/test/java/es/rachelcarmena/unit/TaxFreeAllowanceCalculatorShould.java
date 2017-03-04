package es.rachelcarmena.unit;

import es.rachelcarmena.calculator.TaxFreeAllowanceCalculator;
import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.AnnualGrossSalary;
import es.rachelcarmena.model.MonthlyGrossSalary;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class TaxFreeAllowanceCalculatorShould {

    private TaxFreeAllowanceCalculator taxFreeAllowanceCalculator = new TaxFreeAllowanceCalculator();

    @Test
    public void calculate_tax_free_allowance_for_no_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(5000);
        MonthlyGrossSalary monthlyGrossSalary = annualGrossSalary.toMonthlyGrossSalary();

        assertEquals(monthlyGrossSalary, taxFreeAllowanceCalculator.calculate(monthlyGrossSalary));
    }

    @Test
    public void calculate_tax_free_allowance_for_basic_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(9060);
        MonthlyGrossSalary monthlyGrossSalary = annualGrossSalary.toMonthlyGrossSalary();

        assertEquals(monthlyGrossSalary, taxFreeAllowanceCalculator.calculate(monthlyGrossSalary));
    }

    @Test
    public void calculate_tax_free_allowance_for_higher_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(45000);

        assertEquals(new Amount("916.67"), taxFreeAllowanceCalculator.calculate(annualGrossSalary.toMonthlyGrossSalary()));
    }
}
