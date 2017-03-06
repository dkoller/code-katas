package es.rachelcarmena.calculator;

import es.rachelcarmena.domain.AnnualGrossSalary;
import es.rachelcarmena.domain.MonthlyGrossSalary;

public class MonthlyGrossSalaryCalculator {

    public MonthlyGrossSalary calculate(AnnualGrossSalary annualGrossSalary) {
        return annualGrossSalary.toMonthlyGrossSalary();
    }
}
