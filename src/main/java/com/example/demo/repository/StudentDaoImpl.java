package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDaoImpl implements StudentDao{
	
	private EntityManager entityManager;

	@Autowired
	public StudentDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Student s) {
		entityManager.persist(s);
		
	}

	@Override
	public Student findById(int id) {
		return entityManager.find(Student.class, id);
	}

	@Override
	public List<Student> findAll() {
		//create query
		//Student is entity name from code not actual database table name
		TypedQuery<Student> theQuery= entityManager.createQuery("FROM Student", Student.class);
		
		return theQuery.getResultList();
		
	}

	@Override
	public List<Student> findByLastName(String theLastName) {
		TypedQuery<Student> theQuery=entityManager.createQuery("FROM Student where lastName=:theData",Student.class);
		theQuery.setParameter("theData", theLastName);
		return theQuery.getResultList();
	}

	@Override
	@Transactional
	public void update(Student s) {
		entityManager.merge(s);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Student s=entityManager.find(Student.class, id);
		if(s!=null) entityManager.remove(s);
	}

	@Override
	@Transactional
	public int deleteAll() {
		int numRowDelted= entityManager.createQuery("Delete FROM Student").executeUpdate();
		return numRowDelted;
	}


}
