package com.cg;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.controller.EmployeeController;
import com.cg.dto.EmployeeDTO;
import com.cg.exception.GlobalExceptionHandler;
import com.cg.service.IEmployeeService;

import jakarta.annotation.Resource;

@WebMvcTest(EmployeeController.class)
@Import({ GlobalExceptionHandler.class, EmployeeControllerTest.ControllerTestConfig.class })
class EmployeeControllerTest {

	@Resource
	private MockMvc mockMvc;

	@Resource(name = "fakeEmployeeService")
	private FakeEmployeeService fakeService;

	@BeforeEach
	void setUp() {
		fakeService.reset();
	}

	@Test
	void testGetAllEmployees() throws Exception {
		fakeService.setAllEmployees(List.of(
				new EmployeeDTO(1001, "Rajesh Kumar", LocalDate.of(1990, 5, 15), 50000.0),
				new EmployeeDTO(1002, "Priya Singh", LocalDate.of(1992, 8, 22), 55000.0)));

		mockMvc.perform(get("/api/employees"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[0].empid").value(1001))
				.andExpect(jsonPath("$[1].nameString").value("Priya Singh"));
	}

	@Test
	void testGetEmployeeById() throws Exception {
		fakeService.setGetEmployeeResult(new EmployeeDTO(1001, "Rajesh Kumar", LocalDate.of(1990, 5, 15), 50000.0));

		mockMvc.perform(get("/api/employees/1001"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.empid").value(1001))
				.andExpect(jsonPath("$.nameString").value("Rajesh Kumar"))
				.andExpect(jsonPath("$.dobDate").value("15-05-1990"));
	}

	@Test
	void testGetEmployeeByIdWhenNotFound() throws Exception {
		fakeService.setGetEmployeeResult(null);

		mockMvc.perform(get("/api/employees/9999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value("Employee with ID 9999 not found"));
	}

	@Test
	void testUpdateEmployee() throws Exception {
		fakeService.setUpdateResult(new EmployeeDTO(1002, "Priya Sharma", LocalDate.of(1992, 8, 22), 60000.0));

		String requestBody = """
				{
				  "empid": 1234,
				  "nameString": "Priya Sharma",
				  "dobDate": "22-08-1992",
				  "salary": 60000
				}
				""";

		mockMvc.perform(put("/api/employees/1002")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.empid").value(1002))
				.andExpect(jsonPath("$.nameString").value("Priya Sharma"));
	}

	@Test
	void testDeleteEmployee() throws Exception {
		fakeService.setDeleteResult("Employee with ID 1001 deleted successfully");

		mockMvc.perform(delete("/api/employees/1001"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Employee with ID 1001 deleted successfully"));
	}

	@TestConfiguration
	static class ControllerTestConfig {
		@Bean
		FakeEmployeeService fakeEmployeeService() {
			return new FakeEmployeeService();
		}

		@Bean
		IEmployeeService employeeService(FakeEmployeeService fakeEmployeeService) {
			return fakeEmployeeService;
		}
	}

	static class FakeEmployeeService implements IEmployeeService {
		private List<EmployeeDTO> allEmployees = List.of();
		private EmployeeDTO getEmployeeResult;
		private EmployeeDTO updateResult;
		private String deleteResult = "Employee with ID 0 deleted successfully";

		void reset() {
			allEmployees = List.of();
			getEmployeeResult = null;
			updateResult = null;
			deleteResult = "Employee with ID 0 deleted successfully";
		}

		void setAllEmployees(List<EmployeeDTO> allEmployees) {
			this.allEmployees = allEmployees;
		}

		void setGetEmployeeResult(EmployeeDTO getEmployeeResult) {
			this.getEmployeeResult = getEmployeeResult;
		}

		void setUpdateResult(EmployeeDTO updateResult) {
			this.updateResult = updateResult;
		}

		void setDeleteResult(String deleteResult) {
			this.deleteResult = deleteResult;
		}

		@Override
		public EmployeeDTO createEmployee(EmployeeDTO emp) {
			return emp;
		}

		@Override
		public EmployeeDTO getEmployee(int empid) {
			return getEmployeeResult;
		}

		@Override
		public List<EmployeeDTO> getAllEmployees() {
			return allEmployees;
		}

		@Override
		public String deleteEmployee(int empid) {
			return deleteResult;
		}

		@Override
		public EmployeeDTO updateEmployee(EmployeeDTO emp) {
			return updateResult;
		}
	}
}
