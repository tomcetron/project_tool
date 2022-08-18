package com.proma.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proma.pma.dto.ChartData;
import com.proma.pma.dto.TimeChartData;
import com.proma.pma.entities.Project;

// je to CRUD repository

public interface ProjectRepository extends CrudRepository <Project, Long>{
	
	@Override
	List<Project> findAll();
		
	@Query(nativeQuery=true, value="SELECT stage as label, COUNT(*) as value FROM project GROUP BY stage")
	public List<ChartData> getProjectStatus();
	
	@Query(nativeQuery=true, value = "SELECT name as projectName, start_date as startDate, end_date as endDate FROM project WHERE start_date is not null")
	public List<TimeChartData> getTimeData();
	
	

}
 