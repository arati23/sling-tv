/**
 * 
 */
package com.sling.tv.core.models;


import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;


/**
 * @author Nithya Nair
 *
 */

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class ProgramRibbonModelItem {
	
	
	 @Inject
	 private String imagePath;

	public String getImagePath() {
		return this.imagePath;
	}
	
	
}
