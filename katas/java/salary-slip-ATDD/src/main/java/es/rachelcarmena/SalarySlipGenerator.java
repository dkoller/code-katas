package es.rachelcarmena;

import es.rachelcarmena.calculator.MonthlyGrossSalaryCalculator;
import es.rachelcarmena.calculator.NationalInsuranceContributionCalculator;
import es.rachelcarmena.calculator.TaxesCalculator;
import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.MonthlyGrossSalary;

public class SalarySlipGenerator {

    private SalarySlipPrinter salarySlipPrinter;
    private MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator;
    private NationalInsuranceContributionCalculator nationalInsuranceContributionCalculator;
    private TaxesCalculator taxesCalculator;

    public SalarySlipGenerator(SalarySlipPrinter salarySlipPrinter, MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator, NationalInsuranceContributionCalculator nationalInsuranceContributionCalculator, TaxesCalculator taxesCalculator) {
        this.salarySlipPrinter = salarySlipPrinter;
        this.monthlyGrossSalaryCalculator = monthlyGrossSalaryCalculator;
        this.nationalInsuranceContributionCalculator = nationalInsuranceContributionCalculator;
        this.taxesCalculator = taxesCalculator;
    }

    public void generateFor(Employee employee) {
        final String HEADER_EMPLOYEE_ID = "Employee ID";
        final String HEADER_EMPLOYEE_NAME = "Employee Name";
        final String HEADER_EMPLOYEE_MONTHLY_GROSS_SALARY = "Gross Salary";
        final String HEADER_NATIONAL_INSURANCE_CONTRIBUTION = "National Insurance contributions";
        final String HEADER_TAX_FREE_ALLOWANCE = "Tax-free allowance";
        final String HEADER_TAXABLE_INCOME = "Taxable income";

        String line = formatPrintedLine(HEADER_EMPLOYEE_ID, String.valueOf(employee.employeeID));
        salarySlipPrinter.printLine(line);

        line = formatPrintedLine(HEADER_EMPLOYEE_NAME, employee.employeeName);
        salarySlipPrinter.printLine(line);

        MonthlyGrossSalary monthlyGrossSalary = monthlyGrossSalaryCalculator.calculate(employee.annualGrossSalary);
        line = formatPrintedLine(HEADER_EMPLOYEE_MONTHLY_GROSS_SALARY, monthlyGrossSalary);
        salarySlipPrinter.printLine(line);

        Amount nationalInsuranceContribution = nationalInsuranceContributionCalculator.calculate(employee.annualGrossSalary);
        line = formatPrintedLine(HEADER_NATIONAL_INSURANCE_CONTRIBUTION, nationalInsuranceContribution);
        salarySlipPrinter.printLine(line);

        Amount taxFreeAllowance = taxesCalculator.calculateFreeAllowance(monthlyGrossSalary);
        line = formatPrintedLine(HEADER_TAX_FREE_ALLOWANCE, taxFreeAllowance);
        salarySlipPrinter.printLine(line);

        Amount taxableIncome = taxesCalculator.calculateTaxableIncome(monthlyGrossSalary, taxFreeAllowance);
        line = formatPrintedLine(HEADER_TAXABLE_INCOME, taxableIncome);
        salarySlipPrinter.printLine(line);
    }

    private String formatPrintedLine(String header, String value) {
        return String.format("%s: %s", header, value);
    }

    private String formatPrintedLine(String header, Amount amount) {
        return String.format("%s: £%s", header, amount.toString());
    }
}