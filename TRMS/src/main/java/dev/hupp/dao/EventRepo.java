package dev.hupp.dao;

import java.util.List;

import dev.hupp.models.Event;

public interface EventRepo extends GenericRepo<Event> {
	public List<Event> getEventsByLocation(String location);
}
