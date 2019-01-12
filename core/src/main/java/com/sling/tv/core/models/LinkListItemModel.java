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
public class LinkListItemModel {
	
	private static Logger LOG = LoggerFactory.getLogger(LinkListItemModel.class);
	
	@Inject
	private String title;
	
	@Inject
	private String link;
	
	@Inject
	private String titleCTA;

	@Inject
	private String linkCTA;

	@Inject
	private String classification;

	@Inject
	private String cartFlow;

	@Inject
	private String cartStep;

	@Inject
	private String deviceType;

	@Inject
	private String planId;

	@Inject
	private String offerId;

	@Inject
	private String packageId;

	@Inject
	private String extra;

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getTitleCTA() {
		return titleCTA;
	}

	public String getLinkCTA() {
		return linkCTA;
	}

	public String getCartFlow() {
		return cartFlow;
	}

	public String getCartStep() {
		return cartStep;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public String getPlanId() {
		return planId;
	}

	public String getOfferId() {
		return offerId;
	}

	public String getPackageId() {
		return packageId;
	}

	public String getExtra() {
		return extra;
	}

	public String getClassification() {
		return classification;
	}
}
