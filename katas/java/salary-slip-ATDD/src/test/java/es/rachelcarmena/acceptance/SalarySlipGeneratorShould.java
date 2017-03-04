package es.rachelcarmena.acceptance;

import es.rachelcarmena.*;
import es.rachelcarmena.calculator.MonthlyGrossSalaryCalculator;
import es.rachelcarmena.model.AnnualGrossSalary;
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
    @Mock
    MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator;

    @Test
    @Ignore
    public void print_a_salary_slip_with_employee_details_for_an_employee() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(24000);
        Employee employee = new Employee(12345, "John J Doe", annualGrossSalary);

        SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator(salarySlipPrinter, monthlyGrossSalaryCalculator);
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
