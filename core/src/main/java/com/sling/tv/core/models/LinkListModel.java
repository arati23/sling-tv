package com.sling.tv.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class LinkListModel {

	@Inject
	private String linkListId;

	@Inject
	private String linkType;

	@Inject
	private String mobileHeight;

	@Inject
	private String tabletHeight;

	@Inject
	private String desktopHeight;

	@Inject
	private Resource linkListItemsCTA;

	@Inject
	private Resource linkListItems;

	private List<LinkListItemModel> linkListItemsCTAList;

	private List<LinkListItemModel> linkListItemsList;

	@PostConstruct
	public final void init() {
		// Populate multifield
		linkListItemsCTAList = getLinkCTAItems();

		linkListItemsList = getLinkItems();

	}

	private List<LinkListItemModel> getLinkCTAItems() {
		List<LinkListItemModel> linkCTAItems = new ArrayList<>();
		if (null != linkListItemsCTA ) {
			Iterator<Resource> resourceChildren = linkListItemsCTA.listChildren();
			while (resourceChildren.hasNext())
			{
				linkCTAItems.add(resourceChildren.next().adaptTo(LinkListItemModel.class));
			}

		}

		return linkCTAItems;
	}

	private List<LinkListItemModel> getLinkItems() {
		List<LinkListItemModel> linkItems = new ArrayList<>();
		if (null != linkListItems ) {
			Iterator<Resource> resourceChildren = linkListItems.listChildren();
			while (resourceChildren.hasNext())
			{
				linkItems.add(resourceChildren.next().adaptTo(LinkListItemModel.class));
			}

		}

		return linkItems;
	}

	public String getLinkListId() {
		return linkListId;
	}

	public String getLinkType() {
		return linkType;
	}

	public String getMobileHeight() {
		return mobileHeight;
	}

	public String getTabletHeight() {
		return tabletHeight;
	}

	public String getDesktopHeight() {
		return desktopHeight;
	}

	public List<LinkListItemModel> getLinkListItemsCTA() {
		return linkListItemsCTAList;
	}

	public List<LinkListItemModel> getLinkListItems() {
		return linkListItemsList;
	}
}
