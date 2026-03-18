package com.cg.service;

import java.util.List;

import com.cg.entity.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployee(Long id);

	Employee updateEmployee(Long id, Employee employee);

	void removeEmployee(Long id);
}
