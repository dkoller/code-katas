package es.rachelcarmena.calculator;

import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.AnnualGrossSalary;
import es.rachelcarmena.model.MonthlyGrossSalary;

import java.util.ArrayList;
import java.util.List;

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

        Amount taxPayable = new Amount(0);
        Amount salaryUnderTax = annualGrossSalary;

        List<LimitAndRateRelation> limitAndRateRelations = getLimitAndRateRelations(annualGrossSalary);
        for (LimitAndRateRelation limitAndRateRelation: limitAndRateRelations) {
            Amount amount = salaryUnderTax.subtract(limitAndRateRelation.limit);
            boolean hasExcess = amount.greaterThanZero();
            if (hasExcess) {
                taxPayable = taxPayable.add(amount.calculatePercentage(limitAndRateRelation.rate));
                salaryUnderTax = limitAndRateRelation.limit;
            }
        }
        return taxPayable;
    }

    private List<LimitAndRateRelation> getLimitAndRateRelations(AnnualGrossSalary annualGrossSalary) {
        Amount MAX_LIMIT_BASIC_RATE = Amount.valueOf(11000);
        Amount MAX_LIMIT_HIGHER_RATE = Amount.valueOf(43000);
        final Amount MAX_LIMIT_RULES_CHANGE = Amount.valueOf(100000);
        final int TAX_BASIC_RATE = 20;
        final int TAX_HIGHER_RATE = 40;

        Amount excessToChangeRules = annualGrossSalary.subtract(MAX_LIMIT_RULES_CHANGE);
        boolean hasExcessToChangeRules = excessToChangeRules.greaterThanZero();
        if (hasExcessToChangeRules) {
            Amount reduction = excessToChangeRules.divide(Amount.valueOf(2));
            MAX_LIMIT_BASIC_RATE = MAX_LIMIT_BASIC_RATE.subtract(reduction);
            MAX_LIMIT_HIGHER_RATE = MAX_LIMIT_HIGHER_RATE.subtract(reduction);
        }

        List<LimitAndRateRelation> limitAndRateRelations = new ArrayList<>();
        limitAndRateRelations.add(new LimitAndRateRelation(MAX_LIMIT_HIGHER_RATE, TAX_HIGHER_RATE));
        limitAndRateRelations.add(new LimitAndRateRelation(MAX_LIMIT_BASIC_RATE, TAX_BASIC_RATE));
        return limitAndRateRelations;
    }

}