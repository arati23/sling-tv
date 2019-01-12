/**
 * 
 */
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

/**
 * @author Nithya Nair
 *
 */

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ResponsiveTabsModel {
	
	private static final Logger	LOGGER	= LoggerFactory.getLogger(ResponsiveTabsModel.class);
	
	@Inject
	private String tabsId;
	
	@Inject
	private Resource tabs;
		
	private List<ResponsiveTabsItemsModel> lstTabItems;

	
	@PostConstruct
	public final void init() {
		
		lstTabItems = getResponsiveTabsItems();
		
	}
	
	private List<ResponsiveTabsItemsModel>getResponsiveTabsItems(){
		
		List<ResponsiveTabsItemsModel> tabsItems = new ArrayList<>();
		
		if (null!=tabs) {
			
			Iterator<Resource> resChildren  = tabs.listChildren();
			while(resChildren.hasNext()) {
				tabsItems.add(resChildren.next().adaptTo(ResponsiveTabsItemsModel.class));
			}
		}
		
		LOGGER.debug("The item size is {}",tabsItems.size());
		 return tabsItems;
		
	}

	public String getTabsId() {
		return tabsId;
	}
	
	
	public List<ResponsiveTabsItemsModel> getTabs() {
		return this.lstTabItems;
	}
	
	
	
	

}
