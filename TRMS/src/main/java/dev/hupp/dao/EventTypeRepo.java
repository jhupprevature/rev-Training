package dev.hupp.dao;

import dev.hupp.models.EventType;

public interface EventTypeRepo extends GenericRepo<EventType> {
	public EventType getByName(String name);
}
