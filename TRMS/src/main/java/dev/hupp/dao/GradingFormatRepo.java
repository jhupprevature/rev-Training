package dev.hupp.dao;

import dev.hupp.models.GradingFormat;

public interface GradingFormatRepo extends GenericRepo<GradingFormat> {
	public GradingFormat getByName(String name);
}
