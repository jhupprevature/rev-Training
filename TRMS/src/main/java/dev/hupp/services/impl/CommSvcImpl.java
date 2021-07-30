package dev.hupp.services.impl;

import dev.hupp.dao.CommunicationRepo;
import dev.hupp.models.Communication;
import dev.hupp.services.CommunicationService;

public class CommSvcImpl extends GenericSvcImpl<Communication> implements CommunicationService {
	private CommunicationRepo commRepo;

	public CommSvcImpl(CommunicationRepo cr) {
		super(cr);
		commRepo = (CommunicationRepo) genRepo;
	}
	
	public CommunicationRepo getRepo() {
		return commRepo;
	}
	

}
