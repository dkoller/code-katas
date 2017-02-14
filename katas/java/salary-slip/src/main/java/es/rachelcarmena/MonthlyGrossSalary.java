package es.rachelcarmena;

import java.math.BigDecimal;

public class MonthlyGrossSalary {

	private BigDecimal value;

	public MonthlyGrossSalary(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public BigDecimal subtract(BigDecimal subtrahend) {
		return value.subtract(subtrahend);
	}
}
