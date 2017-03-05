package es.rachelcarmena.unit;

import es.rachelcarmena.Employee;
import es.rachelcarmena.SalarySlipGenerator;
import es.rachelcarmena.SalarySlipPrinter;
import es.rachelcarmena.calculator.MonthlyGrossSalaryCalculator;
import es.rachelcarmena.calculator.NationalInsuranceContributionCalculator;
import es.rachelcarmena.calculator.TaxesCalculator;
import es.rachelcarmena.model.Amount;
import es.rachelcarmena.model.AnnualGrossSalary;
import es.rachelcarmena.model.MonthlyGrossSalary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
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
    TaxesCalculator taxesCalculator;

    private SalarySlipGenerator salarySlipGenerator;
    private Employee employee;

    @Before
    public void initialize() {
        final AnnualGrossSalary ANNUAL_GROSS_SALARY = new AnnualGrossSalary(24000);
        final MonthlyGrossSalary MONTHLY_GROSS_SALARY = new MonthlyGrossSalary(2000);
        final Amount NATIONAL_INSURANCE_CONTRIBUTION = new Amount("159.40");
        final Amount TAX_FREE_ALLOWANCE = new Amount("916.67");
        final Amount TAXABLE_INCOME = new Amount("1083.33");

        employee = new Employee(12345, "John J Doe", ANNUAL_GROSS_SALARY);

        given(monthlyGrossSalaryCalculator.calculate(any(AnnualGrossSalary.class))).willReturn(MONTHLY_GROSS_SALARY);
        given(nationalInsuranceContributionCalculator.calculate(any(AnnualGrossSalary.class))).willReturn(NATIONAL_INSURANCE_CONTRIBUTION);
        given(taxesCalculator.calculateFreeAllowance(any(MonthlyGrossSalary.class))).willReturn(TAX_FREE_ALLOWANCE);
        given(taxesCalculator.calculateTaxableIncome(any(MonthlyGrossSalary.class), any(Amount.class))).willReturn(TAXABLE_INCOME);

        salarySlipGenerator = new SalarySlipGenerator(salarySlipPrinter, monthlyGrossSalaryCalculator, nationalInsuranceContributionCalculator, taxesCalculator);
    }

    @Test
    public void print_employeeID() {
        salarySlipGenerator.generateFor(employee);

        verify(salarySlipPrinter).printLine("Employee ID: 12345");
    }

    @Test
    public void print_employeeName() {
        salarySlipGenerator.generateFor(employee);

        verify(salarySlipPrinter).printLine("Employee Name: John J Doe");
    }

    @Test
    public void print_monthly_salary() {
        salarySlipGenerator.generateFor(employee);

        verify(salarySlipPrinter).printLine("Gross Salary: £2000.00");
    }

    @Test
    public void print_national_insurance_contributions() {
        salarySlipGenerator.generateFor(employee);

        verify(salarySlipPrinter).printLine("National Insurance contributions: £159.40");
    }

    @Test
    public void print_tax_free_allowance() {
        salarySlipGenerator.generateFor(employee);

        verify(salarySlipPrinter).printLine("Tax-free allowance: £916.67");
    }

    @Test
    public void print_tax_payable() {
        salarySlipGenerator.generateFor(employee);

        verify(salarySlipPrinter).printLine("Taxable income: £1083.33");
    }
}
