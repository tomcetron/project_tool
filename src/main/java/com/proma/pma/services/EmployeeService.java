package com.proma.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.proma.pma.dao.EmployeeRepository;
import com.proma.pma.dto.EmployeeProject;
import com.proma.pma.entities.Employee;

@Controller
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;
	
	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}
	
	public Iterable<Employee> getAll(){
		return empRepo.findAll();
	}
	
	public List<EmployeeProject> EmployeeProjects(){
		return empRepo.employeeProjects();
	}
	
	public Employee findByEmployeeId(long theId) {
		return empRepo.findByEmployeeId(theId);
	}

	public void delete(Employee theEmp) {
		empRepo.delete(theEmp);
	}
	
	
	

}
