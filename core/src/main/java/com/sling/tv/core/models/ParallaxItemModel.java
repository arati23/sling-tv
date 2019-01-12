package com.sling.tv.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ParallaxItemModel {

	
	@Inject
	private String imageWidth;
	
	@Inject
	private String imagePath;
	
	@Inject
	private String startX;
	
	@Inject
	private String startY;
	
	@Inject
	private String endX;
	
	@Inject
	private String endY;

	public String getImageWidth() {
		return imageWidth;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getStartX() {
		return startX;
	}

	public String getStartY() {
		return startY;
	}

	public String getEndX() {
		return endX;
	}

	public String getEndY() {
		return endY;
	}
	
}
