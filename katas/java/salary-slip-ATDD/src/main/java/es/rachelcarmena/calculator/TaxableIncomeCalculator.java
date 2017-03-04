package es.rachelcarmena.calculator;

import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.MonthlyGrossSalary;

public class TaxableIncomeCalculator {

    public Amount calculate(MonthlyGrossSalary monthlyGrossSalary, Amount taxFreeAllowance) {
        return monthlyGrossSalary.subtract(taxFreeAllowance);
    }
}
