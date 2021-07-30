package dev.hupp.services.impl;

import java.util.List;

import dev.hupp.dao.EmpRepo;
import dev.hupp.dao.EventRepo;
import dev.hupp.dao.EventTypeRepo;
import dev.hupp.dao.GradingFormatRepo;
import dev.hupp.models.DBTable;
import dev.hupp.models.Event;
import dev.hupp.models.EventType;
import dev.hupp.models.GradingFormat;
import dev.hupp.services.EventService;
import dev.hupp.services.EventTypeService;
import dev.hupp.services.GenericService;
import dev.hupp.services.GradingFormatService;

public class EventSvcImpl extends GenericSvcImpl<Event> implements EventService {
	private EventRepo evntRepo;
	private EventTypeRepo eTypeRepo;
	private GradingFormatRepo grdRepo;
	
	private EventTypeService eTypeSvc;
	private GradingFormatService grdSvc;

	//This may have been a very bad idea...
	public EventSvcImpl(EventRepo er, EventTypeRepo tr, GradingFormatRepo gr) {
		super(er);
		evntRepo = (EventRepo)genRepo;
		
		setETypeSvc(new EventTypeSvcImpl(tr));
		eTypeRepo = (EventTypeRepo) eTypeSvc.getRepo();
		
		setGrdSvc(new GradingFormatSvcImpl(gr));
		grdRepo = (GradingFormatRepo) grdSvc.getRepo();
		
		
	}

	public GenericService<EventType> geteTypeSvc() {
		return eTypeSvc;
	}

	public void setETypeSvc(EventTypeService eTypeSvc) {
		this.eTypeSvc = eTypeSvc;
	}

	public GenericService<GradingFormat> getGrdSvc() {
		return grdSvc;
	}

	public void setGrdSvc(GradingFormatService grdSvc) {
		this.grdSvc = grdSvc;
	}

	

}
