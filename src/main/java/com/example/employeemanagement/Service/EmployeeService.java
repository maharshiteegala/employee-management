package com.example.employeemanagement.Service;

import org.springframework.stereotype.Service;

import com.example.employeemanagement.Exceptions.EmployeeAlreadyExists;
import com.example.employeemanagement.dto.EmployeeRequestDTO;
import com.example.employeemanagement.dto.EmployeeResponseDTO;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	public EmployeeResponseDTO serviceCreateEmployee(EmployeeRequestDTO dto){
		
		if(repository.existsByEmail(dto.getEmail())) {
			throw new EmployeeAlreadyExists("Employee already exits with email :"+ dto.getEmail());
		}

//		EmployeeRequestDTO ->Entity
		Employee emp = new Employee();
		emp.setFirstName(dto.getFirstName());
		emp.setLastName(dto.getLastName());
		emp.setEmail(dto.getEmail());
		emp.setPhoneNumber(dto.getPhoneNumber());
		emp.setDepartment(dto.getDepartment());
		emp.setSalary(dto.getSalary());
		emp.setJoiningDate(dto.getJoiningDate());

//		saved Entity to DB
		Employee savedEmp = repository.save(emp);
		if (dto.getEmail().equals(savedEmp.getEmail())) {
          throw new EmployeeAlreadyExists("Employee already Exists.");
		}

//		Entity -> EmployeeResponseDTO 

		EmployeeResponseDTO response = new EmployeeResponseDTO();
		response.setId(savedEmp.getId());
		response.setFirstName(savedEmp.getFirstName());
		response.setLastName(savedEmp.getLastName());
		response.setEmail(savedEmp.getEmail());
		response.setPhoneNumber(savedEmp.getPhoneNumber());
		response.setDepartment(savedEmp.getDepartment());
		response.setSalary(savedEmp.getSalary());
		response.setJoiningDate(savedEmp.getJoiningDate());

		return response;
	}

}
