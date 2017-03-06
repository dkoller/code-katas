package es.rachelcarmena.unit;

import es.rachelcarmena.calculator.TaxesCalculator;
import es.rachelcarmena.domain.Amount;
import es.rachelcarmena.domain.AnnualGrossSalary;
import es.rachelcarmena.domain.MonthlyGrossSalary;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class TaxesCalculatorShould {

    private TaxesCalculator taxesCalculator = new TaxesCalculator();

    @Test
    public void calculate_tax_free_allowance_for_no_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(5000);
        MonthlyGrossSalary monthlyGrossSalary = annualGrossSalary.toMonthlyGrossSalary();

        assertEquals(monthlyGrossSalary, taxesCalculator.calculateFreeAllowance(monthlyGrossSalary));
    }

    @Test
    public void calculate_tax_free_allowance_for_basic_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(9060);
        MonthlyGrossSalary monthlyGrossSalary = annualGrossSalary.toMonthlyGrossSalary();

        assertEquals(monthlyGrossSalary, taxesCalculator.calculateFreeAllowance(monthlyGrossSalary));
    }

    @Test
    public void calculate_tax_free_allowance_for_higher_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(45000);

        assertEquals(new Amount("916.67"), taxesCalculator.calculateFreeAllowance(annualGrossSalary.toMonthlyGrossSalary()));
    }

    @Test
    public void calculate_taxable_income_for_no_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(5000);
        MonthlyGrossSalary monthlyGrossSalary = annualGrossSalary.toMonthlyGrossSalary();

        assertEquals(new Amount(0), taxesCalculator.calculateTaxableIncome(monthlyGrossSalary, monthlyGrossSalary));
    }

    @Test
    public void calculate_taxable_income_for_basic_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(9060);
        MonthlyGrossSalary monthlyGrossSalary = annualGrossSalary.toMonthlyGrossSalary();

        assertEquals(new Amount(0), taxesCalculator.calculateTaxableIncome(monthlyGrossSalary, monthlyGrossSalary));
    }

    @Test
    public void calculate_taxable_income_for_higher_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(45000);
        MonthlyGrossSalary monthlyGrossSalary = annualGrossSalary.toMonthlyGrossSalary();

        assertEquals(new Amount("2833.33"), taxesCalculator.calculateTaxableIncome(monthlyGrossSalary, new Amount("916.67")));
    }

    @Test
    @Parameters({"5000", "11000"})
    public void calculate_tax_payable_for_personal_allowance_band(int annualGrossSalaryAmount) {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(annualGrossSalaryAmount);

        assertEquals(new Amount(0), taxesCalculator.calculateTaxPayable(annualGrossSalary));
    }

    @Test
    @Parameters({"12000, 200", "40000, 5800"})
    public void calculate_tax_payable_for_basic_rate_band(int annualGrossSalaryAmount, int taxPayable) {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(annualGrossSalaryAmount);

        assertEquals(new Amount(taxPayable), taxesCalculator.calculateTaxPayable(annualGrossSalary));
    }

    @Test
    @Parameters({"45000, 7200", "50000, 9200", "60000, 13200", "100000, 29200", "105500, 32500"})
    public void calculate_tax_payable_for_higher_rate_band(int annualGrossSalaryAmount, int taxPayable) {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(annualGrossSalaryAmount);

        assertEquals(new Amount(taxPayable), taxesCalculator.calculateTaxPayable(annualGrossSalary));
    }
}
