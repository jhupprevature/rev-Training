package dev.hupp.services;

import java.util.List;

import dev.hupp.models.Employee;

public interface EmpService extends GenericService<Employee> {
	
	public boolean login(Employee user, String password);
	
	public Employee getEmpByUsername(String username);

	public List<Employee> getAllSubordinates(int empID, String supType);

//	public Employee getEmpCollections(Employee user);

}
