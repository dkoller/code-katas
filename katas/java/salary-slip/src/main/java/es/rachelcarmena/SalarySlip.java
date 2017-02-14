package es.rachelcarmena;

import java.math.BigDecimal;

public class SalarySlip {

	private int id;
	private String name;
	private MonthlyGrossSalary monthlyGrossSalary;
	private BigDecimal nationalContributions;
	private BigDecimal taxFreeAllowance;
	private BigDecimal taxableIncome;
	private BigDecimal taxPayable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MonthlyGrossSalary getMonthlyGrossSalary() {
		return monthlyGrossSalary;
	}

	public void setMonthlyGrossSalary(MonthlyGrossSalary monthlyGrossSalary) {
		this.monthlyGrossSalary = monthlyGrossSalary;
	}

	public BigDecimal getNationalContributions() {
		return nationalContributions;
	}

	public void setNationalContributions(BigDecimal nationalContributions) {
		this.nationalContributions = nationalContributions;
	}

	public BigDecimal getTaxFreeAllowance() {
		return taxFreeAllowance;
	}

	public void setTaxFreeAllowance(BigDecimal taxFreeAllowance) {
		this.taxFreeAllowance = taxFreeAllowance;
	}

	public BigDecimal getTaxableIncome() {
		return taxableIncome;
	}

	public void setTaxableIncome(BigDecimal taxableIncome) {
		this.taxableIncome = taxableIncome;
	}

	public BigDecimal getTaxPayable() {
		return taxPayable;
	}

	public void setTaxPayable(BigDecimal taxPayable) {
		this.taxPayable = taxPayable;
	}
}