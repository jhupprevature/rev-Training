package dev.hupp.dao.impl;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dev.hupp.dao.RequestRepo;
import dev.hupp.models.ReimbursementRequest;
import dev.hupp.util.HibernateUtil;

public class RequestRepoHBImpl extends GenericRepoHBImpl<ReimbursementRequest> implements RequestRepo {
	
	@Override
	public ReimbursementRequest update(ReimbursementRequest edit) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = sess.beginTransaction();
			
			edit.setLastUpdated(Date.valueOf(LocalDate.now()));
			edit = (ReimbursementRequest) sess.merge(edit);
			
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			sess.close();
		}
		
		return edit;

	}
}
