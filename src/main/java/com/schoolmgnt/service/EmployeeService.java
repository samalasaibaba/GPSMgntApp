package com.schoolmgnt.service;

import java.util.List;

import com.schoolmgnt.po.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployee();

	List<Employee> getEmployeeByUserNameAndPassword(String userName, String password);

	Employee saveEmployee(Employee employee);

	List<Employee> getAgeRange(String start, String end);

}
