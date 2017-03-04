package es.rachelcarmena;

import java.math.BigDecimal;

public class MonthlyGrossSalary extends Amount {
    private final BigDecimal value;

    public MonthlyGrossSalary(int monthlyGrossSalary) {
        this.value = new BigDecimal(monthlyGrossSalary);
    }

    @Override
    public String toString() {
        return value.setScale(2).toString();
    }
}
