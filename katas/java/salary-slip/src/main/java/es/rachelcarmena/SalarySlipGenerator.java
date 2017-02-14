package es.rachelcarmena;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalarySlipGenerator {

	public SalarySlip generateFor(Employee employee) {
		AnnualGrossSalary annualGrossSalary = employee.getAnnualGrossSalary();
		MonthlyGrossSalary monthlyGrossSalary = annualGrossSalary.toMonthlyGrossSalary();

		BigDecimal nationalContributions = calculateNationalContributions(annualGrossSalary);
		BigDecimal taxFreeAllowance = calculateTaxFreeAllowance(monthlyGrossSalary);
		BigDecimal taxableIncome = calculateTaxableIncome(monthlyGrossSalary, taxFreeAllowance);

		SalarySlip salarySip = new SalarySlip();
		salarySip.setId(employee.getId());
		salarySip.setName(employee.getName());
		salarySip.setMonthlyGrossSalary(monthlyGrossSalary);
		salarySip.setNationalContributions(nationalContributions);
		salarySip.setTaxFreeAllowance(taxFreeAllowance);
		salarySip.setTaxableIncome(taxableIncome);
		return salarySip;
	}

	private BigDecimal calculateNationalContributions(AnnualGrossSalary salary) {
		final BigDecimal MAX_LIMIT_NO_CONTRIBUTIONS = new BigDecimal("8060.00");
		final BigDecimal MAX_LIMIT_BASIC_CONTRIBUTIONS = new BigDecimal("43000.00");
		final byte BASIC_RATE = 12;
		final byte HIGHER_RATE = 2;

		BigDecimal basicExcess = salary.subtract(MAX_LIMIT_NO_CONTRIBUTIONS);
		boolean hasBasicExcess = basicExcess.compareTo(BigDecimal.ZERO) > 0;
		if (!hasBasicExcess)
			return new BigDecimal("0.00");

		BigDecimal higherExcess = salary.subtract(MAX_LIMIT_BASIC_CONTRIBUTIONS);
		boolean hasHigherExcess = higherExcess.compareTo(BigDecimal.ZERO) > 0;
		if (!hasHigherExcess)
			return perMonth(calculatePercentage(BASIC_RATE, basicExcess));

		BigDecimal rangeBasicContributions = MAX_LIMIT_BASIC_CONTRIBUTIONS.subtract(MAX_LIMIT_NO_CONTRIBUTIONS);
		BigDecimal contributions = calculatePercentage(BASIC_RATE, rangeBasicContributions);
		contributions = contributions.add(calculatePercentage(HIGHER_RATE, higherExcess));
		return perMonth(contributions);
	}

	private BigDecimal calculateTaxFreeAllowance(MonthlyGrossSalary monthlyGrossSalary) {
		final BigDecimal MAX_LIMIT_FREE_BAND = new BigDecimal("11000.00");

		BigDecimal freeBandPerMonth = perMonth(MAX_LIMIT_FREE_BAND);
		return freeBandPerMonth.min(monthlyGrossSalary.getValue());
	}

	private BigDecimal calculateTaxableIncome(MonthlyGrossSalary monthlyGrossSalary, BigDecimal taxFreeAllowance) {
		return monthlyGrossSalary.subtract(taxFreeAllowance);
	}

	private BigDecimal perMonth(BigDecimal value) {
		return divide(value, new BigDecimal("12.00"));
	}

	private BigDecimal calculatePercentage(byte percentage, BigDecimal value) {
		return divide(value.multiply(BigDecimal.valueOf(percentage)), new BigDecimal("100.00"));
	}

	private BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
		return dividend.divide(divisor, 2, RoundingMode.HALF_EVEN);
	}
}