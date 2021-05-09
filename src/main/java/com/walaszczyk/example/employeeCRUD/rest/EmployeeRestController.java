package com.walaszczyk.example.employeeCRUD.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walaszczyk.example.employeeCRUD.entity.Employee;
import com.walaszczyk.example.employeeCRUD.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController (EmployeeService theEmployeeDAO) {
		employeeService = theEmployeeDAO;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable int id) {
		Employee employee = employeeService.findById(id);
		if (employee == null ) {
			throw new RuntimeException("Employee not found - "+id );			
		}
		return employee;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		
		employeeService.save(employee);
		
		return employee;
	}
}
