/**
 * 
 */
package com.sling.tv.core.models;

import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import com.sling.tv.core.utils.LinkCheckerUtil;

/**
 * @author Nithya Nair
 *
 */


@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class SocialMediaItemsModel {
	
	LinkCheckerUtil lcu = new LinkCheckerUtil();
	
	@Inject
	private String socialMediaLink;
	
	@Inject
	private String socialMediaIcon;

	public String getSocialMediaLink() {
		return lcu.absoluteUrl(this.socialMediaLink);
	}

	public String getSocialMediaIcon() {
		return this.socialMediaIcon;
	}

	

	
	
}
