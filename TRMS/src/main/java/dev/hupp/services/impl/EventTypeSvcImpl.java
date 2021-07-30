package dev.hupp.services.impl;

import dev.hupp.dao.EventTypeRepo;
import dev.hupp.models.EventType;
import dev.hupp.services.EventTypeService;

public class EventTypeSvcImpl extends GenericSvcImpl<EventType> implements EventTypeService {
	private EventTypeRepo evntRepo;

	public EventTypeSvcImpl(EventTypeRepo er) {
		super(er);
		evntRepo = (EventTypeRepo) genRepo;
	}
	
}
