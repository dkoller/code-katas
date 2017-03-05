package es.rachelcarmena;

import es.rachelcarmena.model.AnnualGrossSalary;

public class Employee {

    protected final int employeeID;
    protected final String employeeName;
    protected final AnnualGrossSalary annualGrossSalary;

    public Employee(int employeeID, String employeeName, AnnualGrossSalary annualGrossSalary) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.annualGrossSalary = annualGrossSalary;
    }
}
