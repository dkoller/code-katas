package es.rachelcarmena.generator;

import es.rachelcarmena.model.Employee;
import es.rachelcarmena.model.SalarySlip;
import es.rachelcarmena.type.AnnualGrossSalary;
import es.rachelcarmena.type.CustomCurrency;
import es.rachelcarmena.type.MonthlyGrossSalary;

public class SalarySlipGenerator {

	public SalarySlip generateFor(Employee employee) {
		AnnualGrossSalary annualGrossSalary = employee.getAnnualGrossSalary();
		MonthlyGrossSalary monthlyGrossSalary = annualGrossSalary.toMonthlyGrossSalary();

		CustomCurrency nationalContributions = calculateNationalContributions(annualGrossSalary);
		CustomCurrency taxFreeAllowance = calculateTaxFreeAllowance(monthlyGrossSalary);
		CustomCurrency taxableIncome = calculateTaxableIncome(monthlyGrossSalary, taxFreeAllowance);

		SalarySlip salarySip = new SalarySlip();
		salarySip.setId(employee.getId());
		salarySip.setName(employee.getName());
		salarySip.setMonthlyGrossSalary(monthlyGrossSalary);
		salarySip.setNationalContributions(nationalContributions);
		salarySip.setTaxFreeAllowance(taxFreeAllowance);
		salarySip.setTaxableIncome(taxableIncome);
		return salarySip;
	}

	private CustomCurrency calculateNationalContributions(AnnualGrossSalary salary) {
		final CustomCurrency MAX_LIMIT_NO_CONTRIBUTIONS = new CustomCurrency("8060.00");
		final CustomCurrency MAX_LIMIT_BASIC_CONTRIBUTIONS = new CustomCurrency("43000.00");
		final byte BASIC_RATE = 12;
		final byte HIGHER_RATE = 2;

		CustomCurrency basicExcess = salary.subtract(MAX_LIMIT_NO_CONTRIBUTIONS);
		boolean hasBasicExcess = basicExcess.greaterThanZero();
		if (!hasBasicExcess)
			return new CustomCurrency("0.00");

		CustomCurrency higherExcess = salary.subtract(MAX_LIMIT_BASIC_CONTRIBUTIONS);
		boolean hasHigherExcess = higherExcess.greaterThanZero();
		if (!hasHigherExcess)
			return basicExcess.calculatePercentage(BASIC_RATE).perMonth();

		CustomCurrency rangeBasicContributions = MAX_LIMIT_BASIC_CONTRIBUTIONS.subtract(MAX_LIMIT_NO_CONTRIBUTIONS);
		CustomCurrency contributions = rangeBasicContributions.calculatePercentage(BASIC_RATE);
		contributions = contributions.add(higherExcess.calculatePercentage(HIGHER_RATE));
		return contributions.perMonth();
	}

	private CustomCurrency calculateTaxFreeAllowance(MonthlyGrossSalary monthlyGrossSalary) {
		final CustomCurrency MAX_LIMIT_FREE_BAND = new CustomCurrency("11000.00");

		CustomCurrency freeBandPerMonth = MAX_LIMIT_FREE_BAND.perMonth();
		return freeBandPerMonth.min(monthlyGrossSalary);
	}

	private CustomCurrency calculateTaxableIncome(MonthlyGrossSalary monthlyGrossSalary,
			CustomCurrency taxFreeAllowance) {
		return monthlyGrossSalary.subtract(taxFreeAllowance);
	}
}