package com.sling.tv.core.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class ImageCarouselModelTest {

	/*
	 * @ author Krishna Gunturu
	 *
	 */

	@Rule
	public final AemContext context = new AemContext();
	
	private ImageCarouselModel image;
	private List<ImageCarouselItemModel> imageCarouselItems;
	private static final String COMPONENT_PATH = "/content/slingtv/en/image/jcr:content/par/image-carousel";

	@Before
	public void setup() {
		
		context.load().json("/image-carousel.json", COMPONENT_PATH);
		context.addModelsForClasses(ImageCarouselModel.class);
		context.addModelsForClasses(ImageCarouselItemModel.class);
	}
	
	
	
	@Test
	public void Test_whenPropExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		image = resource.adaptTo(ImageCarouselModel.class);
		String cmpId = image.getImageCarouselId();
		String width = image.getWidth();
		Assert.assertEquals("Component ID",cmpId);
		Assert.assertEquals("40",width);
	}

	@Test
	public void Test_equalsImageCarouselItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		image = resource.adaptTo(ImageCarouselModel.class);
		List<ImageCarouselItemModel> imageCarouselItems = image.getImageCarousel();
		Assert.assertEquals("gvk",imageCarouselItems.get(0).getAlt());
		Assert.assertEquals("/content/dam/sling-tv/devices/device-logos/Android TV White.png",imageCarouselItems.get(0).getImagePath());
		Assert.assertEquals("https://www.google.com",imageCarouselItems.get(0).getImageUrl());
		Assert.assertEquals("green",imageCarouselItems.get(0).getCarouselColorBar());
	}

	@Test
	public void Test_NotNullImageCarouselItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		image = resource.adaptTo(ImageCarouselModel.class);
		List<ImageCarouselItemModel> imageCarouselItems = image.getImageCarousel();
		Assert.assertNotNull(imageCarouselItems.get(0).getAlt());
		Assert.assertNotNull(imageCarouselItems.get(0).getCarouselColorBar());
		Assert.assertNotNull(imageCarouselItems.get(0).getImagePath());
		Assert.assertNotNull(imageCarouselItems.get(0).getImageUrl());
	}

	@Test
	public void Test_NullImageCarouselItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		image = resource.adaptTo(ImageCarouselModel.class);
		List<ImageCarouselItemModel> imageCarouselItems = image.getImageCarousel();
		Assert.assertNull(imageCarouselItems.get(1).getImageUrl());
		Assert.assertNull(imageCarouselItems.get(1).getImagePath());
		Assert.assertNull(imageCarouselItems.get(1).getCarouselColorBar());
		Assert.assertNull(imageCarouselItems.get(1).getAlt());
	}

}
