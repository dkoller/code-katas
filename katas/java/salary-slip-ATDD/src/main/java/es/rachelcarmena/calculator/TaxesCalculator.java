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

        Amount excessToChangeRules = annualGrossSalary.subtract(MAX_LIMIT_RULES_CHANGE);
        boolean hasExcessToChangeRules = excessToChangeRules.greaterThanZero();
        if (hasExcessToChangeRules) {
            Amount reduction = excessToChangeRules.divide(Amount.valueOf(2));
            MAX_LIMIT_BASIC_RATE = MAX_LIMIT_BASIC_RATE.subtract(reduction);
            MAX_LIMIT_HIGHER_RATE = MAX_LIMIT_HIGHER_RATE.subtract(reduction);
        }

        Amount taxPayable = new Amount(0);

        Amount salaryUnderTax = annualGrossSalary;
        Amount amount = salaryUnderTax.subtract(MAX_LIMIT_HIGHER_RATE);
        boolean hasExcess = amount.greaterThanZero();
        if (hasExcess) {
            taxPayable = taxPayable.add(amount.calculatePercentage(TAX_HIGHER_RATE));
            salaryUnderTax = MAX_LIMIT_HIGHER_RATE;
        }

        amount = salaryUnderTax.subtract(MAX_LIMIT_BASIC_RATE);
        hasExcess = amount.greaterThanZero();
        if (hasExcess) {
            taxPayable = taxPayable.add(amount.calculatePercentage(TAX_BASIC_RATE));
            salaryUnderTax = MAX_LIMIT_BASIC_RATE;
        }

        return taxPayable;
    }
}