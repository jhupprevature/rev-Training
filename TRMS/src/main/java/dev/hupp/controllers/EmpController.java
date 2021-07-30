package dev.hupp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.hupp.models.Employee;
import dev.hupp.services.DeptService;
import dev.hupp.services.EmpService;
import io.javalin.http.Handler;

public class EmpController {
	private Logger log = Logger.getLogger("EmpCtrl Logger");


	private EmpService empSvc;
	private DeptService deptSvc;

	private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	public EmpController(EmpService es, DeptService ds) {
		this.empSvc = es;
		this.deptSvc = ds;
	}

	public Handler login = (context) -> {
		log.debug(context.body());
		Employee user;
		Properties submission = gson.fromJson(context.body(), Properties.class);
		String username = submission.getProperty("username");
		String password = submission.getProperty("password");
//		log.info(username=="");

		if (username != "" || password != "") {
			user = empSvc.getEmpByUsername(username);

			if (user != null) {
				boolean loginSuccessful = empSvc.login(user, password);

				if (loginSuccessful) {

					log.info("Login Success: user=" + username);
					context.status(200);
					context.result(gson.toJson(user));
				} else {
					log.info("Login Failure: password does not match");
					context.status(400);
					context.result("{}");
				}
			} else {
				log.error("Login Failure: No user found");
				context.status(400);
				context.result("{}");
			}
		} else { //Investigate why this won't trigger.
			log.error("Login Failure: Null entries found");
			context.status(400);
			context.result("{}");
		}

	};

	public Handler getAllEmployees = (context) -> {
		List<Employee> employees = new ArrayList<Employee>();
		
//		if (user == null) {
			employees = (List<Employee>) empSvc.getAll();
			
			log.info("Employees retrieved.");
			
//		} else {
//			user = empSvc.getEmpCollections(user);
//			employees.add(user);
//			
//			if (user.getSupervisees() != null) {
//				employees.addAll(user.getSupervisees());
//			}
//			if (user.getBeneficiaries() != null) {
//				employees.addAll(user.getBeneficiaries());
//			}
//			if (user.getDeptBeingLed() != null) {
//				Department dept = user.getDeptBeingLed();
//				
//				employees.addAll(dept.getEmployees());
//			}
//			
//			log.info("Employees for user retrieved.");
//
//		}
		context.result(gson.toJson(employees));
		context.status(200);
	};

	public Handler getByID = (context) -> {
		int empID = context.pathParam("id", Integer.class).get();
		
		Employee emp = empSvc.getByID(empID);
		if (emp != null) {
			log.info("Emplpyee retrieved.");
			context.status(200);
			context.result(gson.toJson(emp));
		} else {
			log.info("404: Employee not found.");
			context.status(404);
			context.result("{}");
		}
	};

	public Handler updatePassword = (context) -> {
		int empID = context.pathParam("id", Integer.class).get();
		Employee emp = empSvc.getByID(empID);
		Properties submission = gson.fromJson(context.body(), Properties.class);
		String newPassword = submission.getProperty("password");
		
		emp.setPassword(newPassword);
		emp = empSvc.update(emp);
		
		if(emp != null) {
			log.info("Employee updated");
			context.status(200);
			context.result(gson.toJson(emp));
		} else {
			log.error("Employee failed to update");
			context.status(400);
			context.result("{}");
		}
	};

	public Handler getSupervisees = (context) -> {
		int empID = context.pathParam("id", Integer.class).get();
		Employee emp = empSvc.getByID(empID);
		List<Employee> employees = (List<Employee>) empSvc.getAllSubordinates(empID, "supervisor");
		
		if (employees.isEmpty()) {
			context.result("{}");
			context.status(404);
			log.error("No supervisees found for employee "+empID);
		} else {
			context.result(gson.toJson(employees));
			context.status(200);
		}
	};

	public Handler getDeptEmployees = (context) -> {
		int empID = context.pathParam("id", Integer.class).get();
		Employee emp = empSvc.getByID(empID);
		List<Employee> employees = (List<Employee>) empSvc.getAllSubordinates(empID, "deptHead");
		
		if (employees.isEmpty()) {
			context.result("{}");
			context.status(404);
			log.error("No department subordinates found for employee "+empID);
		} else {
			context.result(gson.toJson(employees));
			context.status(200);
		}
	};

	public Handler getBeneficiaries = (context) -> {
		int empID = context.pathParam("id", Integer.class).get();
		Employee emp = empSvc.getByID(empID);
		List<Employee> employees = (List<Employee>) empSvc.getAllSubordinates(empID, "benefitCoord");
		
		if (employees.isEmpty()) {
			context.result("{}");
			context.status(404);
			log.error("No beneficiaries found for employee "+empID);
		} else {
			context.result(gson.toJson(employees));
			context.status(200);
		}
	};
	
	
}
