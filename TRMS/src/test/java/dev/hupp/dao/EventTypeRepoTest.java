package dev.hupp.dao;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import dev.hupp.dao.impl.EventTypeRepoHBImpl;
import dev.hupp.models.EventType;

public class EventTypeRepoTest {
	final static Logger repoTestLog = Logger.getLogger("Repo Test Logger");

	private EventTypeRepo testRepo;
	private EventType testType;
//	private EventType trueType;

	@Before
	public void setUp() throws Exception {
		testRepo = new EventTypeRepoHBImpl();

		testType = new EventType("Technical Training", 0.90);

//		trueType = testRepo.getByID(1);
	}

	@Test
	public void testGetByName() {
		
		assertEquals("Should retrieve event type of same name", testType.getTypeName(),
				testRepo.getByName(testType.getTypeName()).getTypeName());
		
		
	}

}
