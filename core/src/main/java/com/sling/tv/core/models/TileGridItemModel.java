package com.sling.tv.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class TileGridItemModel {
	
	private static Logger LOG = LoggerFactory.getLogger(TileGridItemModel.class);
	
	@Inject
	private String tabUrl;
	
	@Inject
	private String tabTitle;
	
	@Inject
	private String tabIcon;
	
	@Inject
	private String uniqueId;

	public String getUniqueId() {

		return this.uniqueId;

	}

	public String getTabUrl() {
		return tabUrl;
	}

	public String getTabTitle() {
		return tabTitle;
	}

	public String getTabIcon() {
		return tabIcon;
	}
	
	

}
