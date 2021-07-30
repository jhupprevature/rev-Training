package dev.hupp.services.impl;

import dev.hupp.dao.AttachmentRepo;
import dev.hupp.dao.CommunicationRepo;
import dev.hupp.dao.RequestRepo;
import dev.hupp.models.Communication;
import dev.hupp.models.ReimbursementRequest;
import dev.hupp.models.RequestAttachment;
import dev.hupp.services.AttachmentService;
import dev.hupp.services.CommunicationService;
import dev.hupp.services.GenericService;
import dev.hupp.services.RequestService;

public class RequestServiceImpl extends GenericSvcImpl<ReimbursementRequest> implements RequestService {
	private RequestRepo reqRepo;
	private CommunicationRepo commRepo;
	private AttachmentRepo attchRepo;
	
	private CommunicationService commSvc;
	private AttachmentService attchSvc;
	

	public RequestServiceImpl(RequestRepo rr, CommunicationRepo cr, AttachmentRepo ar) {
		super(rr);
		reqRepo = (RequestRepo) genRepo;
		
		setCommSvc(new CommSvcImpl(cr));
		commRepo = (CommunicationRepo) commSvc.getRepo();
		
		setAttchSvc(new AttchSvcImpl(ar));
		attchRepo = (AttachmentRepo) attchSvc.getRepo();
	}

	public CommunicationService getCommSvc() {
		return commSvc;
	}

	public void setCommSvc(CommunicationService commSvc) {
		this.commSvc = commSvc;
	}

	public AttachmentService getAttchSvc() {
		return attchSvc;
	}

	public void setAttchSvc(AttachmentService attchSvc) {
		this.attchSvc = attchSvc;
	}

}
