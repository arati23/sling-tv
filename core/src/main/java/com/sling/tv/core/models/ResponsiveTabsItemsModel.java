/**
 * 
 */
package com.sling.tv.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.day.cq.commons.jcr.JcrUtil;

/**
 * @author Nithya Nair
 *
 */

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ResponsiveTabsItemsModel {
	
	
	@Inject
	private String tabTitle;
	
	@Inject
	private String tabIcon;
	
	@Inject
	private String defaultTab;
	
	@Inject
	private String hideTab;

	public String getTabTitle() {
		return tabTitle;
	}

	public String getTabIcon() {
		return tabIcon;
	}

	public String getDefaultTab() {
		return defaultTab;
	}

	public String getHideTab() {
		return hideTab;
	}
	
	public String getTabId() {
        return JcrUtil.createValidName(this.tabTitle);
    }

}
