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
 * @author Nithya Nair
 *
 */

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class SocialMediaModel {
	
	
	 @Inject
     private Resource socialMediaLinks;
	 
	 @Inject
	 private String socialMediaLinksId;
	
	public String getSocialMediaLinksId() {
		return socialMediaLinksId;
	}

	private List<SocialMediaItemsModel> socialLinks;
	
	
	public List<SocialMediaItemsModel> getSocialMediaLinks() {
				return this.socialLinks;
			}
	
	
	@PostConstruct
	public final void init() {
			
		socialLinks = getSocialNavLinks();
		}
	
	private List<SocialMediaItemsModel> getSocialNavLinks() {
	  	List<SocialMediaItemsModel> socialLinksList = new ArrayList<>();
	 	if (null != socialMediaLinks ) {
			Iterator<Resource> resourceChildren = socialMediaLinks.listChildren();
			while (resourceChildren.hasNext())
			{
				socialLinksList.add(resourceChildren.next().adaptTo(SocialMediaItemsModel.class));
			}
			
		}

		return socialLinksList;
	}

}
