package com.sling.tv.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ChannelLogosModel {

	private static Logger LOG = LoggerFactory.getLogger(ChannelLogosModel.class);

	@Inject
	private String channelId;

	@Inject
	private Resource channelLogos;

	private List<ChannelLogosItemModel> channelLogosItems;

	@PostConstruct
	public final void init() {
		// Populate multifield
		channelLogosItems = getChennelLogosItems();
	}

	private List<ChannelLogosItemModel> getChennelLogosItems() {
		List<ChannelLogosItemModel> channelItems = new ArrayList<>();
		if (null != channelLogos ) {
			Iterator<Resource> resourceChildren = channelLogos.listChildren();
			while (resourceChildren.hasNext())
			{
				channelItems.add(resourceChildren.next().adaptTo(ChannelLogosItemModel.class));
			}

		}
		LOG.info("Channel Logos Size" + channelItems.size());

		return channelItems;
	}

	public String getChannelId() {
		return channelId;
	}

	public List<ChannelLogosItemModel> getChannelLogos() {
		return channelLogosItems;
	}
}
