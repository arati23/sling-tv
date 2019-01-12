package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class VanillaIceItemModel {

    private static Logger LOG = LoggerFactory.getLogger(VanillaIceItemModel.class);

    @Inject
    private String offerFlagImage;

    @Inject
    private String offerFlagImageAlt;

    @Inject
    private String offerFlag;

    @Inject
    private String uniqueId;

    public String getOfferFlagImage() {
        return offerFlagImage;
    }

    public String getOfferFlagImageAlt() {
        return offerFlagImageAlt;
    }

    public String getOfferFlag() {
        return offerFlag;
    }

    public String getUniqueId() {
        return uniqueId;
    }
}
