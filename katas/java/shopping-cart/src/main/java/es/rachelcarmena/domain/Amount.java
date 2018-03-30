package es.rachelcarmena.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Amount {
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    protected final BigDecimal value;

    public Amount(BigDecimal amount) {
        this.value = amount;
    }

    public Amount add(Amount amount) {
        return new Amount(value.add(amount.value));
    }

    public Amount calculatePercentage(int percentage) {
        BigDecimal amount = value.multiply(BigDecimal.valueOf(percentage));
        amount = amount.divide(BigDecimal.valueOf(100), SCALE, ROUNDING_MODE);
        return new Amount(amount);
    }

    public static Amount valueOf(double amount) {
        BigDecimal result = BigDecimal.valueOf(amount).setScale(SCALE);
        return new Amount(result);
    }

    public String print() {
        return value.setScale(SCALE).toString();
    }

    public Amount multiply(int number) {
        return new Amount(value.multiply(BigDecimal.valueOf(number)));
    }

    public Amount subtract(Amount anotherAmount) {
        return new Amount(value.subtract(anotherAmount.value));
    }
}
