package es.rachelcarmena;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Amount {
    private static final int SCALE = 1;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    private BigDecimal value;

    public Amount(BigDecimal value) {
        this.value = value;
    }

    public static Amount valueOf(double value) {
        return new Amount(BigDecimal.valueOf(value));
    }

    public Amount add(Amount amount) {
        return new Amount(value.add(amount.value));
    }

    public Amount subtract(Amount amount) {
        return new Amount(value.subtract(amount.value));
    }

    public Amount multiply(int amount) {
        return new Amount(value.multiply(BigDecimal.valueOf(amount)));
    }

    private Amount calculatePercentage(int percentage) {
        BigDecimal amount = value.multiply(BigDecimal.valueOf(percentage));
        amount = amount.divide(BigDecimal.valueOf(100), SCALE, ROUNDING_MODE);
        return new Amount(amount);
    }

    public Amount applyDiscount(int percentage) {
        Amount discount = calculatePercentage(percentage);
        return subtract(discount);
    }

    public Amount min(Amount amount) {
        return new Amount(amount.value.min(this.value));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Amount)
            return value.equals(((Amount) obj).value);
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}