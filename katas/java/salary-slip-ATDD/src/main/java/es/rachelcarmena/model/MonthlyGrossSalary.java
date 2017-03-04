package es.rachelcarmena.model;

import java.math.BigDecimal;

public class MonthlyGrossSalary extends Amount {
    public MonthlyGrossSalary(int amount) {
        super(amount);
    }

    public MonthlyGrossSalary(BigDecimal amount) {
        super(amount);
    }

    public MonthlyGrossSalary(String amount) {
        super(amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) return false;
        return ((MonthlyGrossSalary) obj).value.equals(this.value);
    }

    @Override
    public String toString() {
        return value.setScale(2).toString();
    }
}