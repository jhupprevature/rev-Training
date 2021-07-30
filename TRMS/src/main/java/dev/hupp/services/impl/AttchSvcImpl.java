package dev.hupp.services.impl;

import dev.hupp.dao.AttachmentRepo;
import dev.hupp.models.RequestAttachment;
import dev.hupp.services.AttachmentService;

public class AttchSvcImpl extends GenericSvcImpl<RequestAttachment> implements AttachmentService {
	private AttachmentRepo attchRepo;

	public AttchSvcImpl(AttachmentRepo ar) {
		super(ar);
		attchRepo = (AttachmentRepo) genRepo;
	}

	@Override
	public AttachmentRepo getRepo() {
		return attchRepo;
	}

}
