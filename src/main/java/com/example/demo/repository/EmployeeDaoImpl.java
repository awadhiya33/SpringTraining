package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	
	EntityManager entityManager;
	
	@Autowired
	public EmployeeDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public List<Employee> findAll() {
		TypedQuery<Employee> query=entityManager.createQuery("From Employee",Employee.class);
		List<Employee> list=query.getResultList();
		return list;
	}


	@Override
	public Employee findById(int id) {
		Employee employee=entityManager.find(Employee.class, id);
		return employee;
	}


	@Override
	public Employee save(Employee employee) {
		Employee emp=entityManager.merge(employee);
		return emp;
	}


	@Override
	public void deleteById(int id) {
		Employee employee=entityManager.find(Employee.class, id);
		entityManager.remove(employee);
	}
	
}
