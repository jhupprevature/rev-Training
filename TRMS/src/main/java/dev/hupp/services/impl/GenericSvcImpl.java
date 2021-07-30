package dev.hupp.services.impl;

import java.util.List;

import org.apache.log4j.Logger;

import dev.hupp.dao.GenericRepo;
import dev.hupp.models.DBTable;
import dev.hupp.services.GenericService;

public class GenericSvcImpl<T extends DBTable> implements GenericService<T> {
	protected GenericRepo<T> genRepo;
	protected Logger log = Logger.getLogger("Service Logger");
	
	public GenericSvcImpl(GenericRepo<T> genRepo) {
		this.genRepo = genRepo;
	}

	public void add(T entity) {
		genRepo.add(entity);
	}

	public List<? extends DBTable> getAll() {
		return genRepo.getAll();
	}

	public T getByID(int id) {
		return genRepo.getByID(id);
	}

	public T update(T edit) {
		return genRepo.update(edit);
	}

	public T delete(T entity) {
		return genRepo.delete(entity);
	}

	public GenericRepo<T> getRepo() {
		return genRepo;
	}
	
	
}
