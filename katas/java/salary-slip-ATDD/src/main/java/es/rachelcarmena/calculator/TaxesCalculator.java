package es.rachelcarmena.calculator;

import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.AnnualGrossSalary;
import es.rachelcarmena.model.MonthlyGrossSalary;

public class TaxesCalculator {

    public Amount calculateFreeAllowance(MonthlyGrossSalary monthlyGrossSalary) {
        final Amount MAX_LIMIT_FREE_BAND = new Amount("11000.00");

        Amount freeBandPerMonth = MAX_LIMIT_FREE_BAND.perMonth();
        return freeBandPerMonth.min(monthlyGrossSalary);
    }

    public Amount calculateTaxableIncome(MonthlyGrossSalary monthlyGrossSalary, Amount taxFreeAllowance) {
        return monthlyGrossSalary.subtract(taxFreeAllowance);
    }

    public Amount calculateTaxPayable(AnnualGrossSalary annualGrossSalary) {
        final Amount MAX_LIMIT_BASIC_RATE = new Amount(11000);

        Amount basicExcess = annualGrossSalary.subtract(MAX_LIMIT_BASIC_RATE);
        boolean hasBasicExcess = basicExcess.greaterThanZero();
        if (hasBasicExcess) {
            return basicExcess.calculatePercentage(20);
        }
        return new Amount(0);
    }
}
