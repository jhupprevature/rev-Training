package dev.hupp.dao;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import dev.hupp.dao.impl.GFRepoHBImpl;
import dev.hupp.models.GradingFormat;

public class GradingFormatRepoTest {
	final static Logger repoTestLog = Logger.getLogger("Repo Test Logger");
	
	private GradingFormatRepo testRepo;
	private GradingFormat testFormat;
//	private GradingFormat trueFormat;
	

	@Before
	public void setUp() throws Exception {
		testRepo = new GFRepoHBImpl();
		
		testFormat = new GradingFormat("Letter", "Letter Format desc", "C");
		
//		trueFormat = testRepo.getByID(1);
	}

	@Test
	public void testGetByName() {
		
		assertEquals("hould retrieve format of same name", testFormat.getFormatName(),
				testRepo.getByName(testFormat.getFormatName()).getFormatName());
	}

}
