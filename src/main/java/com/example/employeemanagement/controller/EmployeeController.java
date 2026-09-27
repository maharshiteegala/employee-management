package com.example.employeemanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.employeemanagement.Service.EmployeeService;
import com.example.employeemanagement.dto.EmployeeRequestDTO;
import com.example.employeemanagement.dto.EmployeeResponseDTO;
import com.example.employeemanagement.entity.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;

	}

	@PostMapping
	public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO dto) {

		EmployeeResponseDTO savedControlledEmp = service.serviceCreateEmployee(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedControlledEmp);
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDTO>> getEmployees() {

		List<EmployeeResponseDTO> getemp = service.serviceGetEmployees();

		return ResponseEntity.status(HttpStatus.OK).body(getemp);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable long  id) {

		EmployeeResponseDTO getemp = service.serviceGetEmployee(id);

		return ResponseEntity.status(HttpStatus.OK).body(getemp);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable long  id,@RequestBody EmployeeRequestDTO dto) {

		EmployeeResponseDTO updatemp = service.serviceUpdateEmployee(id,dto);

		return ResponseEntity.status(HttpStatus.OK).body(updatemp);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable long  id) {

		 service.serviceDeleteEmployee(id);

		return ResponseEntity.noContent().build();
	}

}
