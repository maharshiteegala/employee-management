package com.example.employeemanagement.Exceptions;

public class EmployeeAlreadyExists extends RuntimeException  {

	public EmployeeAlreadyExists(String message) {
		super(message);
		
	}
}
