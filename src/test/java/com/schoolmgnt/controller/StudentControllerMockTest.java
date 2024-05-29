
package com.schoolmgnt.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.schoolmgnt.po.Student;
import com.schoolmgnt.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentControllerMockTest {

	private MockMvc mockMvc;

	@Mock
	private StudentService studentService;

	@InjectMocks
	private StudentController studentController;

	Student student1 = new Student(1L, "FirstName3", "LastName3", "user3@gmail.com", 25, "Address3 St", "Username3",
			"Password3");
	Student student2 = new Student(2L, "FirstName4", "LastName4", "user4@gmail.com", 25, "Address4 St", "Username4",
			"Password4");
	Student student3 = new Student(3L, "FirstName5", "LastName5", "user5@gmail.com", 21, "Address5 St", "Username5",
			"Password5");

	Student student4 = Student.builder().id(4L).firstName("samala").lastName("saibaba").email("samala@email.com")
			.age(25).addr("Hyd").userName("samala").password("12345").build();

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

	}

	@Test
	public void findAllStudentsSuccess() throws Exception {
		List<Student> list = new ArrayList<>();
		list.add(student1);
		list.add(student2);
		list.add(student3);
		list.add(student4);
		Mockito.when(studentService.getAllStudents()).thenReturn(list);

		mockMvc.perform(get("/student/all")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[3].firstName", is("samala")));
		verify(studentService, times(1)).getAllStudents();
	}

	@Test

	@DisplayName("findAllStudents Failure Case")
	public void findAllStudentsFailure() throws Exception {
		List<Student> list = new ArrayList<>();
		Mockito.when(studentService.getAllStudents()).thenReturn(list);

		mockMvc.perform(get("/student/all")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));

	}

	@Test

	@DisplayName("studentFind Success Case")
	public void studentFindSuccess() throws Exception {
		Student student = new Student(3L, "FirstName5", "LastName5", "user5@gmail.com", 21, "Address5 St", "Username5",
				"Password5");
		Mockito.when(studentService.getStudentById(3L)).thenReturn(Optional.of(student));
		mockMvc.perform(MockMvcRequestBuilders.get("/student/find/3")).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("FirstName5"));
	}

	@Test
	@DisplayName("Student Registaraion Success")
	public void studentRegisterSuccess() throws Exception {
		Student student = new Student(3L, "FirstName5", "LastName5", "user5@gmail.com", 21, "Address5 St", "Username5",
				"Password5");
		Mockito.when(studentService.createStudent(student)).thenReturn(student);

		mockMvc.perform(post("/student/register").contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n" + "    \"firstName\": \"FirstName5\",\r\n" + "    \"lastName\": \"LastName5\",\r\n"
						+ "    \"email\": \"user5@gmail.com\",\r\n" + "    \"age\": \"21\",\r\n"
						+ "    \"id\": \"3\",\r\n" + "    \"addr\": \"Address5 St\",\r\n"
						+ "    \"userName\": \"Username5\",\r\n" + "    \"password\": \"Password5\"\r\n" + "}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value("FirstName5"));
	}
}
