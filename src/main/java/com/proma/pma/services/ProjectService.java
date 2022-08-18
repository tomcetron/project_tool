package com.proma.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proma.pma.dao.ProjectRepository;
import com.proma.pma.dto.ChartData;
import com.proma.pma.dto.TimeChartData;
import com.proma.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	ProjectService proService;
	
	public Project save(Project project) {
		return proRepo.save(project);
	}
	
	public List<Project> getAll(){
		return proRepo.findAll();
	}
	
	public List<ChartData> getProjectStatuses(){
		return proRepo.getProjectStatus();
	}
	
	public List<TimeChartData> getTimeData(){
		return proRepo.getTimeData();
	}

}
