package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.dao.IEmployee;
import com.cg.dto.EmployeeDTO;
import com.cg.entity.Employee;
import com.cg.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
class SpringRestDemoApplicationTests {

	@Mock
	private IEmployee employeeDao;

	@InjectMocks
	private EmployeeService employeeService;

	@Test
	void testCreateEmployee() {
		EmployeeDTO employeeDto = new EmployeeDTO(1003, "Raghav", LocalDate.of(2002, 10, 12), 23400.00);
		Employee createdEmployee = new Employee(1003, "Raghav", LocalDate.of(2002, 10, 12), 23400.00);

		when(employeeDao.createEmployee(org.mockito.ArgumentMatchers.any(Employee.class))).thenReturn(createdEmployee);

		EmployeeDTO result = employeeService.createEmployee(employeeDto);

		assertNotNull(result);
		assertEquals(1003, result.getEmpid());
		assertEquals("Raghav", result.getNameString());
		assertEquals(LocalDate.of(2002, 10, 12), result.getDobDate());
		assertEquals(23400.00, result.getSalary());
		verify(employeeDao).createEmployee(org.mockito.ArgumentMatchers.any(Employee.class));
	}

	@Test
	void testGetEmployeeById() {
		Employee employee = new Employee(1001, "Rajesh Kumar", LocalDate.of(1990, 5, 15), 50000.00);

		when(employeeDao.getEmployee(1001)).thenReturn(employee);

		EmployeeDTO result = employeeService.getEmployee(1001);

		assertNotNull(result);
		assertEquals(1001, result.getEmpid());
		assertEquals("Rajesh Kumar", result.getNameString());
		assertEquals(LocalDate.of(1990, 5, 15), result.getDobDate());
		assertEquals(50000.00, result.getSalary());
		verify(employeeDao).getEmployee(1001);
	}

	@Test
	void testGetEmployeeByIdWhenEmployeeDoesNotExist() {
		when(employeeDao.getEmployee(9999)).thenReturn(null);

		EmployeeDTO result = employeeService.getEmployee(9999);

		assertNull(result);
		verify(employeeDao).getEmployee(9999);
	}

	@Test
	void testGetAllEmployees() {
		List<Employee> employees = List.of(
				new Employee(1001, "Rajesh Kumar", LocalDate.of(1990, 5, 15), 50000.00),
				new Employee(1002, "Priya Singh", LocalDate.of(1992, 8, 22), 55000.00));

		when(employeeDao.getAllEmployees()).thenReturn(employees);

		List<EmployeeDTO> result = employeeService.getAllEmployees();

		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Rajesh Kumar", result.get(0).getNameString());
		assertEquals("Priya Singh", result.get(1).getNameString());
		assertTrue(result.stream().allMatch(employee -> employee instanceof EmployeeDTO));
		verify(employeeDao).getAllEmployees();
	}

	@Test
	void testDeleteEmployee() {
		when(employeeDao.deleteEmployee(1001)).thenReturn("Employee with ID 1001 deleted successfully");

		String result = employeeService.deleteEmployee(1001);

		assertEquals("Employee with ID 1001 deleted successfully", result);
		verify(employeeDao).deleteEmployee(1001);
	}

	@Test
	void testUpdateEmployee() {
		EmployeeDTO employeeDto = new EmployeeDTO(1002, "Priya Sharma", LocalDate.of(1992, 8, 22), 60000.00);
		Employee updatedEmployee = new Employee(1002, "Priya Sharma", LocalDate.of(1992, 8, 22), 60000.00);

		when(employeeDao.updateEmployee(org.mockito.ArgumentMatchers.any(Employee.class))).thenReturn(updatedEmployee);

		EmployeeDTO result = employeeService.updateEmployee(employeeDto);

		assertNotNull(result);
		assertEquals(1002, result.getEmpid());
		assertEquals("Priya Sharma", result.getNameString());
		assertEquals(LocalDate.of(1992, 8, 22), result.getDobDate());
		assertEquals(60000.00, result.getSalary());
		verify(employeeDao).updateEmployee(org.mockito.ArgumentMatchers.any(Employee.class));
	}

	@Test
	void testUpdateEmployeeWhenEmployeeDoesNotExist() {
		EmployeeDTO employeeDto = new EmployeeDTO(9999, "Unknown", LocalDate.of(1995, 1, 1), 30000.00);

		when(employeeDao.updateEmployee(org.mockito.ArgumentMatchers.any(Employee.class))).thenReturn(null);

		EmployeeDTO result = employeeService.updateEmployee(employeeDto);

		assertNull(result);
		verify(employeeDao).updateEmployee(org.mockito.ArgumentMatchers.any(Employee.class));
	}
}
