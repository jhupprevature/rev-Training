package dev.hupp.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dev.hupp.dao.DeptRepo;
import dev.hupp.models.Department;
import dev.hupp.models.Employee;
import dev.hupp.util.HibernateUtil;

public class DeptRepoHBImpl extends GenericRepoHBImpl<Department> implements DeptRepo {
	
	@Override
	public Department getDeptByName(String name) {
		Session sess = HibernateUtil.getSession();
		Department dept = null;
		
		
		try {
			Criteria crit = sess.createCriteria(Department.class);
			crit.add(Restrictions.eq("deptName", name));
			
			if(crit.list().size() > 0) {
				dept = (Department)crit.list().get(0);
			}
			
//			a = (Actor) crit.uniqueResult();			
//			if(crit.list().size() > 0)
//				a = (Actor) crit.list().get(0);
//			CriteriaQuery<Actor> crit = sess.getCriteriaBuilder().createQuery(Actor.class);
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		
		return dept;
	}

	@Override
	public Department getDeptByHead(Employee deptHead) {
		repoLog.trace("In the getDeptByHead method.");
		Session sess = HibernateUtil.getSession();
		Department dept = null;
		//EmpRepo empRepo = new EmpRepoHBImpl();
		repoLog.debug("deptHead="+deptHead.getID());
		
		try {
			repoLog.debug("Hello from the try block...");
			Criteria crit = sess.createCriteria(Department.class);
			repoLog.debug("Before Restriction: crit.list()="+crit.list());
			//Would this work if you only had partial match of employee info???
			crit.add(Restrictions.eq("deptHead", deptHead));
			repoLog.debug("After Restriction: crit.list()="+crit.list());
			
			if(crit.list().size() > 0) {
				dept = (Department)crit.list().get(0);
			}
			
		} catch (HibernateException e) {
			repoLog.debug("Hello from the catch block...");
			e.printStackTrace();
		} finally {
			sess.close();
		}
		
		return dept;
	}

//	@Override
//	public List<Employee> getDeptEmployees(String name) {
//		Session sess = HibernateUtil.getSession();
//		List<Employee> employees = null;
//		int deptID = this.getDeptByName(name).getID();
//		
//		try {
//			Criteria crit = sess.createCriteria(Employee.class);
//			crit.add(Restrictions.eq("department", deptID));
//			
//			if(crit.list().size() > 0) {
//				employees = (List<Employee>)crit.list();
//			}
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		} finally {
//			sess.close();
//		}
//		
//		return employees;
//	}
}
