package com.schoolmgnt.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmgnt.po.Employee;
import com.schoolmgnt.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/all")
	public ResponseEntity<Object> getAllEmp() {
		logger.info("/all end point called");
		List<Employee> empList = employeeService.getAllEmployee();
		return ResponseEntity.status(HttpStatus.OK).body(empList);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> empLogin(@RequestParam(required = true) Map<String, String> reqParam) {
		logger.info("/login end point called");
		String userName = reqParam.get("username");
		String password = reqParam.get("password");
		List<Employee> empDetails = employeeService.getEmployeeByUserNameAndPassword(userName, password);
		return ResponseEntity.status(HttpStatus.OK).body(empDetails);
	}

	@PostMapping("/register")
	public Employee postMethodName(@RequestBody Employee employee) {
		logger.info("/register end point called");
		return employeeService.saveEmployee(employee);
	}

	@GetMapping("/ageRange")
	public ResponseEntity<Object> getAgeRange(@RequestParam(required = true) Map<String, String> reqParam) {
		logger.info("/ageRange end point called");
		String start = reqParam.get("start");
		String end = reqParam.get("end");
		List<Employee> list = employeeService.getAgeRange(start, end);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

}
