package es.rachelcarmena.acceptance;

import es.rachelcarmena.Employee;
import es.rachelcarmena.SalarySlipGenerator;
import es.rachelcarmena.SalarySlipPrinter;
import es.rachelcarmena.calculator.MonthlyGrossSalaryCalculator;
import es.rachelcarmena.calculator.NationalInsuranceContributionCalculator;
import es.rachelcarmena.calculator.TaxFreeAllowanceCalculator;
import es.rachelcarmena.calculator.TaxableIncomeCalculator;
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

    @Mock
    SalarySlipPrinter salarySlipPrinter;
    @Mock
    MonthlyGrossSalaryCalculator monthlyGrossSalaryCalculator;
    @Mock
    NationalInsuranceContributionCalculator nationalInsuranceContributionCalculator;
    @Mock
    TaxFreeAllowanceCalculator taxFreeAllowanceCalculator;
    @Mock
    TaxableIncomeCalculator taxableIncomeCalculator;


    @Test
    public void print_a_salary_slip_with_employee_details_for_an_employee() {
        final AnnualGrossSalary ANNUAL_GROSS_SALARY = new AnnualGrossSalary(24000);
        final MonthlyGrossSalary MONTHLY_GROSS_SALARY = new MonthlyGrossSalary(2000);
        final Amount TAX_FREE_ALLOWANCE = new Amount("916.67");
        Employee employee = new Employee(12345, "John J Doe", ANNUAL_GROSS_SALARY);
        given(monthlyGrossSalaryCalculator.calculate(ANNUAL_GROSS_SALARY)).willReturn(MONTHLY_GROSS_SALARY);
        given(nationalInsuranceContributionCalculator.calculate(ANNUAL_GROSS_SALARY)).willReturn(new Amount("159.40"));
        given(taxFreeAllowanceCalculator.calculate(MONTHLY_GROSS_SALARY)).willReturn(TAX_FREE_ALLOWANCE);
        given(taxableIncomeCalculator.calculate(MONTHLY_GROSS_SALARY, TAX_FREE_ALLOWANCE)).willReturn(new Amount("1083.33"));

        SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator(salarySlipPrinter, monthlyGrossSalaryCalculator, nationalInsuranceContributionCalculator, taxFreeAllowanceCalculator, taxableIncomeCalculator);
        salarySlipGenerator.generateFor(employee);

        verify(salarySlipPrinter).printLine("Employee ID: 12345");
        verify(salarySlipPrinter).printLine("Employee Name: John J Doe");
        verify(salarySlipPrinter).printLine("Gross Salary: £2000.00");
        verify(salarySlipPrinter).printLine("National Insurance contributions: £159.40");
        verify(salarySlipPrinter).printLine("Tax-free allowance: £916.67");
        verify(salarySlipPrinter).printLine("Taxable income: £1083.33");
    }
}
