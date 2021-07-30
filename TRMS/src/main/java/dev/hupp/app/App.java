package dev.hupp.app;

import dev.hupp.controllers.EmpController;
import dev.hupp.controllers.RequestController;
import dev.hupp.dao.AttachmentRepo;
import dev.hupp.dao.CommunicationRepo;
import dev.hupp.dao.DeptRepo;
import dev.hupp.dao.EmpRepo;
import dev.hupp.dao.EventRepo;
import dev.hupp.dao.EventTypeRepo;
import dev.hupp.dao.GenericRepo;
import dev.hupp.dao.GradingFormatRepo;
import dev.hupp.dao.RequestRepo;
import dev.hupp.dao.impl.AttchRepoHBImpl;
import dev.hupp.dao.impl.CommRepoHBImpl;
import dev.hupp.dao.impl.DeptRepoHBImpl;
import dev.hupp.dao.impl.EmpRepoHBImpl;
import dev.hupp.dao.impl.EventRepoHBImpl;
import dev.hupp.dao.impl.EventTypeRepoHBImpl;
import dev.hupp.dao.impl.GFRepoHBImpl;
import dev.hupp.dao.impl.GenericRepoHBImpl;
import dev.hupp.dao.impl.RequestRepoHBImpl;
import dev.hupp.models.Communication;
import dev.hupp.models.ReimbursementRequest;
import dev.hupp.models.RequestAttachment;
import dev.hupp.services.DeptService;
import dev.hupp.services.EmpService;
import dev.hupp.services.EventService;
import dev.hupp.services.RequestService;
import dev.hupp.services.impl.DeptServiceImpl;
import dev.hupp.services.impl.EmpServiceImpl;
import dev.hupp.services.impl.EventSvcImpl;
import dev.hupp.services.impl.RequestServiceImpl;
import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {

		Javalin app = Javalin.create(config -> config.enableCorsForAllOrigins());

		establishRoutes(app);

		app.start(7000);

	}

	private static void establishRoutes(Javalin app) {
		
		addEmployeeRoutes(app);
		addRequestRoutes(app);

	}

	private static void addRequestRoutes(Javalin app) {
		EventRepo evntRepo = new EventRepoHBImpl();
		EventTypeRepo eTypeRepo = new EventTypeRepoHBImpl();
		GradingFormatRepo grdRepo = new GFRepoHBImpl();
		EventService evntSvc = new EventSvcImpl(evntRepo, eTypeRepo, grdRepo);

		CommunicationRepo commRepo = new CommRepoHBImpl();
		AttachmentRepo attchRepo = new AttchRepoHBImpl();
		RequestRepo reqRepo = new RequestRepoHBImpl();
		RequestService reqSvc = new RequestServiceImpl(reqRepo, commRepo, attchRepo);

		RequestController reqCtrl = new RequestController(reqSvc, evntSvc);

		app.get("/employees/:e_id/requests/", reqCtrl.getEmpRequests);
		app.get("/employees/:e_id/requests/:id", reqCtrl.getByID);
		app.post("/employees/:e_id/requests/", reqCtrl.addRequest);
		app.put("/employees/:e_id/requests/:id", reqCtrl.updateRequest);
		
		app.get("events/:id", reqCtrl.getEventByID);
//		
//		//The Attachment and Communication controllers merged with Request.
//		app.get("/employees/:e-id/requests/:r-id/attachments", requestCtrl.getAllAttach);
//		app.post("/employees/:e-id/requests/:r-id/attachments", requestCtrl.addAttach);
//		
//		app.get("/employees/:e-id/requests/:r-id/communication", requestCtrl.getAllComm);
//		app.post("/employees/:e-id/requests/:r-id/communication", requestCtrl.addComm);
	}

	private static void addEmployeeRoutes(Javalin app) {
		EmpRepo empRepo = new EmpRepoHBImpl();
		EmpService empSvc = new EmpServiceImpl(empRepo);

		DeptRepo dptRepo = new DeptRepoHBImpl();
		DeptService dptSvc = new DeptServiceImpl(dptRepo);
		EmpController empCtrl = new EmpController(empSvc, dptSvc);

		// Let's hack Hibernate and force it to load upon startup
		// Just going to make a call to force Hibernate to start
		empRepo.getByID(0);

		app.post("/", empCtrl.login);
		app.post("/index.html", empCtrl.login);

//		app.get("/", empCtrl.logout);
//		app.get("/index.html", empCtrl.logout);
//		app.get("/verify", empCtrl.verify);

		app.get("/employees", empCtrl.getAllEmployees);
		app.get("/employees/:id", empCtrl.getByID);
		app.put("/employees/:id", empCtrl.updatePassword);

		app.get("/employees/:id/supervisor", empCtrl.getSupervisees);
		app.get("/employees/:id/department", empCtrl.getDeptEmployees);
		app.get("/employees/:id/beneficiaries", empCtrl.getBeneficiaries);
	}

}
