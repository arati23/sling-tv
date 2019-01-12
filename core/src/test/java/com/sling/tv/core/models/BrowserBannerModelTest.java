package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.wcm.testing.mock.aem.junit.AemContext;

public class BrowserBannerModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	protected BrowserBannerModel browserBanner;
	private static final String BROWSER_BANNER_COMPONENT_PATH = "/content/slingtv/en/services/jcr:content/par/browser_banner";
	private static final String BROWSER_BANNER_COMPONENT_PATH_1 = "/content/slingtv/en/services/jcr:content/par/browser_banner1";
	

	@Before
	public void setup() {

		context.load().json("/browser-banner.json", BROWSER_BANNER_COMPONENT_PATH) ;
		context.load().json("/browser-banner1.json", BROWSER_BANNER_COMPONENT_PATH_1);
		context.addModelsForClasses(BrowserBannerModel.class);
	}
	
		
	@Test
	public void Test_whenPropExists() {

		Resource resource = context.resourceResolver().getResource(BROWSER_BANNER_COMPONENT_PATH);
		browserBanner = resource.adaptTo(BrowserBannerModel.class);
		String bannerText = browserBanner.getBannerText();
		String imagePath = browserBanner.getImagePath();
		String imageAlt = browserBanner.getAlt();
		String compID = browserBanner.getBrowserSpecificComponentId();
		Assert.assertEquals("This is the Message",bannerText);
		Assert.assertEquals("/content/dam/sling-tv/devices/device-ui-screenshot/dvr-ui/abc.jpg",imagePath);
		Assert.assertEquals("Sample Alt Text",imageAlt);
		Assert.assertEquals("200",compID);
		
	}
	
	@Test
	public void Test_NotNullComp() {

		Resource resource = context.resourceResolver().getResource(BROWSER_BANNER_COMPONENT_PATH_1);
		browserBanner = resource.adaptTo(BrowserBannerModel.class);
		Assert.assertNotNull(browserBanner);
		
		

	}
}
