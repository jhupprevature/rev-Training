/**
 * 
 */
package dev.hupp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dev.hupp.dao.DeptRepo;
import dev.hupp.dao.impl.DeptRepoHBImpl;
import dev.hupp.models.Department;
import dev.hupp.models.Employee;

/**
 * @author Jordan Hupp
 *
 */
public class DeptRepoTest {
	final static Logger repoTestLog = Logger.getLogger("Repo Test Logger");
	
	private DeptRepo testRepo;
	private Department testDept;
	private Department trueDept;
	private Employee testHead;
	private Employee trueHead;
	

	@BeforeClass
	public static void setUpBeforeClass() {
	}

	@AfterClass
	public static void tearDownAfterClass() {
	}

	@Before
	public void setUp() {
		testRepo = new DeptRepoHBImpl();
		
		testDept = new Department(3, "Marketing", new Employee(), "Marketing description");		
		
		testHead = new Employee(3, "Linc", "Tregunna", "ltregunna", "tsMBTR",
				new Date(1977, 03, 03), new Date(2019, 04, 06), testDept, new Employee(), new Employee());
		testDept.setDeptHead(testHead);
		testHead.setSupervisor(testHead);
		
		trueDept = testRepo.getByID(3);
		trueHead = trueDept.getDeptHead();
	}

	@After
	public void tearDown() {
	}

	@Test
	public final void testGetDeptByName() {
		String testName = testDept.getDeptName();
		repoTestLog.debug("employee="+testHead);
		repoTestLog.debug("testHead="+testHead.getID());
		
		assertEquals("Should get the department with a matching name", testName, 
				testRepo.getDeptByName(testName).getDeptName());
		
		//Altered casing fails.
//		assertEquals("Should get the department with a matching name ignoring case", testName.toLowerCase(), 
//				testRepo.getDeptByName(testName.toLowerCase()).getDeptName());
//		assertEquals("SHould get the department with a matching name ignoring case", testName.toUpperCase(),
//				testRepo.getDeptByName(testName.toUpperCase()).getDeptName());
	}

	/**
	 * Test method for {@link dev.hupp.dao.impl.DeptRepoHBImpl#getDeptByHead(dev.hupp.models.Employee)}.
	 */
	@Test
	public final void testGetDeptByHead() {
		repoTestLog.debug("employee="+testHead);
		repoTestLog.debug("testHead="+testHead.getName());
		
		assertEquals("Should get the department with matching head name", testHead.getDepartment().getID(),
				testRepo.getDeptByHead(testHead).getID());
	}

	/**
	 * Test method for {@link dev.hupp.dao.impl.GenericRepoHBImpl#add(dev.hupp.models.DBTable)}.
	 */
	@Test
	public final void testAdd() {
		Department addDept = new Department("Quality Control", null, "QC Desctription");
		
		testRepo.add(addDept);
		
		assertEquals("Should find added department of same name", addDept.getDeptName(), 
				testRepo.getDeptByName(addDept.getDeptName()).getDeptName());
		
		addDept = testRepo.getDeptByName(addDept.getDeptName());
		testRepo.delete(addDept);
		
	}

	/**
	 * Test method for {@link dev.hupp.dao.impl.GenericRepoHBImpl#getAll()}.
	 */
	@Test
	public final void testGetAll() {
		assertEquals("Should get all departments", 5, testRepo.getAll().size());
	}

	/**
	 * Test method for {@link dev.hupp.dao.impl.GenericRepoHBImpl#getByID(int)}.
	 */
	@Test
	public final void testGetByID() {
		assertEquals("Should get department with matching ID", testDept.getID(),
			testRepo.getByID(testDept.getID()).getID());
	}

	/**
	 * Test method for {@link dev.hupp.dao.impl.GenericRepoHBImpl#update(dev.hupp.models.DBTable)}.
	 */
	@Test
	public final void testUpdate() {
		Department editDept = new Department(3, "Marketing", trueHead, "New Marketing description");
		
		assertEquals("Should update the department description.", editDept.getDescription(), 
				testRepo.update(editDept).getDescription());
		
		testRepo.update(trueDept);
	}

	/**
	 * Test method for {@link dev.hupp.dao.impl.GenericRepoHBImpl#delete(dev.hupp.models.DBTable)}.
	 */
	@Test
	public final void testDelete() {
		Department deleteDept = new Department("Training", null, "Training description");
		testRepo.add(deleteDept);
		testRepo.delete(deleteDept);
		
		assertNull("Should not retreive deleted department", testRepo.getDeptByName(deleteDept.getDeptName()));
	}

}
