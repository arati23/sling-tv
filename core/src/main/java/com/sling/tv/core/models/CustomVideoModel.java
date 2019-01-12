/**
 * 
 */
package com.sling.tv.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;


@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CustomVideoModel {
	
	@Inject
	private String imageLink;

	public String getImageLink() {

		return this.imageLink;

	}
	
	@Inject
	private String altText;

	public String getAltText() {

		return this.altText;

	}
	
	@Inject
	private String imageWidth;

	public String getImageWidth() {

		return this.imageWidth;

	}
	
	@Inject
	private String paddingLeft;

	public String getPaddingLeft() {

		return this.paddingLeft;

	}
	
	@Inject
	private String paddingRight;

	public String getPaddingRight() {

		return this.paddingRight;

	}
	
	@Inject
	private String videoLink;

	public String getVideoLink() {

		return this.videoLink;

	}
	
	@Inject
	private String damVideoLink;

	public String getDamVideoLink() {

		return this.damVideoLink;

	}
	@Inject
	private String autoPlay;

	public String getAutoPlay() {

		return this.autoPlay;

	}
	
@Inject
private String customVideoComponentId;

public String getCustomVideoComponentId() {

	return this.customVideoComponentId;

}

}
