package es.rachelcarmena.model;

import es.rachelcarmena.type.CustomCurrency;
import es.rachelcarmena.type.MonthlyGrossSalary;

public class SalarySlip {

	private int id;
	private String name;
	private MonthlyGrossSalary monthlyGrossSalary;
	private CustomCurrency nationalContributions;
	private CustomCurrency taxFreeAllowance;
	private CustomCurrency taxableIncome;
	private CustomCurrency taxPayable;

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

	public CustomCurrency getNationalContributions() {
		return nationalContributions;
	}

	public void setNationalContributions(CustomCurrency nationalContributions2) {
		this.nationalContributions = nationalContributions2;
	}

	public CustomCurrency getTaxFreeAllowance() {
		return taxFreeAllowance;
	}

	public void setTaxFreeAllowance(CustomCurrency taxFreeAllowance2) {
		this.taxFreeAllowance = taxFreeAllowance2;
	}

	public CustomCurrency getTaxableIncome() {
		return taxableIncome;
	}

	public void setTaxableIncome(CustomCurrency taxableIncome2) {
		this.taxableIncome = taxableIncome2;
	}

	public CustomCurrency getTaxPayable() {
		return taxPayable;
	}

	public void setTaxPayable(CustomCurrency taxPayable) {
		this.taxPayable = taxPayable;
	}
}