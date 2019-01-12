/**
 * 
 */
package com.sling.tv.core.models;

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
public class SubNavigationItemsModel {
	
	@Inject
	private String subNavTitle;
	
	@Inject
	private String targetId;

	public String getSubNavTitle() {
		return subNavTitle;
	}

	public String getTargetId() {
		return targetId;
	}
	
}
