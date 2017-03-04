package es.rachelcarmena;

public class MonthlyGrossSalaryCalculator {
    public MonthlyGrossSalary calculate(AnnualGrossSalary annualGrossSalary) {
        return annualGrossSalary.toMonthlyGrossSalary();
    }
}
