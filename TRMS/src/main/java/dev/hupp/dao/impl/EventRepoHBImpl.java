package dev.hupp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dev.hupp.dao.EventRepo;
import dev.hupp.models.Event;
import dev.hupp.util.HibernateUtil;

public class EventRepoHBImpl extends GenericRepoHBImpl<Event> implements EventRepo {

	@Override
	public List<Event> getEventsByLocation(String location) {
		Session sess = HibernateUtil.getSession();
		List<Event> evnt = null;
		
		try {
			Criteria crit = sess.createCriteria(Event.class);
			crit.add(Restrictions.eq("eventLocation", location));
			
			if(crit.list().size() > 0) {
				evnt = (List<Event>)crit.list();
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		
		return evnt;
	}

}
