package es.rachelcarmena.unit;

import es.rachelcarmena.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SalarySlipGeneratorShould {

    @Mock SalarySlipPrinter salarySlipPrinter;
    @Mock MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator;

    private static final AnnualGrossSalary ANNUAL_GROSS_SALARY = new AnnualGrossSalary(24000);
    private static final Employee EMPLOYEE = new Employee(12345, "John J Doe", ANNUAL_GROSS_SALARY);

    private SalarySlipGenerator salarySlipGenerator;

    @Before
    public void initialize() {
        salarySlipGenerator = new SalarySlipGenerator(salarySlipPrinter, monthlyGrossSalaryCalculator);
    }

    @Test
    public void print_employeID_for_an_employee() {
        salarySlipGenerator.generateFor(EMPLOYEE);

        verify(salarySlipPrinter).printLine("Employee ID: 12345");
    }

    @Test
    public void print_employeName_for_an_employee() {
        salarySlipGenerator.generateFor(EMPLOYEE);

        verify(salarySlipPrinter).printLine("Employee Name: John J Doe");
    }

    @Test
    public void print_montly_salary_for_an_employee() {
        when(monthlyGrossSalaryCalculator.calculate(ANNUAL_GROSS_SALARY)).thenReturn(new MonthlyCrossSalary(2000));
        salarySlipGenerator.generateFor(EMPLOYEE);

        verify(salarySlipPrinter).printLine("Gross Salary: £2000.00");
    }
}