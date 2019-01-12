package com.sling.tv.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class TileGridModel {
	
	@Self
	Resource resource;
	
	@Inject
	private String hideBreakout;
	
	@Inject
	private String tcgGridId;
	
	@Inject
	private Resource tiles;
	
	
	
	private List<TileGridItemModel> tileGridItems;


	public String getNodeName() {

		return resource.getName();
	}

	@PostConstruct
	public final void init() {
		// Populate multifield
		tileGridItems = getTileItems();
	}
	
	private List<TileGridItemModel> getTileItems() {
		List<TileGridItemModel> tileItems = new ArrayList<>();
		if (null != tiles ) {
			Iterator<Resource> resourceChildren = tiles.listChildren();
			while (resourceChildren.hasNext())
			{
				tileItems.add(resourceChildren.next().adaptTo(TileGridItemModel.class));
			}
			
		}
		
		return tileItems;
	}
	
	public List<TileGridItemModel> getTiles() {
		return this.tileGridItems;
	}

	
	public String getHideBreakout() {
		return hideBreakout;
	}

	public String getTcgGridId() {
		return tcgGridId;
	}

}
