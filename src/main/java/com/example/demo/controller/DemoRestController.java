package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class DemoRestController {
	
	private List<Student> theStudent;
	
	@PostConstruct
	public void loadData() {
		theStudent=new ArrayList<>();
		theStudent.add(new Student("Abhi","singh"));
		theStudent.add(new Student("aniket","kumar"));
		theStudent.add(new Student("amit","vernma"));
	}
	@GetMapping("/students")
	public List<Student> getStudents(){
		return theStudent;
	}
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {
		if(id>=theStudent.size() || id<0) {
			throw new StudentNotFoundException("Student id not found - "+id);
		}
		return theStudent.get(id);
	}
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello";
	}
	
}
