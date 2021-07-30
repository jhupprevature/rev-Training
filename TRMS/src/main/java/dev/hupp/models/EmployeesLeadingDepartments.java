package dev.hupp.models;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.google.gson.annotations.Expose;

public class EmployeesLeadingDepartments extends DBTable {
	@Expose
	@OneToOne(targetEntity = Employee.class)
	@JoinColumn(name = "dept_head", referencedColumnName = "id")
	private Employee deptHead;
	
	@Expose
	@ManyToOne(optional = false, targetEntity = Department.class)
	@JoinColumn(name = "department", referencedColumnName = "id")
	private Department department;

}
