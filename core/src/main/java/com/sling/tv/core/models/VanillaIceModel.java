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

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class VanillaIceModel {

    @Self
    Resource resource;

    @Inject
    private String offersDealsId;

    @Inject
    private Resource offerDeals;


    public String getNodeName() {

        return resource.getName();
    }

    private List<VanillaIceItemModel> offerDealsList;

    @PostConstruct
    public final void init() {
        // Populate multifield
        offerDealsList = getOffersDealsItems();
    }

    private List<VanillaIceItemModel> getOffersDealsItems() {
        List<VanillaIceItemModel> offerItems = new ArrayList<>();
        if (null != offerDeals ) {
            Iterator<Resource> resourceChildren = offerDeals.listChildren();
            while (resourceChildren.hasNext())
            {
                offerItems.add(resourceChildren.next().adaptTo(VanillaIceItemModel.class));
            }

        }

        return offerItems;
    }

    public List<VanillaIceItemModel> getOfferDeals() {
        return this.offerDealsList;
    }

    public String getOffersDealsId() {
        return offersDealsId;
    }
}
