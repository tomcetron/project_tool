package com.proma.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proma.pma.dao.EmployeeRepository;
import com.proma.pma.entities.Employee;
import com.proma.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		Employee anEmployee = new Employee();
		model.addAttribute("employee", anEmployee);
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee( Model model, @Validated Employee employee, Errors errors) {
	
	if(errors.hasErrors()) return "employees/new-employee";
		
		
	// save to the database using an employee CRUD repository
	empRepo.save(employee);
	return "redirect:/employees/";
	
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
		Employee theEmp = empService.findByEmployeeId(theId);
		model.addAttribute("employee", theEmp);
		return "employees/new-employee";
	}
	
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long theId, Model model) {
		
		Employee theEmp = empService.findByEmployeeId(theId);
		empService.delete(theEmp); 
		return "redirect:/employees";
	}
	
	
	
}