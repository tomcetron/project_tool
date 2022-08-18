package com.proma.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proma.pma.dao.EmployeeRepository;
import com.proma.pma.dao.ProjectRepository;
import com.proma.pma.dto.ChartData;
import com.proma.pma.dto.EmployeeProject;
import com.proma.pma.entities.Project;
import com.proma.pma.services.ProjectService;

@Controller
public class HomeController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	ProjectService proService;
	
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		Map<String, Object> map = new HashMap<>();
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);
		
		// get project status
		List<ChartData> projectData = proRepo.getProjectStatus();
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		
		model.addAttribute("projectStatusCnt", jsonString);
		
		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);
		
		return "main/home";
	}



}
