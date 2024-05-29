package com.schoolmgnt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolmgnt.po.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> getEmployeeByUserNameAndPassword(String userName, String password);

	List<Employee> findByEmpAgeBetween(String startAge, String endAge);

}
