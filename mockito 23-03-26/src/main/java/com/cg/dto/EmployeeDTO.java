package com.cg.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class EmployeeDTO {

	private int empid;
	@NotBlank(message = "Full name is required")
	@Pattern(regexp = "^[A-Za-z]+(?:[A-Za-z ]*[A-Za-z])?$", message = "Full name should contain only letters and spaces")
	private String nameString;
	@NotNull(message = "Date of birth is required")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dobDate;
	@NotNull(message = "Salary is required")
	@DecimalMin(value = "1000.0", message = "Salary must be at least 1000")
	private Double salary;

	public EmployeeDTO() {
	}

	public EmployeeDTO(int empid, String nameString, LocalDate dobDate, Double salary) {
		this.empid = empid;
		this.nameString = nameString;
		this.dobDate = dobDate;
		this.salary = salary;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public LocalDate getDobDate() {
		return dobDate;
	}

	public void setDobDate(LocalDate dobDate) {
		this.dobDate = dobDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
}
