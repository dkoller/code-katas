package es.rachelcarmena.model;

import java.math.BigDecimal;

public class Amount {
    protected final BigDecimal value;

    public Amount(int amount) {
        this.value = new BigDecimal(amount);
    }

    public Amount(BigDecimal amount) {
        this.value = amount;
    }

    public Amount(String amount) {
        this.value = new BigDecimal(amount);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
