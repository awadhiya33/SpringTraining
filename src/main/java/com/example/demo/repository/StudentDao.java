package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Student;

public interface StudentDao {
	
	public void save(Student s);
	
	public Student findById(int id);
	
	public List<Student> findAll();

	List<Student> findByLastName(String theLastName);
	
	public void update(Student s);
	
	public void delete(Integer id);
	
	public int deleteAll();
}
