package dev.hupp.dao;

import java.util.List;
import org.apache.log4j.Logger;

import dev.hupp.models.DBTable;

public interface GenericRepo<T extends DBTable> {
	final static Logger repoLog = Logger.getLogger("Repo Logger");
	
	public void add(T entity);
	
	public List<? extends DBTable> getAll();
	public T getByID(int id);
	
	public T update(T edit);
	
	public T delete(T entity);
}
