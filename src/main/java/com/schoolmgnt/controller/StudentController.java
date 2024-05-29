package com.schoolmgnt.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmgnt.po.Student;
import com.schoolmgnt.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	private static final Logger logger = LogManager.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	// @Autowired
	// private GlobalProperties globalProperties;

	@Autowired
	private Environment environment;

	@GetMapping("/find/{studentId}")
	public ResponseEntity<Student> studentFind(@PathVariable Long studentId) {
		logger.info("/find/{studentId} end point called");
		Student result = null;
		Optional<Student> studentList = studentService.getStudentById(studentId);
		if (studentList.isPresent()) {
			result = studentList.get();
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.ok(result);
		}
	}

	@GetMapping("/login")
	public ResponseEntity<Object> studentLogin(@RequestParam(required = true) Map<String, String> reqParam) {
		logger.info("/login end point called");
		String userName = reqParam.get("username");
		String password = reqParam.get("password");
		Student result = null;
		Optional<Student> studentList = studentService.getStudentByUserNameAndPassword(userName, password);
		if (studentList.isPresent()) {
			result = studentList.get();
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<Object> studentRegister(@RequestBody Student student) throws Exception {
		logger.info("/register end point called");
		Student studentResponse = studentService.createStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body(studentResponse);
	}

	@GetMapping("/all")
	public ResponseEntity<Object> findAllStudents() {
		logger.info("/all end point called");
		List<Student> studentList = studentService.getAllStudents();

		// String uri = "http://localhost:8001/abcschooldev/student/find/6";
		// RestTemplate restTemplate = new RestTemplate();
		// String result = restTemplate.getForObject(uri, String.class);
		// System.out.println(result);
		// System.out.println(globalProperties.getName());

		return ResponseEntity.status(HttpStatus.OK).body(studentList);
	}

	@PostMapping("/delete/{studentID}")
	public ResponseEntity<Object> deleteStudent(@PathVariable Long studentID) {
		logger.info("/delete/{studentID} end point called");
		String response = null;
		try {
			studentService.deleteStudent(studentID);
			response = "Student is deleted";
		} catch (Exception e) {
			response = e.getMessage();
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}
}
