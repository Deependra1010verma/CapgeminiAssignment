package com.cg.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.cg.dto.EmployeeDTO;
import com.cg.exception.GlobalExceptionHandler;
import com.cg.service.IEmployeeService;

class EmployeeControllerValidationTest {

	private MockMvc mockMvc;

	private IEmployeeService employeeService;

	@BeforeEach
	void setUp() {
		employeeService = mock(IEmployeeService.class);

		EmployeeController employeeController = new EmployeeController();
		ReflectionTestUtils.setField(employeeController, "employeeService", employeeService);

		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();

		JacksonJsonHttpMessageConverter messageConverter = new JacksonJsonHttpMessageConverter();

		mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
				.setControllerAdvice(new GlobalExceptionHandler())
				.setValidator(validator)
				.setMessageConverters(messageConverter)
				.build();
	}

	@Test
	void shouldReturnValidationErrorsWhenRequiredFieldsAreMissing() throws Exception {
		String requestBody = """
				{
				  "nameString": "",
				  "salary": null
				}
				""";

		mockMvc.perform(post("/api/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Validation failed for the request body"))
				.andExpect(jsonPath("$.fieldErrors.nameString").value("Full name is required"))
				.andExpect(jsonPath("$.fieldErrors.dobDate").value("Date of birth is required"))
				.andExpect(jsonPath("$.fieldErrors.salary").value("Salary is required"));
	}

	@Test
	void shouldReturnReadableMessageWhenDateFormatIsWrong() throws Exception {
		String requestBody = """
				{
				  "nameString": "Rahul Sharma",
				  "dobDate": "1995/12/10",
				  "salary": 42000
				}
				""";

		mockMvc.perform(post("/api/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Invalid date format for dobDate. Expected format is dd-MM-yyyy"));
	}

	@Test
	void shouldReturnNotFoundFromGlobalHandler() throws Exception {
		when(employeeService.getEmployee(9999)).thenReturn(null);

		mockMvc.perform(get("/api/employees/9999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value("Employee with ID 9999 not found"));
	}

	@Test
	void shouldRejectNonNumericEmployeeId() throws Exception {
		mockMvc.perform(get("/api/employees/abc"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Invalid value for empid. Please provide the correct format"));
	}

	@Test
	void shouldCreateEmployeeWhenPayloadIsValid() throws Exception {
		EmployeeDTO createdEmployee = new EmployeeDTO(1003, "Rahul Sharma", LocalDate.of(1995, 12, 10), 42000.0);

		when(employeeService.createEmployee(any(EmployeeDTO.class))).thenReturn(createdEmployee);

		String requestBody = """
				{
				  "nameString": "Rahul Sharma",
				  "dobDate": "10-12-1995",
				  "salary": 42000
				}
				""";

		mockMvc.perform(post("/api/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.nameString").value("Rahul Sharma"))
				.andExpect(jsonPath("$.dobDate").value("10-12-1995"));
	}
}
