package es.rachelcarmena.calculator;

import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.AnnualGrossSalary;

public class NationalInsuranceContributionCalculator {

    public Amount calculate(AnnualGrossSalary annualGrossSalary) {
        final Amount MAX_LIMIT_NO_CONTRIBUTIONS = new Amount("8060.00");
        final Amount MAX_LIMIT_BASIC_CONTRIBUTIONS = new Amount("43000.00");
        final byte BASIC_RATE = 12;
        final byte HIGHER_RATE = 2;

        Amount basicExcess = annualGrossSalary.subtract(MAX_LIMIT_NO_CONTRIBUTIONS);
        boolean hasBasicExcess = basicExcess.greaterThanZero();
        if (!hasBasicExcess)
            return new Amount("0.00");

        Amount higherExcess = annualGrossSalary.subtract(MAX_LIMIT_BASIC_CONTRIBUTIONS);
        boolean hasHigherExcess = higherExcess.greaterThanZero();
        if (!hasHigherExcess)
            return basicExcess.calculatePercentage(BASIC_RATE).perMonth();

        Amount rangeBasicContributions = MAX_LIMIT_BASIC_CONTRIBUTIONS.subtract(MAX_LIMIT_NO_CONTRIBUTIONS);
        Amount contributions = rangeBasicContributions.calculatePercentage(BASIC_RATE);
        contributions = contributions.add(higherExcess.calculatePercentage(HIGHER_RATE));
        return contributions.perMonth();
    }
}
