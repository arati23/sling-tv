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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ParallaxModel {
	
	private static Logger LOG = LoggerFactory.getLogger(ParallaxModel.class);
	
	
	
	@Inject
	private String parallaxId;
	
	@Inject
	private String desktopHeight;
	
	@Inject
	private String mobileHeight;
	
	@Inject
	private Resource parallaxItems;
	
	@Inject
	private Resource parallaxItemsMobile;
	
	private List<ParallaxItemModel> parallaxListItems;
	
	private List<ParallaxItemModelMobile> parallaxListItemsMobile;
	
	@PostConstruct
	public final void init() {
		// Populate multifield
		parallaxListItems = getParallaxItems();
		parallaxListItemsMobile = getParallaxMobileItems();
	}
	
	public List<ParallaxItemModel> getParallaxItems() {
		List<ParallaxItemModel> parallaxItemsList = new ArrayList<>();
		if (null != parallaxItems ) {
			Iterator<Resource> resourceChildren = parallaxItems.listChildren();
			while (resourceChildren.hasNext())
			{
				parallaxItemsList.add(resourceChildren.next().adaptTo(ParallaxItemModel.class));
			}
			
		}
		
		return parallaxItemsList;
	}
	
	public List<ParallaxItemModel> getParallaxListItems() {
		return this.parallaxListItems;
	}
	
	public List<ParallaxItemModelMobile> getParallaxMobileItems() {
		List<ParallaxItemModelMobile> parallaxMobileListItems = new ArrayList<>();
		if (null != parallaxItemsMobile ) {
			Iterator<Resource> resourceChildren = parallaxItemsMobile.listChildren();
			while (resourceChildren.hasNext())
			{
				parallaxMobileListItems.add(resourceChildren.next().adaptTo(ParallaxItemModelMobile.class));
			}
			
		}
		
		return parallaxMobileListItems;
	}
	
	public List<ParallaxItemModelMobile> getParallaxListItemsMobile() {
		return this.parallaxListItemsMobile;
	}

	public String getDesktopHeight() {
		
		return desktopHeight;
	}

	public String getMobileHeight() {
		return mobileHeight;
	}

	public String getParallaxId() {
		return parallaxId;
	}

}
