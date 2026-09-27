package com.example.employeemanagement.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.employeemanagement.Exceptions.EmployeeAlreadyExists;
import com.example.employeemanagement.Exceptions.EmployeeNotFoundException;
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

	public EmployeeResponseDTO serviceCreateEmployee(EmployeeRequestDTO dto) {

		if (repository.existsByEmail(dto.getEmail())) {
			throw new EmployeeAlreadyExists("Employee already exits with email :" + dto.getEmail());
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

	public List<EmployeeResponseDTO> serviceGetEmployees() {
		// Get all employees from the database
		List<Employee> employeeEntities = repository.findAll();
//		Entity -> EmployeeResponseDTO 
		// Create a list to store EmployeeResponseDTO objects
		List<EmployeeResponseDTO> employeeResponseList = new ArrayList<EmployeeResponseDTO>();
		for (Employee employeeEntity : employeeEntities) {

			EmployeeResponseDTO employeeResponse = new EmployeeResponseDTO();

			employeeResponse.setId(employeeEntity.getId());
			employeeResponse.setFirstName(employeeEntity.getFirstName());
			employeeResponse.setLastName(employeeEntity.getLastName());
			employeeResponse.setEmail(employeeEntity.getEmail());
			employeeResponse.setPhoneNumber(employeeEntity.getPhoneNumber());
			employeeResponse.setDepartment(employeeEntity.getDepartment());
			employeeResponse.setSalary(employeeEntity.getSalary());
			employeeResponse.setJoiningDate(employeeEntity.getJoiningDate());
			// Add the converted DTO to the response list
			employeeResponseList.add(employeeResponse);

		}
		return employeeResponseList;
	}

	public EmployeeResponseDTO serviceGetEmployee(long id) {

		Employee employeeEntity = repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with :" + id));

		EmployeeResponseDTO response = new EmployeeResponseDTO();
		response.setId(employeeEntity.getId());
		response.setFirstName(employeeEntity.getFirstName());
		response.setLastName(employeeEntity.getLastName());
		response.setEmail(employeeEntity.getEmail());
		response.setPhoneNumber(employeeEntity.getPhoneNumber());
		response.setDepartment(employeeEntity.getDepartment());
		response.setSalary(employeeEntity.getSalary());
		response.setJoiningDate(employeeEntity.getJoiningDate());
		return response;

	}

	public EmployeeResponseDTO serviceUpdateEmployee(long id, EmployeeRequestDTO dto) {

		Employee employeeEntity = repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with :" + id));

//		EmployeeRequestDTO ->Entity
		// Employee emp = new Employee();
		employeeEntity.setFirstName(dto.getFirstName());
		employeeEntity.setLastName(dto.getLastName());
		employeeEntity.setEmail(dto.getEmail());
		employeeEntity.setPhoneNumber(dto.getPhoneNumber());
		employeeEntity.setDepartment(dto.getDepartment());
		employeeEntity.setSalary(dto.getSalary());
		employeeEntity.setJoiningDate(dto.getJoiningDate());

//		saved Entity to DB
		Employee updatEmployeeEntity = repository.save(employeeEntity);

		EmployeeResponseDTO response = new EmployeeResponseDTO();
		response.setId(updatEmployeeEntity.getId());
		response.setFirstName(updatEmployeeEntity.getFirstName());
		response.setLastName(updatEmployeeEntity.getLastName());
		response.setEmail(updatEmployeeEntity.getEmail());
		response.setPhoneNumber(updatEmployeeEntity.getPhoneNumber());
		response.setDepartment(updatEmployeeEntity.getDepartment());
		response.setSalary(updatEmployeeEntity.getSalary());
		response.setJoiningDate(updatEmployeeEntity.getJoiningDate());
		return response;

	}

	public void serviceDeleteEmployee(long id) {
		Employee employeeEntity = repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

		repository.delete(employeeEntity);

	}
}
