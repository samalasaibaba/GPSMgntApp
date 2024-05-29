package com.schoolmgnt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolmgnt.po.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> getStudentByUserNameAndPassword(String userName, String password);

}
