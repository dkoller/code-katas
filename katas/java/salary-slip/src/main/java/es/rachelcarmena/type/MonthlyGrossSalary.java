package es.rachelcarmena.type;

public class MonthlyGrossSalary extends CustomCurrency {

	public MonthlyGrossSalary(AnnualGrossSalary annualGrossSalary) {
		super(annualGrossSalary.perMonth());
	}
}
