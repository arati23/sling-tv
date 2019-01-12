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
public class ImageCarouselModel {

	/*
	 *  @author Krishna Gunturu
	 *
	 */

	@Inject
	private String imageCarouselId;

	@Inject
	private String width;

	@Inject
	private Resource imageCarousel;

	private List<ImageCarouselItemModel> imageCarouselItems;

	@PostConstruct
	public final void init() {
		// Populate multifield
		imageCarouselItems = getImageItems();
	}

	private List<ImageCarouselItemModel> getImageItems() {
		List<ImageCarouselItemModel> imageItems = new ArrayList<>();
		if (null != imageCarousel) {
			Iterator<Resource> resourceChildren = imageCarousel.listChildren();
			while (resourceChildren.hasNext())
			{
				imageItems.add(resourceChildren.next().adaptTo(ImageCarouselItemModel.class));
			}

		}

		return imageItems;
	}

	public List<ImageCarouselItemModel> getImageCarousel() {
		return this.imageCarouselItems;
	}

	public String getImageCarouselId() {
		return imageCarouselId;
	}

	public String getWidth() {
		return width;
	}

}
