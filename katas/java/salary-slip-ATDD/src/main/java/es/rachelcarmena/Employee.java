package es.rachelcarmena;

import es.rachelcarmena.model.AnnualGrossSalary;

public class Employee {

    protected int employeeID;
    protected String employeeName;
    protected AnnualGrossSalary annualGrossSalary;

    public Employee(int employeeID, String employeeName, AnnualGrossSalary annualGrossSalary) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.annualGrossSalary = annualGrossSalary;
    }
}
