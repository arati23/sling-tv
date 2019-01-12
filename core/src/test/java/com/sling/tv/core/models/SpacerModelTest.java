package com.sling.tv.core.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class SpacerModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private SpacerModel spacer;
	private static final String COMPONENT_PATH = "/content/sling-tv/en/domestic/jcr:content/par/container_component__478259074/par/dismissible_banner_c/content/spacer";

	@Before
	public void setup() {
		
		context.load().json("/spacer.json", COMPONENT_PATH);
		context.addModelsForClasses(SpacerModel.class);
	}
	
	@Test
	public void Test_whenIDexists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		spacer = resource.adaptTo(SpacerModel.class);
		String theID = spacer.getSpacerId();
		Assert.assertEquals("ABCD123",theID);
	}

	@Test
	public void Test_whenlineStyleexists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		SpacerModel spacer = resource.adaptTo(SpacerModel.class);
		String lineStyle = spacer.getLineStyle();
		
		Assert.assertEquals("small",lineStyle);
	}


	@Test
	public void Test_whenHeightInPercentageNotexists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		spacer = resource.adaptTo(SpacerModel.class);
		String heightInPercentage = spacer.getHeightInPercentage();
		Assert.assertNull(heightInPercentage);
	}


	@Test
	public void Test_whenHeightInPixelNotexists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		spacer = resource.adaptTo(SpacerModel.class);
		String heightInPixel = spacer.getHeightInPixel();
		Assert.assertNull(heightInPixel);
	}


	@Test
	public void nullTest() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		SpacerModel spacer = resource.adaptTo(SpacerModel.class);
		Assert.assertNotNull(spacer);

	}


}
