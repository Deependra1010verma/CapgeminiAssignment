package com.cg.service;

import java.util.List;

import com.cg.entity.Employee;

public interface IEmployeeService {
	public Employee createEmployee(Employee emp);
	public Employee getEmployee(int empid);
	public List<Employee> getAllEmployees();
	public String deleteEmployee(int empid);
	public Employee updateEmployee(Employee emp);
}
