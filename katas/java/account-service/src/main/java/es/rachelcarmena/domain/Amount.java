package es.rachelcarmena.domain;

import java.math.BigDecimal;

public class Amount {

    private BigDecimal amount;

    public Amount(int amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    public Amount(BigDecimal amount) {
        this.amount = amount;
    }

    public Amount toNegative() {
        return new Amount(- amount.intValue());
    }

    public Amount add(Amount value) {
        return new Amount(this.amount.add(value.amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Amount amount1 = (Amount) o;

        return amount != null ? amount.equals(amount1.amount) : amount1.amount == null;
    }

    @Override
    public int hashCode() {
        return amount != null ? amount.hashCode() : 0;
    }

    public String toPrintedString() {
        return amount.setScale(2).toString();
    }
}
