package es.rachelcarmena;

import es.rachelcarmena.calculator.MonthlyGrossSalaryCalculator;
import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.MonthlyGrossSalary;

public class SalarySlipGenerator {

    private SalarySlipPrinter salarySlipPrinter;
    private MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator;

    public SalarySlipGenerator(SalarySlipPrinter salarySlipPrinter, MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator) {
        this.salarySlipPrinter = salarySlipPrinter;
        this.monthlyGrossSalaryCalculator = monthlyGrossSalaryCalculator;
    }

    public void generateFor(Employee employee) {
        String line = createLineForEmployeeID(employee.employeeID);
        salarySlipPrinter.printLine(line);

        line = createLineForEmployeeName(employee.employeeName);
        salarySlipPrinter.printLine(line);

        MonthlyGrossSalary monthlyCrossSalary = monthlyGrossSalaryCalculator.calculate(employee.annualGrossSalary);
        line = createLineForMonthlyGrossSalary(monthlyCrossSalary);
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

    private String formatPrintedLine(String header, String value) {
        return String.format("%s: %s", header, value);
    }

    private String formatPrintedLine(String header, Amount amount) {
        return String.format("%s: £%s", header, amount.toString());
    }
}