package com.sling.tv.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;



@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class TabbedCatCarouselCompModel {

    @Inject
    private String categoryPath;
    
    @Inject
    private String tabbedCategoryCarouselId;

    public String getTabbedCategoryCarouselId() {
		return tabbedCategoryCarouselId;
	}

	public String getCategoryPath() {
        return categoryPath;
    }

}
