package com.sling.tv.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class ContainerModel {

	/*
	* @author Krishna Gunturu
	*
	 */

	/** Default log. */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private String containerCmpId;

	@Inject
	private String containerTheme;

	@Inject
	private String fullWidthOptions;

	@Inject
	private String border;

	@Inject
	private String anchorTag;

	@Inject
	private String mobileContent;

	@Inject
	private String maxWidthOptions;

	@Inject
	private String paddingRight;

	@Inject
	private String paddingLeft;

	@Inject
	private String paddingLeftMobile;

	@Inject
	private String paddingRightMobile;

	public String getContainerCmpId() {
		return containerCmpId;
	}

	public String getContainerTheme() {
		return containerTheme;
	}

	public String getFullWidthOptions() {
		return fullWidthOptions;
	}

	public String getBorder() {
		return border;
	}

	public String getAnchorTag() {
		return anchorTag;
	}

	public String getMobileContent() {
		return mobileContent;
	}

	public String getMaxWidthOptions() {
		return maxWidthOptions;
	}

	public String getPaddingRight() { return paddingRight; }

	public String getPaddingLeft() { return paddingLeft; }

	public String getPaddingLeftMobile() { return paddingLeftMobile; }

	public String getPaddingRightMobile() { return paddingRightMobile; }
}