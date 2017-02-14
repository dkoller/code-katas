package es.rachelcarmena.type;

public class AnnualGrossSalary extends CustomCurrency {

	public AnnualGrossSalary(String value) {
		super(value);
	}

	public MonthlyGrossSalary toMonthlyGrossSalary() {
		return new MonthlyGrossSalary(this);
	}
}
