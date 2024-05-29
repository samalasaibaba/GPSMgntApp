package com.schoolmgnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmgnt.po.Employee;
import com.schoolmgnt.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> getEmployeeByUserNameAndPassword(String userName, String password) {
		return employeeRepository.getEmployeeByUserNameAndPassword(userName, password);

	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);

	}

	@Override
	public List<Employee> getAgeRange(String start, String end) {
		List<Employee> list = employeeRepository.findByEmpAgeBetween(start, end);
		return list;
	}

}
