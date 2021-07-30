package dev.hupp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dev.hupp.dao.GenericRepo;
import dev.hupp.models.DBTable;
import dev.hupp.util.HibernateUtil;

public class GenericRepoHBImpl<T extends DBTable> implements GenericRepo<T>{
	@SuppressWarnings("unchecked")
	private Class<T> c = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()
			).getActualTypeArguments()[0]);
	
	public void add(T entity) {
		//Problems encountered when entity has an entity field...
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = sess.beginTransaction();
			sess.persist(entity);
			tx.commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			entity = null;
		} finally {
			sess.close();
		}		
	}

	@SuppressWarnings("unchecked")
	public List<? extends DBTable> getAll() {
		Session sess = HibernateUtil.getSession();
		List<? extends DBTable> entities = null;
		String tableName = c.getName();
		
		try {
			entities = (List<? extends DBTable>) sess.createQuery("FROM "+ tableName).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return entities;
	}

	public T getByID(int id) {
		Session sess = HibernateUtil.getSession();
		T entity = null;
		
		try {
			entity = (T) sess.get(c, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public T update(T edit) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = sess.beginTransaction();
			
			edit = (T) sess.merge(edit);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			sess.close();
		}
		
		return edit;

	}

	public T delete(T entity) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = sess.beginTransaction();
			sess.delete(entity);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		} finally {
			sess.close();
		}

		return entity;

	}

}
