package dev.hupp.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dev.hupp.dao.GradingFormatRepo;
import dev.hupp.models.GradingFormat;
import dev.hupp.util.HibernateUtil;

public class GFRepoHBImpl extends GenericRepoHBImpl<GradingFormat> implements GradingFormatRepo {

	@Override
	public GradingFormat getByName(String name) {
		Session sess = HibernateUtil.getSession();
		GradingFormat format = null;
		
		try {
			Criteria crit = sess.createCriteria(GradingFormat.class);
			crit.add(Restrictions.eq("formatName", name));
			
			if(crit.list().size() > 0) {
				format = (GradingFormat)crit.list().get(0);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		
		return format;
	}

}
