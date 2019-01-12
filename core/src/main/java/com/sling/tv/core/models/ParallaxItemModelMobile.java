package com.sling.tv.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ParallaxItemModelMobile {

	
	@Inject
	private String imageWidthMobile;
	
	@Inject
	private String imagePathMobile;
	
	@Inject
	private String startXMobile;
	
	@Inject
	private String startYMobile;
	
	@Inject
	private String endXMobile;
	
	@Inject
	private String endYMobile;

	public String getImageWidthMobile() {
		return imageWidthMobile;
	}

	public String getImagePathMobile() {
		return imagePathMobile;
	}

	public String getStartXMobile() {
		return startXMobile;
	}

	public String getStartYMobile() {
		return startYMobile;
	}

	public String getEndXMobile() {
		return endXMobile;
	}

	public String getEndYMobile() {
		return endYMobile;
	}

	
	
}
