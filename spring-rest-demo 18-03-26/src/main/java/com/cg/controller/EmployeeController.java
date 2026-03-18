package com.cg.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Employee;
import com.cg.service.IEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService employeeService;
	
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	
	@GetMapping("/{empid}")
	public ResponseEntity<?> getEmployeeById(@PathVariable int empid) {
		Employee employee = employeeService.getEmployee(empid);
		if (employee != null) {
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Employee with ID " + empid + " not found", HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) {
		Employee createdEmployee = employeeService.createEmployee(emp);
		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{empid}")
	public ResponseEntity<?> updateEmployee(@PathVariable int empid, @RequestBody Employee emp) {
		emp.setEmpid(empid);
		Employee updatedEmployee = employeeService.updateEmployee(emp);
		if (updatedEmployee != null) {
			return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Employee with ID " + empid + " not found", HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/{empid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int empid) {
		String result = employeeService.deleteEmployee(empid);
		if (result.contains("deleted")) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/demo/sample")
	public ResponseEntity<Employee> getDemoEmployee() {
		Employee emp = new Employee(9999, "Demo User", LocalDate.of(1990, 1, 1), 50000);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
}
