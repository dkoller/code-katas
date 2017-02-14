package es.rachelcarmena;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AnnualGrossSalary {

	private BigDecimal value;

	public AnnualGrossSalary(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public MonthlyGrossSalary toMonthlyGrossSalary() {
		return new MonthlyGrossSalary(value.divide(new BigDecimal("12.00"), 2, RoundingMode.HALF_EVEN));
	}

	public BigDecimal subtract(BigDecimal subtrahend) {
		return value.subtract(subtrahend);
	}
}
