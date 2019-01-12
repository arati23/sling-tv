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
public class ChannelLogosItemModel {
	
	@Inject
	private String channelImage;
	
	@Inject
	private String altName;

	public String getChannelImage() {
		return channelImage;
	}

	public String getAltName() {
		return altName;
	}
}
