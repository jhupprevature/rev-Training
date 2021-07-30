package dev.hupp.services.impl;

import dev.hupp.dao.GenericRepo;
import dev.hupp.dao.impl.GFRepoHBImpl;
import dev.hupp.models.GradingFormat;
import dev.hupp.services.GradingFormatService;

public class GradingFormatSvcImpl extends GenericSvcImpl<GradingFormat> implements GradingFormatService {
	private GFRepoHBImpl grdRepo;

	public GradingFormatSvcImpl(GenericRepo<GradingFormat> genRepo) {
		super(genRepo);
		grdRepo = (GFRepoHBImpl) genRepo;
	}

}
