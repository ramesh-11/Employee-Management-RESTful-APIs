package com.employee.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;


@Service
//@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeRepository employeeRepository;
	
//	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id){
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent())
//			return employee.get();
//		else throw new ResourceNotFoundException("Employee","Id",id);
		return employeeRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Employee","Id",id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// check employee id existence
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Employee","Id",id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		//save the updated data 
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		// check employee id existence
		employeeRepository.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Employee","Id",id));
		employeeRepository.deleteById(id);
	}
	
}
