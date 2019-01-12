package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;

import com.adobe.cq.sightly.WCMUsePojo;

public class SubNavModel extends WCMUsePojo {

	@Override
	public void activate() throws Exception {

	}  
	
	/**
	 * Gets the sub-nav items resource.
	 *
	 * @return the sub-nav items resource
	 */
	public Resource getSubNavItemsResource() {
		
		if(this.getResource().hasChildren()) {
			final Resource subNavItemsRes = this.getResource().getChild("subNavItems");
			return subNavItemsRes;
		}
		return null;
	}
	
}
