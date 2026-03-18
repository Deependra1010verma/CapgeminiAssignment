package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IEmployee;
import com.cg.entity.Employee;

@Service
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	private IEmployee employeeDao;
	
	@Override
	public Employee createEmployee(Employee emp) {
		return employeeDao.createEmployee(emp);
	}
	
	@Override
	public Employee getEmployee(int empid) {
		return employeeDao.getEmployee(empid);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}
	
	@Override
	public String deleteEmployee(int empid) {
		return employeeDao.deleteEmployee(empid);
	}
	
	@Override
	public Employee updateEmployee(Employee emp) {
		return employeeDao.updateEmployee(emp);
	}
}
