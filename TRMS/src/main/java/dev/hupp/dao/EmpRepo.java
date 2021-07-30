package dev.hupp.dao;

import java.util.List;

import dev.hupp.models.Employee;

public interface EmpRepo extends GenericRepo<Employee> {
	
	public List<Employee> getEmpByName(String name); //Configure for first, last, and full name.
	public Employee getEmpByUsername(String username);
//	public Employee getEmpCollections(Employee user);
	
}
