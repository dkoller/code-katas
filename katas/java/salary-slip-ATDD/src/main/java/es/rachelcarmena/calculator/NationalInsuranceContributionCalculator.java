package es.rachelcarmena.calculator;

import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.AnnualGrossSalary;

import java.util.ArrayList;
import java.util.List;

public class NationalInsuranceContributionCalculator {

    public Amount calculate(AnnualGrossSalary annualGrossSalary) {
        List<LimitAndRateRelation> limitAndRateRelations = getLimitAndRateRelations();

        SalaryAttributeCalculator salaryAttributeCalculator = new SalaryAttributeCalculator(limitAndRateRelations);
        Amount attribute = salaryAttributeCalculator.calculate(annualGrossSalary);
        return attribute.perMonth();
    }

    private List<LimitAndRateRelation> getLimitAndRateRelations() {
        final Amount MAX_LIMIT_NO_CONTRIBUTIONS = new Amount("8060.00");
        final Amount MAX_LIMIT_BASIC_CONTRIBUTIONS = new Amount("43000.00");
        final byte BASIC_RATE = 12;
        final byte HIGHER_RATE = 2;

        List<LimitAndRateRelation> limitAndRateRelations = new ArrayList<>();
        limitAndRateRelations.add(new LimitAndRateRelation(MAX_LIMIT_BASIC_CONTRIBUTIONS, HIGHER_RATE));
        limitAndRateRelations.add(new LimitAndRateRelation(MAX_LIMIT_NO_CONTRIBUTIONS, BASIC_RATE));
        return limitAndRateRelations;
    }
}
