package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IEmployee;
import com.cg.dto.EmployeeDTO;
import com.cg.entity.Employee;

@Service
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	private IEmployee employeeDao;
	
	@Override
	public EmployeeDTO createEmployee(EmployeeDTO emp) {
		Employee createdEmployee = employeeDao.createEmployee(toEntity(emp));
		return toDto(createdEmployee);
	}
	
	@Override
	public EmployeeDTO getEmployee(int empid) {
		return toDto(employeeDao.getEmployee(empid));
	}
	
	@Override
	public List<EmployeeDTO> getAllEmployees() {
		return employeeDao.getAllEmployees().stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public String deleteEmployee(int empid) {
		return employeeDao.deleteEmployee(empid);
	}
	
	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO emp) {
		Employee updatedEmployee = employeeDao.updateEmployee(toEntity(emp));
		return toDto(updatedEmployee);
	}

	private EmployeeDTO toDto(Employee employee) {
		if (employee == null) {
			return null;
		}

		return new EmployeeDTO(
				employee.getEmpid(),
				employee.getNameString(),
				employee.getDobDate(),
				employee.getSalary());
	}

	private Employee toEntity(EmployeeDTO employeeDto) {
		if (employeeDto == null) {
			return null;
		}

		return new Employee(
				employeeDto.getEmpid(),
				employeeDto.getNameString(),
				employeeDto.getDobDate(),
				employeeDto.getSalary());
	}
}
