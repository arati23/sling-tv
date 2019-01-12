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


public class RegionLogoLinkModel {
	
	private static Logger LOG = LoggerFactory.getLogger(RegionLogoLinkModel.class);
	
	LinkCheckerUtil lcu = new LinkCheckerUtil();
	
	@Inject
	private String regionLogo;
	
	@Inject
	private String regionLink;
	
	public String getRegionLogo() {
		return regionLogo;
	}

	public String getRegionLink() {
		return lcu.absoluteUrl(regionLink);
	}
	
	
	
	
	
}
