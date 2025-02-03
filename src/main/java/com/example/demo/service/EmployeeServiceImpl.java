package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeDao;
import com.example.demo.repository.EmployeeJpaRepo;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	EmployeeDao employeedao;
	EmployeeJpaRepo employeeJpaRepo;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDao employeedao, EmployeeJpaRepo employeeJpaRepo) {
		this.employeedao = employeedao;
		this.employeeJpaRepo = employeeJpaRepo;
	}

	@Override
	public List<Employee> findAll() {
		
		return employeeJpaRepo.findAll();
	}

	@Override
	public Employee findById(int id) {
		Optional<Employee>emp= employeeJpaRepo.findById(id);
		if(emp.isPresent()) {
			return emp.get();
		}else {
			throw new RuntimeException("Did not find the Employee - "+id);
		}
	}

	@Transactional
	@Override
	public Employee save(Employee employee) {
		return employeedao.save(employee);
	}

	@Transactional
	@Override
	public void deleteById(int id) {
		employeedao.deleteById(id);
	}

}
