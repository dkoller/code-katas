package es.rachelcarmena;

import java.math.BigDecimal;

public class AnnualGrossSalary {
    protected BigDecimal value;

    public AnnualGrossSalary(int annualGrossSalary) {
        this.value = new BigDecimal(annualGrossSalary);
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
