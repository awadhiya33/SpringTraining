package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Employee;

public interface EmployeeDao {
	List<Employee> findAll();
	Employee findById(int id);
	Employee save(Employee employee);
	void deleteById(int id);
}
