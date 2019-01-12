package com.sling.tv.core.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class browserBannerImageModelTest {

	/*
	 * @ author Arati Jena
	 *
	 */

	@Rule
	public final AemContext context = new AemContext();
	
	private BrowserBannerImageModel image;
	private static final String COMPONENT_PATH = "/content/slingtv/en/image/jcr:content/par/browser-banner-image";

	@Before
	public void setup() {
		
		context.load().json("/browser-banner-image.json", COMPONENT_PATH);
		context.addModelsForClasses(BrowserBannerImageModel.class);
		context.addModelsForClasses(ImageCarouselItemModel.class);
	}

	@Test
	public void Test_whenImageIdExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		image = resource.adaptTo(BrowserBannerImageModel.class);
		String cmpId = image.getBrowserBannerImageId();
		String width = image.getWidth();
		Assert.assertEquals("Component ID",cmpId);
		Assert.assertEquals("40",width);
	}

	@Test
	public void Test_equalsImageCarouselItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		image = resource.adaptTo(BrowserBannerImageModel.class);
		List<ImageCarouselItemModel> imageBannerItems = image.getBrowserBannerImage();
		Assert.assertEquals("gvk",imageBannerItems.get(0).getAlt());
		Assert.assertEquals("/content/dam/sling-tv/devices/device-logos/Android TV White.png",imageBannerItems.get(0).getImagePath());
		Assert.assertEquals("https://www.google.com",imageBannerItems.get(0).getImageUrl());
	}

	@Test
	public void Test_NotNullImageCarouselItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		image = resource.adaptTo(BrowserBannerImageModel.class);
		List<ImageCarouselItemModel> browserBannerImageItems = image.getBrowserBannerImage();
		Assert.assertNotNull(browserBannerImageItems.get(0).getAlt());
		Assert.assertNotNull(browserBannerImageItems.get(0).getImagePath());
		Assert.assertNotNull(browserBannerImageItems.get(0).getImageUrl());
	}

	@Test
	public void Test_NullImageCarouselItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		image = resource.adaptTo(BrowserBannerImageModel.class);
		List<ImageCarouselItemModel> browserBannerImageItems = image.getBrowserBannerImage();
		Assert.assertNull(browserBannerImageItems.get(1).getImageUrl());
		Assert.assertNull(browserBannerImageItems.get(1).getImagePath());
		Assert.assertNull(browserBannerImageItems.get(1).getAlt());
	}


}
