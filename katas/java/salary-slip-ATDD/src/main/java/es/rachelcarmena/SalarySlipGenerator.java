package es.rachelcarmena;

public class SalarySlipGenerator {
    private SalarySlipPrinter salarySlipPrinter;

    public SalarySlipGenerator(SalarySlipPrinter salarySlipPrinter) {
        this.salarySlipPrinter = salarySlipPrinter;
    }

    public void generateFor(Employee employee) {
        salarySlipPrinter.printLine("Employee ID: 12345");

    }
}
