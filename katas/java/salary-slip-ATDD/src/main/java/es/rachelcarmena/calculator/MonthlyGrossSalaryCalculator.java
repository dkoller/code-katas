package es.rachelcarmena.calculator;

import es.rachelcarmena.model.AnnualGrossSalary;
import es.rachelcarmena.model.MonthlyGrossSalary;

public class MonthlyGrossSalaryCalculator {
    public MonthlyGrossSalary calculate(AnnualGrossSalary annualGrossSalary) {
        return annualGrossSalary.toMonthlyGrossSalary();
    }
}
