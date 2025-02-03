package com.example.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentDao;

@SpringBootApplication
public class SpringTraining {

	public static void main(String[] args) {
		SpringApplication.run(SpringTraining.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner->{
			//createStudent(studentDao);
//			readStudent(studentDao);
			//queryForStudent(studentDao);
			//queryForStudentByLastName(studentDao);
			//updateStudent(studentDao);
			//deleteStudent(studentDao);
			//deleteAllStudent(studentDao);
		};
	}
	private void deleteAllStudent(StudentDao studentDao) {
		System.out.println("No of Student deleted :"+studentDao.deleteAll());
		
	}
	private void deleteStudent(StudentDao studentDao) {
		studentDao.delete(8);
	}
	private void updateStudent(StudentDao studentDao) {
		int id=1;
		Student s=studentDao.findById(id);
		s.setFirstName("ujjwal");
		studentDao.update(s);
		System.out.println(s);
	}
	private void queryForStudentByLastName(StudentDao studentDao) {
		List<Student> list=studentDao.findByLastName("singh");
		for(Student s:list) System.out.println(s);
		
	}
	private void queryForStudent(StudentDao studentDao) {
		
		List<Student> list=studentDao.findAll();
		for(Student s:list) System.out.println(s);
	}
	private void readStudent(StudentDao studentDao) {
		Student s=studentDao.findById(5);
		System.out.println(s);
	}
	private void createStudent(StudentDao studentDao) {
		Student s=new Student("abhishek","singh");
		studentDao.save(s);
		System.out.println("id of student saved: "+s.getId());
		
	}
}
