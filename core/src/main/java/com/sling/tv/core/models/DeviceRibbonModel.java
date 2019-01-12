package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class DeviceRibbonModel {
	
	@Inject
	private String deviceRibbonSharedComponentId;
	
	@Inject
	private String oneRow;

	@Inject
	private String deviceRibbonPath;


	public String getDeviceRibbonPath() {
		return deviceRibbonPath;
	}

	public String getDeviceRibbonSharedComponentId() {
		return deviceRibbonSharedComponentId;
	}

	public String getOneRow() {
		return oneRow;
	}

}
