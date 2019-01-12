package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = Resource.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class BrowserBannerImageModel {

	/*
	 *  @author Arati Jena
	 *
	 */

	@Inject
	private String browserBannerImageId;

	@Inject
	private String width;

	@Inject
	private Resource browserBannerImage;

	private List<ImageCarouselItemModel> browserBannerImageItems;

	@PostConstruct
	public final void init() {
		// Populate multifield
		browserBannerImageItems = getImageItems();
	}

	private List<ImageCarouselItemModel> getImageItems() {
		List<ImageCarouselItemModel> imageItems = new ArrayList<>();
		if (null != browserBannerImage) {
			Iterator<Resource> resourceChildren = browserBannerImage.listChildren();
			while (resourceChildren.hasNext())
			{
				imageItems.add(resourceChildren.next().adaptTo(ImageCarouselItemModel.class));
			}

		}

		return imageItems;
	}

	public List<ImageCarouselItemModel> getBrowserBannerImage() {
		return this.browserBannerImageItems;
	}

	public String getBrowserBannerImageId() {
		return browserBannerImageId;
	}

	public String getWidth() {
		return width;
	}

}
