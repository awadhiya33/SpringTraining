package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestGlobalExceptionHandler {
	
	// Add an exception Handlre
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException e){
			StudentErrorResponse error=new StudentErrorResponse();
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(e.getMessage());
			error.setTimestamp(System.currentTimeMillis());
			return new ResponseEntity(error,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(Exception e){
			StudentErrorResponse error=new StudentErrorResponse();
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage(e.getMessage());
			error.setTimestamp(System.currentTimeMillis());
			return new ResponseEntity(error,HttpStatus.BAD_REQUEST);
		}
}
