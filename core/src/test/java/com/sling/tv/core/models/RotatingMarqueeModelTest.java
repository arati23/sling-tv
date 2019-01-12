package com.sling.tv.core.models;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import io.wcm.testing.mock.aem.junit.AemContext;
import org.junit.Assert;

public class RotatingMarqueeModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private RotatingMarqueeModel marqueeModel;
	private List<RotatingMarqueeItemsModel> marqueeModelItems;
	private static final String MARQUEE_COMPONENT_PATH = "/content/slingtv/en/jcr:content/par/rotating-marquee";

	@Before
	public void setup() {
		
		context.load().json("/rotating-marquee.json", MARQUEE_COMPONENT_PATH);
		context.addModelsForClasses(RotatingMarqueeModel.class);
		context.addModelsForClasses(RotatingMarqueeItemsModel.class);
	}
	
	
	
	@Test
	public void Test_equalsNavItems() {

		Resource resource = context.resourceResolver().getResource(MARQUEE_COMPONENT_PATH);
		marqueeModel = resource.adaptTo(RotatingMarqueeModel.class);
		List<RotatingMarqueeItemsModel> marqueeItems = marqueeModel.getCarouselImages();
		Assert.assertEquals("/content/dam/sling-tv/marquees/domestic/device-lps/slingers_xbox_desktop.jpg",marqueeItems.get(0).getCarousalImage());
		Assert.assertEquals("image",marqueeItems.get(0).getMediaType());
		Assert.assertEquals("/content/we-retail/ca",marqueeItems.get(0).getImageURL());
		Assert.assertEquals("Image Alt Text",marqueeItems.get(0).getAltName());
		Assert.assertEquals("/content/dam/sling-tv/marquees/domestic/campaign-assets/desktop_love_video_new.mp4",marqueeItems.get(2).getVideoPath());
		Assert.assertEquals("video",marqueeItems.get(2).getMediaType());
		Assert.assertEquals("/content/sling-tv/es/latinoes",marqueeItems.get(2).getVideoURL());
		Assert.assertEquals("/content/dam/sling-tv/device-lockups/international/device_lockup_Mixed_V2.jpg",marqueeItems.get(2).getFallBackImageUrl());
		
		
	}
	
	@Test
	public void Test_NotNullComp() {

		Resource resource = context.resourceResolver().getResource(MARQUEE_COMPONENT_PATH);
		marqueeModel = resource.adaptTo(RotatingMarqueeModel.class);
		Assert.assertNotNull(marqueeModel);
	}
	
		
	
	

}
