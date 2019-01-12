package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;


/**
 * @author Dileep Muraleedharan
 *
 */

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CTADropDownItemsModel {

	@Inject
	private String ctaLogo;
	
	@Inject
	private String ctaPath;
	
	private String resourceType;
	
    private String id;
	
	private String info;
	
	public String getCtaLogo() {
		return ctaLogo;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceType() {
		return resourceType;
	}

	public String getCtaPath() {
		return ctaPath;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
