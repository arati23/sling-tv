package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Model(adaptables = Resource.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ImageCarouselItemModel {


	@Inject
	private String carouselColorBar;

	@Inject
	private String imagePath;

	@Inject
	private String imageUrl;

	@Inject
	private String alt;

	public String getCarouselColorBar() {
		return carouselColorBar;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getAlt() {
		return alt;
	}
}
