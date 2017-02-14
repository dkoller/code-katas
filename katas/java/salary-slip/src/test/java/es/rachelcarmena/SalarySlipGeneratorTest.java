package es.rachelcarmena;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import es.rachelcarmena.generator.SalarySlipGenerator;
import es.rachelcarmena.model.Employee;
import es.rachelcarmena.model.SalarySlip;
import es.rachelcarmena.type.AnnualGrossSalary;
import es.rachelcarmena.type.CustomCurrency;

public class SalarySlipGeneratorTest {

	private static SalarySlipGenerator salarySlipGenerator;

	@BeforeClass
	public static void beforeClass() {
		salarySlipGenerator = new SalarySlipGenerator();
	}

	@Test
	public void generateSlip_no_contributions_and_personal_allowance_band() {
		Employee employee = createEmployeeFor("5000.00");
		SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

		assertEquals(new CustomCurrency("0.00"), salarySlip.getNationalContributions());
		assertEquals(salarySlip.getMonthlyGrossSalary(), salarySlip.getTaxFreeAllowance());
		assertEquals(new CustomCurrency("0.00"), salarySlip.getTaxableIncome());
	}

	@Test
	public void generateSlip_basic_contributions_and_personal_allowance_band() {
		Employee employee = createEmployeeFor("9060.00");
		SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

		assertEquals(new CustomCurrency("10.00"), salarySlip.getNationalContributions());
		assertEquals(salarySlip.getMonthlyGrossSalary(), salarySlip.getTaxFreeAllowance());
		assertEquals(new CustomCurrency("0.00"), salarySlip.getTaxableIncome());
	}

	@Test
	public void generateSlip_higher_contributions_and_personal_allowance_band() {
		Employee employee = createEmployeeFor("45000.00");
		SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

		assertEquals(new CustomCurrency("352.73"), salarySlip.getNationalContributions());
		assertEquals(new CustomCurrency("916.67"), salarySlip.getTaxFreeAllowance());
		assertEquals(new CustomCurrency("2833.33"), salarySlip.getTaxableIncome());
	}

	private Employee createEmployeeFor(String annualGrossSalary) {
		return new Employee(1, "Guillem", new AnnualGrossSalary(annualGrossSalary));
	}
}