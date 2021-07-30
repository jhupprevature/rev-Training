package dev.hupp.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "employees")
public class Employee extends DBTable {
//	@Id
//	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	
	@Expose
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Expose
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Expose
	@Column(name = "username", nullable = false)
	private String username;

	@Expose
	@Column(name = "password", nullable = false)
	private String password;

	@Expose
	@Column(name = "dob", nullable = false)
	private Date dob;

	@Expose
	@Column(name = "hire_date", nullable = false)
	private Date hireDate;

	@Expose
	@ManyToOne(optional = false, targetEntity = Department.class)
	@JoinColumn(name = "department", referencedColumnName = "id")
	private Department department;

	@Expose
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "supervisor", referencedColumnName = "id")
	private Employee supervisor;

	@Expose
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "ben_co", referencedColumnName = "id")
	private Employee benefitCoord;

	@OneToOne(mappedBy = "deptHead", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	private Department deptBeingLed;

	@OneToMany(mappedBy = "supervisor", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	private List<Employee> supervisees;

	@OneToMany(mappedBy = "benefitCoord", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	private List<Employee> beneficiaries;
	
	@OneToMany(mappedBy = "requestor", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	private List<ReimbursementRequest> requests;
	
	@OneToMany(mappedBy = "decider", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	private List<ReimbursementRequest> requestDecisions;
	
	@OneToMany(mappedBy = "sender", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	private List<Communication> sentMessages;
	
	@OneToMany(mappedBy = "recipient", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	private List<Communication> recievedMessages;

	public Employee() {
		super();
	}

	// TODO Do I want to have a username-less constructor to generate a default
	// username?

	public Employee(String firstName, String lastName, String username, String password, Date dob, Date hireDate,
			Department department, Employee supervisor, Employee benefitCoord) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.dob = dob;
		this.hireDate = hireDate;
		this.department = department;
		this.supervisor = supervisor;
		this.benefitCoord = benefitCoord;
	}

	public Employee(int id, String firstName, String lastName, String username, String password, Date dob,
			Date hireDate, Department department, Employee supervisor, Employee benefitCoord) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.dob = dob;
		this.hireDate = hireDate;
		this.department = department;
		this.supervisor = supervisor;
		this.benefitCoord = benefitCoord;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	public Employee getBenefitCoord() {
		return benefitCoord;
	}

	public void setBenefitCoord(Employee benefitCoord) {
		this.benefitCoord = benefitCoord;
	}

	public Department getDeptBeingLed() {
		return deptBeingLed;
	}

	public void setDeptBeingLed(Department deptBeingLed) {
		this.deptBeingLed = deptBeingLed;
	}

	public List<Employee> getSupervisees() {
		return supervisees;
	}

	public void setSupervisees(List<Employee> supervisees) {
		this.supervisees = supervisees;
	}

	public List<Employee> getBeneficiaries() {
		return beneficiaries;
	}

	public void setBeneficiaries(List<Employee> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
	
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", dob=" + dob + ", hireDate=" + hireDate 
				+ ", department=" + (department != null ? department.getID() : null)
				+ ", supervisor=" + (supervisor != null ? supervisor.getName() : null) 
				+ ", benefitCoord=" + (benefitCoord != null ? benefitCoord.getName() : null) + "]";
	}

}
