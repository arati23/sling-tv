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

/**
 * @author Dileep Muraleedharan
 *
 */

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class SubNavigationModel {
	
	
	 @Inject
     private Resource subNavItems;
	
	private List<SubNavigationItemsModel> navItems;
	
	
	public List<SubNavigationItemsModel> getSubNavItems() {
				return this.navItems;
			}
	
	
	@PostConstruct
	public final void init() {
			// Populate multifield
		navItems = getNavigationItems();
		}
	
	private List<SubNavigationItemsModel> getNavigationItems() {
	  	List<SubNavigationItemsModel> naviItemList = new ArrayList<>();
	 	if (null != subNavItems ) {
			Iterator<Resource> resourceChildren = subNavItems.listChildren();
			while (resourceChildren.hasNext())
			{
				naviItemList.add(resourceChildren.next().adaptTo(SubNavigationItemsModel.class));
			}
			
		}

		return naviItemList;
	}

}
