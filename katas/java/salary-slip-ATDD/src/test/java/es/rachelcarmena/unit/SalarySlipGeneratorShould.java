package es.rachelcarmena.unit;

import es.rachelcarmena.AnnualGrossSalary;
import es.rachelcarmena.Employee;
import es.rachelcarmena.SalarySlipGenerator;
import es.rachelcarmena.SalarySlipPrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SalarySlipGeneratorShould {

    @Mock SalarySlipPrinter salarySlipPrinter;

    private static final AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(24000);
    private static final Employee EMPLOYEE_12345_JOHN = new Employee(12345, "John J Doe", annualGrossSalary);
    private static final Employee EMPLOYEE_56789_PAUL = new Employee(56789, "Paul J Doe", annualGrossSalary);

    private SalarySlipGenerator salarySlipGenerator;

    @Before
    public void initialize() {
        salarySlipGenerator = new SalarySlipGenerator(salarySlipPrinter);
    }

    @Test
    public void print_employeID_12345_for_an_employee_with_ID_12345() {
        salarySlipGenerator.generateFor(EMPLOYEE_12345_JOHN);

        verify(salarySlipPrinter).printLine("Employee ID: 12345");
    }

    @Test
    public void print_employeID_56789_for_an_employee_with_ID_56789() {
        salarySlipGenerator.generateFor(EMPLOYEE_56789_PAUL);

        verify(salarySlipPrinter).printLine("Employee ID: 56789");
    }

    @Test
    public void print_employeName_John_for_an_employee_with_name_John() {
        salarySlipGenerator.generateFor(EMPLOYEE_12345_JOHN);

        verify(salarySlipPrinter).printLine("Employee Name: John J Doe");
    }
}
