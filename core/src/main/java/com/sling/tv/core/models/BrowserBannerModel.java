package com.sling.tv.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import org.apache.sling.models.annotations.Model;


@Model(adaptables = Resource.class,

		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL

)

public class BrowserBannerModel {

	@Inject
	private String bannerText;

	@Inject
	private String alt;

	@Inject
	private String imagePath;
	
	@Inject
	private String browserSpecificComponentId;

	public String getBannerText() {
		return bannerText;
	}

	public String getAlt() {
		return alt;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getBrowserSpecificComponentId() {
		return browserSpecificComponentId;
	}
	
		
}
