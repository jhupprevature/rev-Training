package dev.hupp.services.impl;

import java.util.ArrayList;
import java.util.List;

import dev.hupp.dao.EmpRepo;
import dev.hupp.models.Employee;
import dev.hupp.services.EmpService;

public class EmpServiceImpl extends GenericSvcImpl<Employee> implements EmpService {
	private EmpRepo empRepo;

	public EmpServiceImpl(EmpRepo er) {
		super(er);
		empRepo = (EmpRepo)genRepo;
		
	}

	public boolean login(Employee user, String password) {
		boolean authenticated = false;
		
		if (user.getPassword().equals(password)) {
			authenticated = true;
		}
		
		return authenticated;
	}

	public Employee getEmpByUsername(String username) {
		return empRepo.getEmpByUsername(username);
	}

	@Override
	public List<Employee> getAllSubordinates(int empID, String supType) {
		List<Employee> subordinates = new ArrayList<Employee>();
		List<Employee> allEmp = (List<Employee>) this.getAll();
		
//		try {
//			String col = Employee.class.getField(supType).getName();
//		} catch (NoSuchFieldException|SecurityException e) {
//			e.printStackTrace();
//		}
		
		for (Employee emp : allEmp) {
			int supEmpID = 0;
			switch (supType) {
			case "supervisor":
				supEmpID = emp.getSupervisor().getID();
				break;
			case "deptHead":
				supEmpID = emp.getDepartment().getDeptHead().getID();
				break;
			case "benefitCoord":
				supEmpID = emp.getBenefitCoord().getID();
				break;
			}
			
			if (empID == supEmpID) {
				subordinates.add(emp);
			}
		}
		log.info(subordinates.size() + " subordinates found.");
		
		//Lazy Init errors...
//		Employee emp = this.getByID(empID);
//		switch (supType) {
//		case "supervisor":
//			subordinates = emp.getSupervisees();
//			log.info(subordinates.size() + " supervisees found.");
//			break;
//		case "deptHead":
//			subordinates = emp.getDepartment().getEmployees();
//			log.info(subordinates.size() + " dept emps found.");
//			break;
//		case "benefitCoord":
//			subordinates = emp.getBeneficiaries();
//			log.info(subordinates.size() + " beneficiaries found.");
//			break;
//		}
		
		return subordinates;
	}

//	@Override
//	public Employee getEmpCollections(Employee user) {
//		return empRepo.getEmpCollections(user);
//	}

}
