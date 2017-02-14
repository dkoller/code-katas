package es.rachelcarmena.type;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CustomCurrency {

	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	private static final int SCALE = 2;

	private BigDecimal value;

	public CustomCurrency(BigDecimal value) {
		this.value = value;
	}

	public CustomCurrency(String value) {
		this.value = new BigDecimal(value);
	}

	public CustomCurrency(CustomCurrency customCurrency) {
		this.value = customCurrency.value;
	}

	public CustomCurrency subtract(BigDecimal subtrahend) {
		return new CustomCurrency(value.subtract(subtrahend));
	}

	public CustomCurrency subtract(CustomCurrency subtrahend) {
		return new CustomCurrency(value.subtract(subtrahend.value));
	}

	public CustomCurrency perMonth() {
		return new CustomCurrency(value.divide(new BigDecimal("12.00"), SCALE, ROUNDING_MODE));
	}

	public CustomCurrency divide(CustomCurrency divisor) {
		return new CustomCurrency(value.divide(divisor.value, SCALE, ROUNDING_MODE));
	}

	public CustomCurrency calculatePercentage(byte percentage) {
		return new CustomCurrency(
				value.multiply(BigDecimal.valueOf(percentage)).divide(new BigDecimal("100.00"), SCALE, ROUNDING_MODE));
	}

	public boolean greaterThanZero() {
		return value.compareTo(BigDecimal.ZERO) > 0;
	}

	public CustomCurrency add(CustomCurrency augend) {
		return new CustomCurrency(value.add(augend.value));
	}

	public CustomCurrency min(CustomCurrency val) {
		return new CustomCurrency(value.min(val.value));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof CustomCurrency)
			return value.equals(((CustomCurrency) obj).value);
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
