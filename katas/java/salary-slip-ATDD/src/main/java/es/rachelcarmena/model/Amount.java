package es.rachelcarmena.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Amount {
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    protected final BigDecimal value;

    public Amount(int amount) {
        BigDecimal value = new BigDecimal(amount);
        this.value = value.setScale(SCALE);
    }

    public Amount(BigDecimal amount) {
        this.value = amount;
    }

    public Amount(String amount) {
        this.value = new BigDecimal(amount);
    }

    public Amount subtract(Amount amount) {
        return new Amount(this.value.subtract(amount.value));
    }

    public Amount min(Amount amount) {
        return new Amount(this.value.min(amount.value));
    }

    public Amount perMonth() {
        BigDecimal perMonth = value.divide(BigDecimal.valueOf(12), SCALE , ROUNDING_MODE);
        return new Amount(perMonth);
    }

    public Amount add(Amount amount) {
        return new Amount(this.value.add(amount.value));
    }

    public boolean greaterThanZero() {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    public Amount calculatePercentage(int percentage) {
        return new Amount(
                value.multiply(BigDecimal.valueOf(percentage)).divide(BigDecimal.valueOf(100), SCALE, ROUNDING_MODE));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Amount)
            return value.equals(((Amount) obj).value);
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public String toString() {
        return value.setScale(SCALE).toString();
    }
}