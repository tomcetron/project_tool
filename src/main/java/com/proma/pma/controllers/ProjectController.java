package com.proma.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proma.pma.dao.EmployeeRepository;
import com.proma.pma.dao.ProjectRepository;
import com.proma.pma.dto.TimeChartData;
import com.proma.pma.entities.Employee;
import com.proma.pma.entities.Project;
import com.proma.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	  @Autowired ProjectRepository proRepo;
	  
	  @Autowired 
	  ProjectService proService;
	  
	  @Autowired
	  EmployeeRepository empRepo;
	  
	  @GetMapping 
	  public String displayProjects(Model model) {
		  List<Project> projects = proRepo.findAll(); model.addAttribute("projects", projects);
		  return "projects/list-projects";
	  }
	  
	@GetMapping("/new")
		public String displayProjectForm(Model model) {
	
		Project aProject = new Project();
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);			
		return "projects/new-project";
	}
	
	  @PostMapping("/save")
	  public String createProject(Project project, Model model) {
		  proRepo.save(project);
		  return "redirect:/projects";
	  }
	  
	  @GetMapping("timelines/")
	  public String displayProjectTimelines(Model model) throws JsonProcessingException {
		 List<TimeChartData> timelineData = proService.getTimeData();
		 ObjectMapper objectMapper = new ObjectMapper();
		 String jsonTimeLineString = objectMapper.writeValueAsString(timelineData);
		 
		 System.out.println("------------ project timelines ------------");
		 System.out.println(jsonTimeLineString);
		 
		 model.addAttribute("projectTimeList",jsonTimeLineString);
		 
		 return "projects/project-timelines";
	  
	  }
	  
	  
	  
	  
	  
	 
	  
}