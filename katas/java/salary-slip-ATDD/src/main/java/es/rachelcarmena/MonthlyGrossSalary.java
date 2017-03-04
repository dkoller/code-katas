package es.rachelcarmena;

import java.math.BigDecimal;

public class MonthlyGrossSalary extends Amount {
    private final BigDecimal value;

    public MonthlyGrossSalary(int monthlyGrossSalary) {
        this.value = new BigDecimal(monthlyGrossSalary);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
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
