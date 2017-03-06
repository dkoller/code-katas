package es.rachelcarmena.calculator;

import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.AnnualGrossSalary;
import es.rachelcarmena.model.MonthlyGrossSalary;

public class TaxesCalculator {

    public Amount calculateFreeAllowance(MonthlyGrossSalary monthlyGrossSalary) {
        final Amount MAX_LIMIT_FREE_BAND = Amount.valueOf(11000);

        Amount freeBandPerMonth = MAX_LIMIT_FREE_BAND.perMonth();
        return freeBandPerMonth.min(monthlyGrossSalary);
    }

    public Amount calculateTaxableIncome(MonthlyGrossSalary monthlyGrossSalary, Amount taxFreeAllowance) {
        return monthlyGrossSalary.subtract(taxFreeAllowance);
    }

    public Amount calculateTaxPayable(AnnualGrossSalary annualGrossSalary) {
        Amount MAX_LIMIT_BASIC_RATE = Amount.valueOf(11000);
        Amount MAX_LIMIT_HIGHER_RATE = Amount.valueOf(43000);
        Amount MAX_LIMIT_RULES_CHANGE = Amount.valueOf(100000);
        final int TAX_BASIC_RATE = 20;
        final int TAX_HIGHER_RATE = 40;

        Amount excess = annualGrossSalary.subtract(MAX_LIMIT_RULES_CHANGE);
        boolean hasExcess = excess.greaterThanZero();
        if (hasExcess) {
            Amount reduction = excess.divide(Amount.valueOf(2));
            MAX_LIMIT_BASIC_RATE = MAX_LIMIT_BASIC_RATE.subtract(reduction);
            MAX_LIMIT_HIGHER_RATE = MAX_LIMIT_HIGHER_RATE.subtract(reduction);
        }

        Amount basicExcess = annualGrossSalary.subtract(MAX_LIMIT_BASIC_RATE);
        boolean hasBasicExcess = basicExcess.greaterThanZero();
        if (!hasBasicExcess)
            return Amount.valueOf(0);

        Amount higherExcess = annualGrossSalary.subtract(MAX_LIMIT_HIGHER_RATE);
        boolean hasHigherExcess = higherExcess.greaterThanZero();
        if (!hasHigherExcess)
            return basicExcess.calculatePercentage(TAX_BASIC_RATE);

        Amount rangeBasicRate = MAX_LIMIT_HIGHER_RATE.subtract(MAX_LIMIT_BASIC_RATE);
        Amount taxPayable = rangeBasicRate.calculatePercentage(TAX_BASIC_RATE);
        taxPayable = taxPayable.add(higherExcess.calculatePercentage(TAX_HIGHER_RATE));
        return taxPayable;
    }
}