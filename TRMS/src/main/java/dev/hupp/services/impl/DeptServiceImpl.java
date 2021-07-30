package dev.hupp.services.impl;

import dev.hupp.dao.DeptRepo;
import dev.hupp.dao.GenericRepo;
import dev.hupp.models.Department;
import dev.hupp.services.DeptService;

public class DeptServiceImpl extends GenericSvcImpl<Department> implements DeptService {
	DeptRepo dptRepo;
	
	public DeptServiceImpl(GenericRepo<Department> dptRepo) {
		super(dptRepo);
		this.dptRepo = (DeptRepo) dptRepo;
	}
	
}
