package es.rachelcarmena.calculator;

import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.AnnualGrossSalary;

import java.util.List;

public class SalaryAttributeCalculator {
    private final List<LimitAndRateRelation> limitAndRateRelations;

    public SalaryAttributeCalculator(List<LimitAndRateRelation> limitAndRateRelations) {
        this.limitAndRateRelations = limitAndRateRelations;
    }

    public Amount calculate(AnnualGrossSalary annualGrossSalary) {
        Amount attribute = new Amount(0);
        Amount salaryUnderTax = annualGrossSalary;
        for (LimitAndRateRelation limitAndRateRelation: limitAndRateRelations) {
            Amount amount = salaryUnderTax.subtract(limitAndRateRelation.limit);
            boolean hasExcess = amount.greaterThanZero();
            if (hasExcess) {
                attribute = attribute.add(amount.calculatePercentage(limitAndRateRelation.rate));
                salaryUnderTax = limitAndRateRelation.limit;
            }
        }
        return attribute;
    }
}
