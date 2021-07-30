package dev.hupp.services;

import java.util.List;

import dev.hupp.dao.GenericRepo;
import dev.hupp.models.DBTable;

public interface GenericService<T extends DBTable> {
	public void add(T entity);
	
	public List<? extends DBTable> getAll();
	public T getByID(int id);
	
	public T update(T edit);
	
	public T delete(T entity);

	public GenericRepo<T> getRepo();
}
