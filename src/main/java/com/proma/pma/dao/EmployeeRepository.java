package com.proma.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proma.pma.dto.EmployeeProject;
import com.proma.pma.entities.Employee;

public interface EmployeeRepository extends CrudRepository <Employee, Long>{
	
	
	@Override
	@Query(nativeQuery=true, value="SELECT e.employee_id, e.first_name, e.last_name, e.email FROM employee e "
			+ "ORDER BY e.first_name")
	List<Employee> findAll();
	
	
	@Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount "
			+ "FROM employee e LEFT JOIN project_employee pe ON pe.employee_id = e.employee_id "
			+ "GROUP BY e.first_name, e.last_name ORDER BY 1") 
	public List<EmployeeProject> employeeProjects();
	
	
	public Employee findByEmail(String value);
	
	public Employee findByEmployeeId(long theId);
	
	
	

}
