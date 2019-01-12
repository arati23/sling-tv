package com.sling.tv.core.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class DismissibleBannerModelTest {

	/**
	 * @author Krishna Gunturu
	 *
	 */

	@Rule
	public final AemContext context = new AemContext();
	
	private DismissibleBannerModel dismissibleBanner;
	private static final String COMPONENT_PATH = "/content/slingtv/en/services/jcr:content/par/tcg_grid";

	@Before
	public void setup() {
		
		context.load().json("/dismissibleBanner.json", COMPONENT_PATH);
		context.addModelsForClasses(DismissibleBannerModel.class);
	}

	/*
	 * This method will check dismissible Banner property items.
	 */
	
	@Test
	public void Test_whenBannerItemExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		dismissibleBanner = resource.adaptTo(DismissibleBannerModel.class);
		String compID = dismissibleBanner.getDismissibleBannerId();
		String banType = dismissibleBanner.getBannerType();
		String sticky = dismissibleBanner.getSticky();
		String imagePath = dismissibleBanner.getImageURL();
		String imagePathP = dismissibleBanner.getImageURLP();
		String damLink = dismissibleBanner.getDamVideoLink();
		String damLinkP = dismissibleBanner.getDamVideoLinkP();
		String dismiss = dismissibleBanner.getDismissible();
		String rteDesktop = dismissibleBanner.getBottomRte();
		String rteMobile = dismissibleBanner.getBottomRteMobile();
		Assert.assertEquals("gvk",compID);
		Assert.assertEquals("video",banType);
		Assert.assertEquals("true",sticky);
		Assert.assertEquals("/content/dam/sling-tv/marquees/international/malayalam/Sling_Malayalam_AsiaNet_Marquee_1500x400_030718.jpg",imagePath);
		Assert.assertEquals("/content/dam/sling-tv/marquees/international/malayalam/Sling_Malayalam_AsiaNet_Marquee_1500x400_030718.jpg",imagePathP);
		Assert.assertEquals("/content/dam/sling-tv/marquees/domestic/campaign-assets/desktop_love_video_new.mp4",damLink);
		Assert.assertEquals("/content/dam/sling-tv/marquees/domestic/campaign-assets/desktop_love_video_new.mp4",damLinkP);
		Assert.assertEquals("true",dismiss);
		Assert.assertEquals("true",rteDesktop);
		Assert.assertEquals("true",rteMobile);

	}

	@Test
	public void nullTest() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		dismissibleBanner = resource.adaptTo(DismissibleBannerModel.class);
		Assert.assertNotNull(dismissibleBanner);

	}
	

}
