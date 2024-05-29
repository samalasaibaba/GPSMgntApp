package com.schoolmgnt.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.schoolmgnt.po.Employee;
import com.schoolmgnt.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerMockTest {

	private MockMvc mockMvc;

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	@DisplayName("getAllEmployee Success Case")
	public void getAllEmpSuccessTest() throws Exception {
		Employee employee = new Employee(1L, "EmpFirstName1", "EmpLastName1", "25", "EmpAddr1", "EmpEmail1",
				"EmpDesignation1", "EmpUsername1", "EmpPassword1");
		List<Employee> list = new ArrayList<>();
		list.add(employee);
		Mockito.when(employeeService.getAllEmployee()).thenReturn(list);

		mockMvc.perform(get("/emp/all")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].empFirstName", is("EmpFirstName1")));
	}
}
