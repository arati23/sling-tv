package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.wcm.testing.mock.aem.junit.AemContext;

public class TabbedCatCarouselCompModelTest {
	


	@Rule
	public final AemContext context = new AemContext();
	
	private TabbedCatCarouselCompModel tabbedCatCarousel;
	private static final String COMPONENT_PATH = "/content/slingtv/en/jcr:content/par/tabbed-category-carousel";

	@Before
	public void setup() {
		
		context.load().json("/tabbed-category.json", COMPONENT_PATH);
		context.addModelsForClasses(TabbedCatCarouselCompModel.class);
	}
	
	
	
	@Test
	public void Test_whenContainerPropExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		tabbedCatCarousel = resource.adaptTo(TabbedCatCarouselCompModel.class);
		String catResourcePath = tabbedCatCarousel.getCategoryPath();
		String compID = tabbedCatCarousel.getTabbedCategoryCarouselId();
		Assert.assertEquals("12345",compID);
		Assert.assertEquals("/content/sling-tv/channel-grids/new_extras",catResourcePath);
		
			}

	@Test
	public void nullTest() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		tabbedCatCarousel = resource.adaptTo(TabbedCatCarouselCompModel.class);
		Assert.assertNotNull(tabbedCatCarousel);

	}
	



}
