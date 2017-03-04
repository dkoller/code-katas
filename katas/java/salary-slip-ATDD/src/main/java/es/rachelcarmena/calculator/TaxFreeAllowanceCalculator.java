package es.rachelcarmena.calculator;

import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.MonthlyGrossSalary;

public class TaxFreeAllowanceCalculator {

    public Amount calculate(MonthlyGrossSalary monthlyGrossSalary) {
        final Amount MAX_LIMIT_FREE_BAND = new Amount("11000.00");

        Amount freeBandPerMonth = MAX_LIMIT_FREE_BAND.perMonth();
        return freeBandPerMonth.min(monthlyGrossSalary);
    }
}
