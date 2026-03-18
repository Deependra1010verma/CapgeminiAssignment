package com.cg.entity;

import java.time.LocalDate;

public class Employee {
	private int empid;
	private String nameString;
	private LocalDate dobDate;
	private double salary;
	
	public Employee() {
	}
	
	public Employee(int empid, String nameString, LocalDate dobDate, double salary) {
		this.empid = empid;
		this.nameString = nameString;
		this.dobDate = dobDate;
		this.salary = salary;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public LocalDate getDobDate() {
		return dobDate;
	}
	public void setDobDate(LocalDate dobDate) {
		this.dobDate = dobDate;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", nameString=" + nameString + ", dobDate=" + dobDate
				+ ", salary=" + salary + "]";
	}

}
