package dev.hupp.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.hupp.dao.EmpRepo;
import dev.hupp.dao.impl.EmpRepoHBImpl;
import dev.hupp.models.Employee;
import dev.hupp.models.Event;
import dev.hupp.models.ReimbursementRequest;
import dev.hupp.services.EmpService;
import dev.hupp.services.EventService;
import dev.hupp.services.RequestService;
import dev.hupp.services.impl.EmpServiceImpl;
import io.javalin.http.Handler;

public class RequestController {
	// This is probably bad practise...
	private EmpRepo empRepo = new EmpRepoHBImpl();
	private EmpService empSvc = new EmpServiceImpl(empRepo);

	private RequestService reqSvc;
	private EventService evntSvc;

	private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	private Logger log = Logger.getLogger("ReqCtrl Logger");

	public RequestController(RequestService rs, EventService es) {
		this.reqSvc = rs;
		this.evntSvc = es;
	}

	public Handler getEmpRequests = (context) -> {
		// route: "/employees/:e_id/requests/"
		int empID = context.pathParam("e_id", Integer.class).get();
		List<ReimbursementRequest> empReqs = new ArrayList<ReimbursementRequest>();
		List<ReimbursementRequest> allReqs = (List<ReimbursementRequest>) reqSvc.getAll();

		for (ReimbursementRequest req : allReqs) {
			if (req.getRequestor().getID() == empID) {
				empReqs.add(req);
			}
		}

		if (!empReqs.isEmpty()) {
			log.info("Requests retrieved for e_id: " + empID);
			context.status(200);
			context.result(gson.toJson(empReqs));
		} else {
			log.error("No requests found for e_id: " + empID);
			context.status(404);
			context.result("{}");
		}
	};

	public Handler getByID = (context) -> {
		// route: "/employees/:e_id/requests/:id"
		int empID = context.pathParam("e_id", Integer.class).get();
		int reqID = context.pathParam("id", Integer.class).get();
		ReimbursementRequest req = reqSvc.getByID(reqID);

		if (req != null) {
			if (req.getRequestor().getID() == empID) {
				log.info("Request retrieved. ID: " + reqID);
				context.status(200);
				context.result(gson.toJson(req));
			} else {
				log.error("Request id: " + reqID + " does not belong to emp " + empID);
				context.status(400);
				context.result("{}");
			}
		} else {
			log.error("Request not found. ID: " + reqID);
			context.status(404);
			context.result("{}");
		}
	};

	public Handler addRequest = (context) -> {
		// route: "/employees/:e_id/requests/"
		int empID = context.pathParam("e_id", Integer.class).get();
//		Properties submission = gson.fromJson(context.body(), Properties.class);
		ReimbursementRequest req = gson.fromJson(context.body(), ReimbursementRequest.class);
		log.debug("Raw req: "+req);

		// Access EmpCtrl in ReqCtrl???
		Employee reqEmp = empSvc.getByID(empID);
		Event reqEvent = evntSvc.getByID(req.getEvent().getID());
		String justification = req.getJustification();
		double timeReq = req.getTimeRequirement();
		req = new ReimbursementRequest(reqEmp, reqEvent, justification, timeReq);

		reqSvc.add(req);
	};

	public Handler updateRequest = (context) -> {
		// route: "/employees/:e_id/requests/:id"
		int empID = context.pathParam("e_id", Integer.class).get();
		ReimbursementRequest req = gson.fromJson(context.body(), ReimbursementRequest.class);

		// Access EmpCtrl in ReqCtrl???
		req.setRequestor(empSvc.getByID(empID));
		req.setEvent(evntSvc.getByID(req.getEvent().getID()));
		if (req.getDecider() != null) {
			req.setDecider(empSvc.getByID(req.getDecider().getID()));
		}

		req = reqSvc.update(req);
		
		if (req != null) {
			log.info("Request updated. ID: "+req.getID());
			context.status(200);
			context.result(gson.toJson(req));
		} else {
			log.error("Error updating request.");
			context.status(400);
			context.result("{}");
		}
	};
	public Handler getEventByID = (context) -> {
		// route: /events/:id
		int evntID = context.pathParam("id", Integer.class).get();
		Event evnt = evntSvc.getByID(evntID);
		
		if (evnt != null) {
			log.info("Event retrieved. ID: "+evntID);
			context.status(200);
			context.result(gson.toJson(evnt));
		} else {
			log.error("Event not found");
			context.status(404);
			context.result("{}");
		}
	};
}
