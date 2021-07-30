package dev.hupp.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.Time;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import dev.hupp.dao.impl.EventRepoHBImpl;
import dev.hupp.models.Event;
import dev.hupp.models.EventType;
import dev.hupp.models.GradingFormat;

public class EventRepoTest {
	final static Logger repoTestLog = Logger.getLogger("Repo Test Logger");
	
	private EventRepo testRepo;
	private Event testEvent;
//	private Event trueEvent;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		testRepo = new EventRepoHBImpl();
		
		testEvent = new Event(new Date(2020, 8, 6), new Time(15,43,00),
				"Charlotte", null, 445.36, new EventType(), new GradingFormat(), null);
		
//		trueEvent = testRepo.getByID(1);
	}

	@Test
	public void testGetEventsByLocation() {
		
		assertEquals("Should retrieve all events at same location", 3, 
				testRepo.getEventsByLocation(testEvent.getEventLocation()).size());
		
	}

}
