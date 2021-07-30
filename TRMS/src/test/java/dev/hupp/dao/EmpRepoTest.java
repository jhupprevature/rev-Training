package dev.hupp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dev.hupp.dao.impl.EmpRepoHBImpl;
import dev.hupp.models.Department;
import dev.hupp.models.Employee;

public class EmpRepoTest {
	EmpRepo testRepo;
	Employee testEmp;

	@Before
	public void setUp() throws Exception {
		testRepo = new EmpRepoHBImpl();

		testEmp = new Employee(10, "Ferris", "Wyllie", "fwyllie", "fAiE7tnRbry0", new Date(1986, 10, 19),
				new Date(2019, 12, 15), new Department(), new Employee(), new Employee());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEmpByName() {
		String testName = testEmp.getName();

		assertEquals("Should get the employee with a matching name", testName,
				testRepo.getEmpByName(testName).get(0).getName());
//		assertEquals("Should get the department with a matching name ignoring case", testName, 
//				testRepo.getDeptByName(testName.toLowerCase()).getDeptName());
//		assertEquals("SHould get the department with a matching name ignoring case", testName,
//				testRepo.getDeptByName(testName.toUpperCase()).getDeptName());
	}

	@Test
	public void testGetEmpByUsername() {
		String testUser = testEmp.getUsername();
		
		assertEquals("Should get the employee with a matching username", testUser,
				testRepo.getEmpByUsername(testUser).getUsername());
	}

}
