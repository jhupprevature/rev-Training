package dev.hupp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dev.hupp.dao.EmpRepo;
import dev.hupp.models.Employee;
import dev.hupp.util.HibernateUtil;

public class EmpRepoHBImpl extends GenericRepoHBImpl<Employee> implements EmpRepo {

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmpByName(String name) {
		Session sess = HibernateUtil.getSession();
		List<Employee> emps = null;
		
		try {
			Criteria crit = sess.createCriteria(Employee.class);
			
			String[] names = name.split("\\s");
			if (names.length == 1) {
				crit.add(Restrictions.eq("firstName", name));
				crit.add(Restrictions.eq("lastName", name));
			} else {
				crit.add(Restrictions.eq("firstName", names[0]));
				crit.add(Restrictions.eq("lastName", names[1]));
			}
			
			if(crit.list().size() > 0) {
				emps = (List<Employee>)crit.list();
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		
		return emps;
	}

	@Override
	public Employee getEmpByUsername(String username) {
		Session sess = HibernateUtil.getSession();
		Employee emp = null;
		
		try {
			Criteria crit = sess.createCriteria(Employee.class);
			crit.add(Restrictions.eq("username", username.toLowerCase()));
			
			if(crit.list().size() > 0) {
				emp = (Employee)crit.list().get(0);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		
		return emp;
	}

//	@Override
//	public Employee getEmpCollections(Employee user) {
//		Session sess = HibernateUtil.getSession();
//		int userID = user.getID();
//		
//		try {
//			user = (Employee) sess.createQuery("FROM Employee WHERE id="+userID, Employee.class);
//			user.getBeneficiaries();
//			user.getDeptBeingLed();
//			user.getSupervisees();	
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		} finally {
//			sess.close();
//		}
//		return user;
//	}

	

}
