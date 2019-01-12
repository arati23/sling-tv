package com.sling.tv.core.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class GridInteractionModelTest {

	/**
	 * @author Krishna Gunturu
	 *
	 */

	@Rule
	public final AemContext context = new AemContext();
	
	private GridInteractionModel gridInteraction;
	private static final String COMPONENT_PATH = "/content/slingtv/en/jcr:content/par/grid-interaction";

	@Before
	public void setup() {
		
		context.load().json("/grid-interaction.json", COMPONENT_PATH);
		context.addModelsForClasses(GridInteractionModel.class);
	}

	/*
	 * This method will check Grid Interaction property items.
	 */
	
	@Test
	public void Test_whenPropExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		gridInteraction = resource.adaptTo(GridInteractionModel.class);
		String compID = gridInteraction.getGridInteractionId();
		Assert.assertEquals("grid",compID);
	}

	@Test
	public void nullTest() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		gridInteraction = resource.adaptTo(GridInteractionModel.class);
		Assert.assertNotNull(gridInteraction);
	}
	

}
