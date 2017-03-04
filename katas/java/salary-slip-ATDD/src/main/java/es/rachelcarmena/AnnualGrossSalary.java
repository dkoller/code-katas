package es.rachelcarmena;

import java.math.BigDecimal;

public class AnnualGrossSalary {
    protected BigDecimal value;

    public AnnualGrossSalary(int annualGrossSalary) {
        this.value = new BigDecimal(annualGrossSalary);
    }

    public MonthlyGrossSalary toMonthlyGrossSalary() {
        BigDecimal perMonth = value.divide(new BigDecimal(12));
        return new MonthlyGrossSalary(perMonth);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) return false;
        return ((AnnualGrossSalary) obj).value.equals(this.value);
    }
}
