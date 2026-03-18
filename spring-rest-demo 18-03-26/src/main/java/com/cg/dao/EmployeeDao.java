package com.cg.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cg.entity.Employee;

@Repository
public class EmployeeDao implements IEmployee {
	
	private static List<Employee> employeeList = new ArrayList<>();
	private static int empIdCounter = 1002;
	
	static {
		employeeList.add(new Employee(1001, "Rajesh Kumar", java.time.LocalDate.of(1990, 5, 15), 50000));
		employeeList.add(new Employee(1002, "Priya Singh", java.time.LocalDate.of(1992, 8, 22), 55000));
	}
	
	@Override
	public Employee createEmployee(Employee emp) {
		// Generate ID if not provided or is 0
		if (emp.getEmpid() == 0) {
			emp.setEmpid(++empIdCounter);
		} else {
			empIdCounter = Math.max(empIdCounter, emp.getEmpid());
		}
		employeeList.add(emp);
		return emp;
	}
	
	@Override
	public Employee getEmployee(int empid) {
		Optional<Employee> employee = employeeList.stream()
				.filter(e -> e.getEmpid() == empid)
				.findFirst();
		return employee.orElse(null);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return new ArrayList<>(employeeList);
	}
	
	@Override
	public String deleteEmployee(int empid) {
		Employee emp = getEmployee(empid);
		if (emp != null) {
			employeeList.remove(emp);
			return "Employee with ID " + empid + " deleted successfully";
		}
		return "Employee with ID " + empid + " not found";
	}
	
	@Override
	public Employee updateEmployee(Employee emp) {
		Employee existingEmp = getEmployee(emp.getEmpid());
		if (existingEmp != null) {
			existingEmp.setNameString(emp.getNameString());
			existingEmp.setDobDate(emp.getDobDate());
			existingEmp.setSalary(emp.getSalary());
			return existingEmp;
		}
		return null;
	}
}
