package com.sling.tv.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

/**
 * Sling model for holding the Channel Page properties.
 *
 * @author vhs
 *
 */
@Model(adaptables = Resource.class)
public class DevicePage {

	@Inject
	@Named("jcr:title")
	private String title;

		@Inject
	@Optional
	@Named("imagePath")
	private String imagePath;

	@Inject
	@Named("deviceAlt")
	@Optional
	private String deviceAlt;
	
	private List<DevicePage> children = new ArrayList<>();
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getDeviceAlt() {
		return deviceAlt;
	}

	public void setDeviceAlt(String deviceAlt) {
		this.deviceAlt = deviceAlt;
	}

	public List<DevicePage> getChildren() {
		return children;
	}

	public void setChildren(List<DevicePage> children) {
		this.children = children;
	}


	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}
}

