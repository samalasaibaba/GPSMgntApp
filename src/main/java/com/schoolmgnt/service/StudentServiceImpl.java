package com.schoolmgnt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmgnt.po.Student;
import com.schoolmgnt.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Long id) throws Exception {
		 if(studentRepository.existsById(id)) {
			 studentRepository.deleteById(id);
		 }else {
			 throw new Exception("StudentId is not found..!");
		 }
	}

	@Override
	public Optional<Student> getStudentByUserNameAndPassword(String userName, String password) {
		
		return studentRepository.getStudentByUserNameAndPassword(userName, password);
	}

}
