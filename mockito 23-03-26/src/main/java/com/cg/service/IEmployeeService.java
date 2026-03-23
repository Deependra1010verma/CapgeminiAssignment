package com.cg.service;

import java.util.List;

import com.cg.dto.EmployeeDTO;

public interface IEmployeeService {
	public EmployeeDTO createEmployee(EmployeeDTO emp);
	public EmployeeDTO getEmployee(int empid);
	public List<EmployeeDTO> getAllEmployees();
	public String deleteEmployee(int empid);
	public EmployeeDTO updateEmployee(EmployeeDTO emp);
}
