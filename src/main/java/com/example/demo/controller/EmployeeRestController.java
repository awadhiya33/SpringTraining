package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("api")
public class EmployeeRestController {
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	@PostConstruct
	private void loadAll() {
		Employee e=new Employee("abhi","singh","abhi.harry33@gmail.com");
		employeeService.save(e);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		return employeeService.findAll();	
	}
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployeeById(@PathVariable int employeeId){
		Employee emp= employeeService.findById(employeeId);	
		if(emp==null) {
			throw new RuntimeException("Employee id not found - "+employeeId);
		}
		return emp;
	}
	@PostMapping("/employees")
	public Employee saveEmployee(@RequestBody Employee employee) {
		employee.setId(0);  // to make sure to save not update
		return employeeService.save(employee);
	}
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.save(employee);
	}
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee emp= employeeService.findById(employeeId);	
		if(emp==null) {
			throw new RuntimeException("Employee id not found - "+employeeId);
		}
		employeeService.deleteById(employeeId);
		return "Deleted Employee id - "+employeeId;
	}
}
