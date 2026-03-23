package com.cg.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.EmployeeDTO;
import com.cg.exception.EmployeeNotFoundException;
import com.cg.service.IEmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {
	
	@Autowired
	private IEmployeeService employeeService;
	
	
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		List<EmployeeDTO> employees = employeeService.getAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	
	@GetMapping("/{empid}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int empid) {
		EmployeeDTO employee = employeeService.getEmployee(empid);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee with ID " + empid + " not found");
		}

		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO emp) {
		EmployeeDTO createdEmployee = employeeService.createEmployee(emp);
		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{empid}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable int empid, @Valid @RequestBody EmployeeDTO emp) {
		emp.setEmpid(empid);
		EmployeeDTO updatedEmployee = employeeService.updateEmployee(emp);
		if (updatedEmployee == null) {
			throw new EmployeeNotFoundException("Employee with ID " + empid + " not found");
		}

		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{empid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int empid) {
		String result = employeeService.deleteEmployee(empid);
		if (!result.contains("deleted")) {
			throw new EmployeeNotFoundException(result);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@GetMapping("/demo/sample")
	public ResponseEntity<EmployeeDTO> getDemoEmployee() {
		EmployeeDTO emp = new EmployeeDTO(9999, "Demo User", LocalDate.of(1990, 1, 1), 50000.0);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
}
