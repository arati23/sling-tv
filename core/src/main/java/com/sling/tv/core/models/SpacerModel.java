package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import javax.inject.Inject;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class SpacerModel {
	
	@Inject
	private String lineStyle;
	
	@Inject
	private String spacerId;

	@Inject
	private String heightInPixel;

	@Inject
	private String heightInPercentage;

	public String getLineStyle() {
		return lineStyle;
	}

	public String getSpacerId() {
		return spacerId;
	}

	public String getHeightInPixel() {
		return heightInPixel;
	}

	public String getHeightInPercentage() {
		return heightInPercentage;
	}

}
