package com.sling.tv.core.models;

import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class DismissibleBannerModel{

    /** Default log. */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private String dismissibleBannerId;

    @Inject
    private String bannerType;

    @Inject
    private String sticky;

    @Inject
    private String imageURL;

    @Inject
    private String fallbackImageUrl;

    @Inject
    private String damVideoLink;

    @Inject
    private String dismissible;

    @Inject
    private String bottomRte;

    @Inject
    private String imageURLP;

    @Inject
    private String fallbackImageUrlP;

    @Inject
    private String damVideoLinkP;

    @Inject
    private String bottomRteMobile;
    
    @Inject
    private String imageAltText;
    
    public String getImageAltText() {
		return imageAltText;
	}

    public String getDismissibleBannerId() {
        return dismissibleBannerId;
    }

    public String getBannerType() {
        return bannerType;
    }

    public String getSticky() { return sticky; }

    public String getImageURL() {
        return imageURL;
    }

    public String getFallbackImageUrl() {
        return fallbackImageUrl;
    }

    public String getDamVideoLink() {
        return damVideoLink;
    }

    public String getDismissible() {
        return dismissible;
    }

    public String getBottomRte() {
        return bottomRte;
    }

    public String getImageURLP() {
        return imageURLP;
    }

    public String getFallbackImageUrlP() {
        return fallbackImageUrlP;
    }

    public String getDamVideoLinkP() {
        return damVideoLinkP;
    }

    public String getBottomRteMobile() {
        return bottomRteMobile;
    }
}