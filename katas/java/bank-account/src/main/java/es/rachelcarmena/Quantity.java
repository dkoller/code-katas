package es.rachelcarmena;

import java.math.BigDecimal;

public class Quantity {

	private BigDecimal value;

	public Quantity(String value) {
		this.value = new BigDecimal(value);
	}

	private Quantity(BigDecimal value) {
		this.value = value;
	}

	public Quantity add(Quantity augend) {
		BigDecimal augendValue = augend.value;
		return new Quantity(value.add(augendValue));
	}

	public Quantity subtract(Quantity subtrahend) {
		BigDecimal subtrahendValue = subtrahend.value;
		return new Quantity(value.subtract(subtrahendValue));
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
