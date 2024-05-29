package com.schoolmgnt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.schoolmgnt.po.Student;

@Service
public interface StudentService {

	public List<Student> getAllStudents();

	public Optional<Student> getStudentById(Long id);

	public Student createStudent(Student student);

	public void deleteStudent(Long id) throws Exception;

	public Optional<Student> getStudentByUserNameAndPassword(String userName, String password);
}