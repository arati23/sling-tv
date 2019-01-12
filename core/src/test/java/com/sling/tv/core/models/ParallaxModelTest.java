package com.sling.tv.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.wcm.testing.mock.aem.junit.AemContext;

public class ParallaxModelTest {
	
	
	@Rule
	public final AemContext context = new AemContext();
	
	protected ParallaxModel parallax;
	private static final String PARALLAX_COMPONENT_PATH = "/content/slingtv/en/services/jcr:content/par/parallax";
	
	
	@Before
	public void setup() {

		context.load().json("/parallax.json", PARALLAX_COMPONENT_PATH) ;
		context.addModelsForClasses(ParallaxModel.class);
		context.addModelsForClasses(ParallaxItemModel.class);
		context.addModelsForClasses(ParallaxItemModelMobile.class);
	}
	
	@Test
	public void Test_whenPropExists() {

		Resource resource = context.resourceResolver().getResource(PARALLAX_COMPONENT_PATH);
		parallax = resource.adaptTo(ParallaxModel.class);
		String parallaxId  = parallax.getParallaxId();
		String desktopHeight  = parallax.getDesktopHeight();
		String mobileHeight  = parallax.getMobileHeight();
		List<ParallaxItemModel> lstParallaxItems  = parallax.getParallaxListItems();
		List<ParallaxItemModelMobile> lstParallaxMobileItems  = parallax.getParallaxListItemsMobile();
		Assert.assertEquals("p3",parallaxId);
		Assert.assertEquals("600",desktopHeight);
		Assert.assertEquals("300",mobileHeight);
		
		Assert.assertEquals("22.5",lstParallaxItems.get(0).getImageWidth());
		Assert.assertEquals("/content/dam/sling-tv/misc/new-home-page-videos/parllax/TV.png",lstParallaxItems.get(0).getImagePath());
		Assert.assertEquals("-20",lstParallaxItems.get(0).getStartX());
		Assert.assertEquals("50",lstParallaxItems.get(0).getStartY());
		Assert.assertEquals("10",lstParallaxItems.get(0).getEndX());
		Assert.assertEquals("50",lstParallaxItems.get(0).getEndY());
		
		Assert.assertEquals("22.5",lstParallaxMobileItems.get(0).getImageWidthMobile());
		Assert.assertEquals("/content/dam/sling-tv/misc/new-home-page-videos/parllax/TV.png",lstParallaxMobileItems.get(0).getImagePathMobile());
		Assert.assertEquals("-20",lstParallaxMobileItems.get(0).getStartXMobile());
		Assert.assertEquals("50",lstParallaxMobileItems.get(0).getStartYMobile());
		Assert.assertEquals("10",lstParallaxMobileItems.get(0).getEndXMobile());
		Assert.assertEquals("50",lstParallaxMobileItems.get(0).getEndYMobile());

	}
	
	@Test
	public void Test_NotNullComp() {

		Resource resource = context.resourceResolver().getResource(PARALLAX_COMPONENT_PATH);
		parallax = resource.adaptTo(ParallaxModel.class);
		Assert.assertNotNull(parallax);
	}
}
