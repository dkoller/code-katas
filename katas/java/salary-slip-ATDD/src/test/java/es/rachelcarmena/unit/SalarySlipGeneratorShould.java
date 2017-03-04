package es.rachelcarmena.unit;

import es.rachelcarmena.AnnualGrossSalary;
import es.rachelcarmena.Employee;
import es.rachelcarmena.SalarySlipGenerator;
import es.rachelcarmena.SalarySlipPrinter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SalarySlipGeneratorShould {

    @Mock SalarySlipPrinter salarySlipPrinter;

    @Test
    public void print_employeID_12345_for_an_employee_with_ID_12345() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(24000);
        Employee employee = new Employee(12345, "John J Doe", annualGrossSalary);

        SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator(salarySlipPrinter);
        salarySlipGenerator.generateFor(employee);

        verify(salarySlipPrinter).printLine("Employee ID: 12345");
    }

    @Test
    public void print_employeID_56789_for_an_employee_with_ID_56789() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(24000);
        Employee employee = new Employee(56789, "John J Doe", annualGrossSalary);

        SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator(salarySlipPrinter);
        salarySlipGenerator.generateFor(employee);

        verify(salarySlipPrinter).printLine("Employee ID: 56789");
    }
}
