package es.rachelcarmena.acceptance;

import es.rachelcarmena.Employee;
import es.rachelcarmena.SalarySlipGenerator;
import es.rachelcarmena.Console;
import es.rachelcarmena.calculator.MonthlyGrossSalaryCalculator;
import es.rachelcarmena.calculator.NationalInsuranceContributionCalculator;
import es.rachelcarmena.calculator.TaxesCalculator;
import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.AnnualGrossSalary;
import es.rachelcarmena.model.MonthlyGrossSalary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SalarySlipGeneratorShould {

    private final AnnualGrossSalary ANNUAL_GROSS_SALARY = new AnnualGrossSalary(24000);
    private final MonthlyGrossSalary MONTHLY_GROSS_SALARY = new MonthlyGrossSalary(2000);
    private final Amount TAX_FREE_ALLOWANCE = new Amount("916.67");

    @Mock
    Console console;
    @Mock
    MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator;
    @Mock
    NationalInsuranceContributionCalculator nationalInsuranceContributionCalculator;
    @Mock
    TaxesCalculator taxesCalculator;

    private final Employee employee = new Employee(12345, "John J Doe", ANNUAL_GROSS_SALARY);

    @Test
    public void print_a_salary_slip_with_employee_details_for_an_employee() {
        given(monthlyGrossSalaryCalculator.calculate(ANNUAL_GROSS_SALARY)).willReturn(MONTHLY_GROSS_SALARY);
        given(nationalInsuranceContributionCalculator.calculate(ANNUAL_GROSS_SALARY)).willReturn(new Amount("159.40"));
        given(taxesCalculator.calculateFreeAllowance(MONTHLY_GROSS_SALARY)).willReturn(TAX_FREE_ALLOWANCE);
        given(taxesCalculator.calculateTaxableIncome(MONTHLY_GROSS_SALARY, TAX_FREE_ALLOWANCE)).willReturn(new Amount("1083.33"));

        SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator(console, monthlyGrossSalaryCalculator, nationalInsuranceContributionCalculator, taxesCalculator);
        salarySlipGenerator.generateFor(employee);

        verify(console).printLine("Employee ID: 12345");
        verify(console).printLine("Employee Name: John J Doe");
        verify(console).printLine("Gross Salary: £2000.00");
        verify(console).printLine("National Insurance contributions: £159.40");
        verify(console).printLine("Tax-free allowance: £916.67");
        verify(console).printLine("Taxable income: £1083.33");
    }
}
