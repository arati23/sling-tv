package com.sling.tv.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import org.apache.sling.models.annotations.Model;

/*
 * This class is used to map the column container resource node
 * Total 12 properties are being used
 */

@Model(adaptables = Resource.class,

		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL

)

public class ColumnContainerModel {

	@Inject
	private String setup;

	@Inject
	private String colOverride;

	@Inject
	private String column1Val;
	
	@Inject
	private String column2Val;
	
	@Inject
	private String column3Val;
	
	@Inject
	private String column4Val;
	
	@Inject
	private String column5Val;
	
	@Inject
	private String column6Val;
	
	@Inject
	private String alignment;
	
	@Inject
	private String colPadding;
	
	@Inject
	private String fixedContainerCheck;
	
	@Inject
	private String carouselMode;
	
	@Inject
	private String flexColumnContainerId;

	public String getFlexColumnContainerId() {
		return flexColumnContainerId;
	}

	public String getSetup() {
		return setup;
	}

	public String getColOverride() {
		return colOverride;
	}

	public String getColumn1Val() {
		return column1Val;
	}

	public String getColumn2Val() {
		return column2Val;
	}

	public String getColumn3Val() {
		return column3Val;
	}

	public String getColumn4Val() {
		return column4Val;
	}

	public String getColumn5Val() {
		return column5Val;
	}

	public String getColumn6Val() {
		return column6Val;
	}

	public String getAlignment() {
		return alignment;
	}

	public String getColPadding() {
		return colPadding;
	}

	public String getFixedContainerCheck() {
		return fixedContainerCheck;
	}

	public String getCarouselMode() {
		return carouselMode;
	}

	
}
