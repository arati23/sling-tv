package com.sling.tv.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sling.tv.core.utils.LinkCheckerUtil;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)


public class RegionTextLinkModel {
	
	private static Logger LOG = LoggerFactory.getLogger(RegionTextLinkModel.class);
	
	LinkCheckerUtil lcu = new LinkCheckerUtil();

	@Inject
	private String regionName;
	
	@Inject
	private String regionLink;
	
	@Inject
	private String regionNameNew;
	
	@Inject
	private String regionLinkNew;


	public String getRegionName() {
		return regionName;
	}

	public String getRegionLink() {
		return lcu.absoluteUrl(regionLink);
	}
	
	public String getRegionNameNew() {
		return regionNameNew;
	}

	public String getRegionLinkNew() {
		return lcu.absoluteUrl(regionLinkNew);
	}
	
	
	
	
	
}
