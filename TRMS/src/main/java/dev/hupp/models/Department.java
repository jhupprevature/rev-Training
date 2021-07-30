/**
 * 
 */
package dev.hupp.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * @author Jordan Hupp
 *
 */
@Entity
@Table(name="departments")
public class Department extends DBTable{
//	@Id
//	@Column(name="id", updatable=false)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;

	@Expose
	@Column(name="dept_name", nullable=false)
	private String deptName;
	
//	@Expose
	@OneToOne(targetEntity = Employee.class)
	@JoinColumn(name = "dept_head", referencedColumnName = "id")
	private Employee deptHead;
	
	@Expose
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy = "department", cascade = {CascadeType.MERGE} )
	private List<Employee> employees;
	
	public Department() {
		super();
	}
	
	public Department(String deptName, Employee deptHead, String descripton) {
		super();
		this.deptName = deptName;
		this.deptHead = deptHead;
		this.description = descripton;
	}
	
	public Department(int id, String deptName, Employee deptHead, String descripton) {
		super();
		this.id = id;
		this.deptName = deptName;
		this.deptHead = deptHead;
		this.description = descripton;
	}
	
//	@Override
//	public int getID() {
//		return id;
//	}
//
//	@Override
//	public void setID(int id) {
//		this.id = id;
//	}

	public String getDeptName() {
		return deptName;
	}
	

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	

	public Employee getDeptHead() {
		return deptHead;
	}
	

	public void setDeptHead(Employee deptHead) {
		this.deptHead = deptHead;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", deptName=" + deptName 
				+ ", deptHead=" + (deptHead != null ? deptHead.getName() : null) 
				+ ", description=" + description + "]";
	}
}
