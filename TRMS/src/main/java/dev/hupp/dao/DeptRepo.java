package dev.hupp.dao;

import java.util.List;

import dev.hupp.models.Department;
import dev.hupp.models.Employee;

public interface DeptRepo extends GenericRepo<Department>{
	
	public Department getDeptByName(String name);
	public Department getDeptByHead(Employee deptHead);
	
//	public List<Employee> getDeptEmployees(String name);

}
