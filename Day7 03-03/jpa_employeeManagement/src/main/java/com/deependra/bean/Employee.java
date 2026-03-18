package com.deependra.bean;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {
	@Id
	private String empid;
	private String name; 
	private String email;
	private String dob;
	
	public Employee() {
		
	}
	
	public Employee(String name, String email, String dob) {
		this.name = name;
		this.email = email;
		this.dob = dob;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", email=" + email + ", dob=" + dob + "]";
	}
	
	
	
	
}
