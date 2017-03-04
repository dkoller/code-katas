package es.rachelcarmena;

public class SalarySlipGenerator {
    private SalarySlipPrinter salarySlipPrinter;

    public SalarySlipGenerator(SalarySlipPrinter salarySlipPrinter) {
        this.salarySlipPrinter = salarySlipPrinter;
    }

    public void generateFor(Employee employee) {
        salarySlipPrinter.printLine("Employee ID: " + String.valueOf(employee.employeeID));
        salarySlipPrinter.printLine("Employee Name: " + String.valueOf(employee.employeeName));
    }
}
