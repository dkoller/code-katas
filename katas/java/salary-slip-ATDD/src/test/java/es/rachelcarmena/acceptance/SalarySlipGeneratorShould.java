package es.rachelcarmena.acceptance;

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

    @Mock
    SalarySlipPrinter salarySlipPrinter;

    @Test
    @Ignore
    public void print_a_salary_slip_with_employee_details_for_an_employee() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(24000);
        Employee employee = new Employee(12345, "John J Doe", annualGrossSalary);

        SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator(salarySlipPrinter);
        salarySlipGenerator.generateFor(employee);

        verify(salarySlipPrinter).printLine("Employee ID: 12345");
        verify(salarySlipPrinter).printLine("Employee Name: John J Doe");
        verify(salarySlipPrinter).printLine("Gross Salary: £2000.00");
        verify(salarySlipPrinter).printLine("National Insurance contributions: £159.40");
        verify(salarySlipPrinter).printLine("Tax-free allowance: £916.67");
        verify(salarySlipPrinter).printLine("Taxable income: £1083.33");
        verify(salarySlipPrinter).printLine("Tax payable: £216.67");
    }
}
