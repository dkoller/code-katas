package es.rachelcarmena.model;

import java.math.BigDecimal;

public class AnnualGrossSalary extends Amount {

    public AnnualGrossSalary(int amount) {
        super(amount);
    }

    public AnnualGrossSalary(String amount) {
        super(amount);
    }

    public MonthlyGrossSalary toMonthlyGrossSalary() {
        BigDecimal perMonth = value.divide(new BigDecimal(12));
        return new MonthlyGrossSalary(perMonth);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) return false;
        return ((AnnualGrossSalary) obj).value.equals(this.value);
    }
}