package es.rachelcarmena.model;

import es.rachelcarmena.type.AnnualGrossSalary;

public class Employee {
	private int id;
	private String name;
	private AnnualGrossSalary annualGrossSalary;

	public Employee(int id, String name, AnnualGrossSalary annualGrossSalary) {
		this.id = id;
		this.name = name;
		this.annualGrossSalary = annualGrossSalary;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public AnnualGrossSalary getAnnualGrossSalary() {
		return annualGrossSalary;
	}
}