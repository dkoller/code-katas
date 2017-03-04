package es.rachelcarmena;

public class SalarySlipGenerator {
    private static final String HEADER_EMPLOYEE_ID = "Employee ID";
    private static final String HEADER_EMPLOYEE_NAME = "Employee Name";
    private SalarySlipPrinter salarySlipPrinter;

    public SalarySlipGenerator(SalarySlipPrinter salarySlipPrinter) {
        this.salarySlipPrinter = salarySlipPrinter;
    }

    public void generateFor(Employee employee) {
        String line = createLineForEmployeeID(employee.employeeID);
        salarySlipPrinter.printLine(line);

        line = createLineForEmployeeName(employee.employeeName);
        salarySlipPrinter.printLine(line);
    }

    private String createLineForEmployeeID(int employeeID) {
        return formatPrintedLine(HEADER_EMPLOYEE_ID, String.valueOf(employeeID));
    }

    private String createLineForEmployeeName(String employeeName) {
        return formatPrintedLine(HEADER_EMPLOYEE_NAME, employeeName);
    }

    private String formatPrintedLine(String header, String value) {
        return String.format("%s: %s", header, value);
    }
}