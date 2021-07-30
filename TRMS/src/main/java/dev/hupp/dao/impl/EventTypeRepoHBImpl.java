package dev.hupp.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dev.hupp.dao.EventTypeRepo;
import dev.hupp.models.EventType;
import dev.hupp.util.HibernateUtil;

public class EventTypeRepoHBImpl extends GenericRepoHBImpl<EventType> implements EventTypeRepo {

	@Override
	public EventType getByName(String name) {
		Session sess = HibernateUtil.getSession();
		EventType type = null;
		
		try {
			Criteria crit = sess.createCriteria(EventType.class);
			crit.add(Restrictions.eq("typeName", name));
			
			if(crit.list().size() > 0) {
				type = (EventType)crit.list().get(0);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		
		return type;
	}

}
