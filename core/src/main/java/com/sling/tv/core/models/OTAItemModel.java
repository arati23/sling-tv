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
public class OTAItemModel {
	
	private static Logger LOG = LoggerFactory.getLogger(OTAItemModel.class);
	
	@Inject
	private String offerFlagImage;

	@Inject
	private String offerRibbonText;

	@Inject
	private String offerImage;

	@Inject
	private String offerPrice;

	@Inject
	private String offerHeader;

	@Inject
	private String offerDescription;

	@Inject
	private String offerCaveat;

	public String getOfferFlagImage() { return offerFlagImage; }

	public String getOfferRibbonText() {
		return offerRibbonText;
	}

	public String getOfferImage() {
		return offerImage;
	}

	public String getOfferPrice() {
		return offerPrice;
	}

	public String getOfferHeader() {
		return offerHeader;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public String getOfferCaveat() {
		return offerCaveat;
	}
}
