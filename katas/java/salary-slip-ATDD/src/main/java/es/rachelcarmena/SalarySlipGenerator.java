package es.rachelcarmena;

import es.rachelcarmena.calculator.MonthlyGrossSalaryCalculator;
import es.rachelcarmena.calculator.NationalInsuranceContributionCalculator;
import es.rachelcarmena.calculator.TaxFreeAllowanceCalculator;
import es.rachelcarmena.calculator.TaxableIncomeCalculator;
import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.MonthlyGrossSalary;

public class SalarySlipGenerator {

    private SalarySlipPrinter salarySlipPrinter;
    private MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator;
    private NationalInsuranceContributionCalculator nationalInsuranceContributionCalculator;
    private TaxFreeAllowanceCalculator taxFreeAllowanceCalculator;
    private TaxableIncomeCalculator taxableIncomeCalculator;

    public SalarySlipGenerator(SalarySlipPrinter salarySlipPrinter, MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator, NationalInsuranceContributionCalculator nationalInsuranceContributionCalculator, TaxFreeAllowanceCalculator taxFreeAllowanceCalculator, TaxableIncomeCalculator taxableIncomeCalculator) {
        this.salarySlipPrinter = salarySlipPrinter;
        this.monthlyGrossSalaryCalculator = monthlyGrossSalaryCalculator;
        this.nationalInsuranceContributionCalculator = nationalInsuranceContributionCalculator;
        this.taxFreeAllowanceCalculator = taxFreeAllowanceCalculator;
        this.taxableIncomeCalculator = taxableIncomeCalculator;
    }

    public void generateFor(Employee employee) {
        String line = createLineForEmployeeID(employee.employeeID);
        salarySlipPrinter.printLine(line);

        line = createLineForEmployeeName(employee.employeeName);
        salarySlipPrinter.printLine(line);

        MonthlyGrossSalary monthlyGrossSalary = monthlyGrossSalaryCalculator.calculate(employee.annualGrossSalary);
        line = createLineForMonthlyGrossSalary(monthlyGrossSalary);
        salarySlipPrinter.printLine(line);

        Amount nationalInsuranceContribution = nationalInsuranceContributionCalculator.calculate(employee.annualGrossSalary);
        line = createLineForNationalInsuranceContribution(nationalInsuranceContribution);
        salarySlipPrinter.printLine(line);

        Amount taxFreeAllowance = taxFreeAllowanceCalculator.calculate(monthlyGrossSalary);
        line = createLineForTaxFreeAllowance(taxFreeAllowance);
        salarySlipPrinter.printLine(line);

        Amount taxableIncome = taxableIncomeCalculator.calculate(monthlyGrossSalary, taxFreeAllowance);
        line = createLineForTaxableIncome(taxableIncome);
        salarySlipPrinter.printLine(line);
    }

    private String createLineForEmployeeID(int employeeID) {
        final String HEADER_EMPLOYEE_ID = "Employee ID";
        return formatPrintedLine(HEADER_EMPLOYEE_ID, String.valueOf(employeeID));
    }

    private String createLineForEmployeeName(String employeeName) {
        final String HEADER_EMPLOYEE_NAME = "Employee Name";
        return formatPrintedLine(HEADER_EMPLOYEE_NAME, employeeName);
    }

    private String createLineForMonthlyGrossSalary(MonthlyGrossSalary monthlyCrossSalary) {
        final String HEADER_EMPLOYEE_MONTHLY_GROSS_SALARY = "Gross Salary";
        return formatPrintedLine(HEADER_EMPLOYEE_MONTHLY_GROSS_SALARY, monthlyCrossSalary);
    }

    private String createLineForNationalInsuranceContribution(Amount nationalInsuranceContribution) {
        final String HEADER_NATIONAL_INSURANCE_CONTRIBUTION = "National Insurance contributions";
        return formatPrintedLine(HEADER_NATIONAL_INSURANCE_CONTRIBUTION, nationalInsuranceContribution);
    }

    private String createLineForTaxFreeAllowance(Amount taxFreeAllowance) {
        final String HEADER_TAX_FREE_ALLOWANCE = "Tax-free allowance";
        return formatPrintedLine(HEADER_TAX_FREE_ALLOWANCE, taxFreeAllowance);
    }

    private String createLineForTaxableIncome(Amount taxableIncome) {
        final String HEADER_TAXABLE_INCOME = "Taxable income";
        return formatPrintedLine(HEADER_TAXABLE_INCOME, taxableIncome);
    }

    private String formatPrintedLine(String header, String value) {
        return String.format("%s: %s", header, value);
    }

    private String formatPrintedLine(String header, Amount amount) {
        return String.format("%s: £%s", header, amount.toString());
    }
}