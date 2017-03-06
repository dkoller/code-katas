package es.rachelcarmena;

import es.rachelcarmena.calculator.MonthlyGrossSalaryCalculator;
import es.rachelcarmena.calculator.NationalInsuranceContributionCalculator;
import es.rachelcarmena.calculator.TaxesCalculator;
import es.rachelcarmena.domain.Amount;
import es.rachelcarmena.domain.MonthlyGrossSalary;

public class SalarySlipGenerator {

    private Console console;
    private MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator;
    private NationalInsuranceContributionCalculator nationalInsuranceContributionCalculator;
    private TaxesCalculator taxesCalculator;

    public SalarySlipGenerator(Console console, MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator, NationalInsuranceContributionCalculator nationalInsuranceContributionCalculator, TaxesCalculator taxesCalculator) {
        this.console = console;
        this.monthlyGrossSalaryCalculator = monthlyGrossSalaryCalculator;
        this.nationalInsuranceContributionCalculator = nationalInsuranceContributionCalculator;
        this.taxesCalculator = taxesCalculator;
    }

    public void generateFor(Employee employee) {
        final String DESCRIPTION_EMPLOYEE_ID = "Employee ID";
        final String DESCRIPTION_EMPLOYEE_NAME = "Employee Name";
        final String DESCRIPTION_EMPLOYEE_MONTHLY_GROSS_SALARY = "Gross Salary";
        final String DESCRIPTION_NATIONAL_INSURANCE_CONTRIBUTION = "National Insurance contributions";
        final String DESCRIPTION_TAX_FREE_ALLOWANCE = "Tax-free allowance";
        final String DESCRIPTION_TAXABLE_INCOME = "Taxable income";
        final String DESCRIPTION_TAX_PAYABLE = "Tax payable";

        String line = formatPrintedLine(DESCRIPTION_EMPLOYEE_ID, String.valueOf(employee.employeeID));
        console.printLine(line);

        line = formatPrintedLine(DESCRIPTION_EMPLOYEE_NAME, employee.employeeName);
        console.printLine(line);

        MonthlyGrossSalary monthlyGrossSalary = monthlyGrossSalaryCalculator.calculate(employee.annualGrossSalary);
        line = formatPrintedLine(DESCRIPTION_EMPLOYEE_MONTHLY_GROSS_SALARY, monthlyGrossSalary);
        console.printLine(line);

        Amount nationalInsuranceContribution = nationalInsuranceContributionCalculator.calculate(employee.annualGrossSalary);
        line = formatPrintedLine(DESCRIPTION_NATIONAL_INSURANCE_CONTRIBUTION, nationalInsuranceContribution);
        console.printLine(line);

        Amount taxFreeAllowance = taxesCalculator.calculateFreeAllowance(monthlyGrossSalary);
        line = formatPrintedLine(DESCRIPTION_TAX_FREE_ALLOWANCE, taxFreeAllowance);
        console.printLine(line);

        Amount taxableIncome = taxesCalculator.calculateTaxableIncome(monthlyGrossSalary, taxFreeAllowance);
        line = formatPrintedLine(DESCRIPTION_TAXABLE_INCOME, taxableIncome);
        console.printLine(line);

        Amount taxPayable = taxesCalculator.calculateTaxPayable(employee.annualGrossSalary);
        line = formatPrintedLine(DESCRIPTION_TAX_PAYABLE, taxPayable);
        console.printLine(line);
    }

    private String formatPrintedLine(String description, String value) {
        return String.format("%s: %s", description, value);
    }

    private String formatPrintedLine(String description, Amount amount) {
        return formatPrintedLine(description, "£" + amount.toString());
    }
}