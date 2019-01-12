package com.sling.tv.core.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class VanillaIceModelTest {

    @Rule
    public final AemContext context = new AemContext();

    private VanillaIceModel offerDeals;
    private List<VanillaIceItemModel> offerDealsItems;
    private static final String COMPONENT_PATH = "/content/slingtv/en/jcr:content/par/vanilla-ice";

    @Before
    public void setup() {

        context.load().json("/offers-deals.json", COMPONENT_PATH);
        context.addModelsForClasses(VanillaIceModel.class);
        context.addModelsForClasses(VanillaIceItemModel.class);
    }

    @Test
    public void Test_equalsComponentId() {

        Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
        offerDeals = resource.adaptTo(VanillaIceModel.class);
        String cmpId = offerDeals.getOffersDealsId();
        Assert.assertEquals("component Id", cmpId);
    }


    @Test
    public void Test_equalsOffersItems() {

        Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
        offerDeals = resource.adaptTo(VanillaIceModel.class);
        List<VanillaIceItemModel> offerItems = offerDeals.getOfferDeals();
        Assert.assertEquals("FREE", offerItems.get(0).getOfferFlag());
        Assert.assertEquals("/content/dam/sling-tv/devices/device-hardware/free-ribbon.png", offerItems.get(0).getOfferFlagImage());
        Assert.assertEquals("flagTest", offerItems.get(0).getOfferFlagImageAlt());
    }

    @Test
    public void Test_NotNullOfferItems() {

        Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
        offerDeals = resource.adaptTo(VanillaIceModel.class);
        List<VanillaIceItemModel> offerItems = offerDeals.getOfferDeals();
        Assert.assertNotNull(offerItems.get(0).getOfferFlag());
        Assert.assertNotNull(offerItems.get(0).getOfferFlagImage());
        Assert.assertNotNull(offerItems.get(0).getOfferFlagImageAlt());
    }

    @Test
    public void Test_NullOfferItems() {

        Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
        offerDeals = resource.adaptTo(VanillaIceModel.class);
        List<VanillaIceItemModel> offerItems = offerDeals.getOfferDeals();
        Assert.assertNull(offerItems.get(1).getOfferFlag());
        Assert.assertNull(offerItems.get(1).getOfferFlagImageAlt());
        Assert.assertNull(offerItems.get(1).getOfferFlagImage());

    }

}
