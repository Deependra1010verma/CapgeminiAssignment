package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.entity.Employee;
import com.cg.exception.EmployeeNotFoundException;
import com.cg.repo.IEmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final IEmployeeRepo employeeRepo;

	public EmployeeServiceImpl(IEmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployee(Long id) {
		return employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		Employee existingEmployee = getEmployee(id);
		existingEmployee.setName(employee.getName());
		existingEmployee.setEmail(employee.getEmail());
		existingEmployee.setDepartment(employee.getDepartment());
		existingEmployee.setSalary(employee.getSalary());
		existingEmployee.setJoinedDate(employee.getJoinedDate());
		return employeeRepo.save(existingEmployee);
	}

	@Override
	public void removeEmployee(Long id) {
		Employee existingEmployee = getEmployee(id);
		employeeRepo.delete(existingEmployee);
	}
}
