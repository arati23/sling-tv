package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.wcm.testing.mock.aem.junit.AemContext;

public class ColumnContainerModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	protected ColumnContainerModel columnContainerModel;
	private static final String COLUMN_CONTAINER_COMPONENT_PATH = "/content/slingtv/en/services/jcr:content/par/column_container";
	private static final String COLUMN_CONTAINER_COMPONENT_PATH_1 = "/content/slingtv/en/services/jcr:content/par/column_container1";
	

	@Before
	public void setup() {

		context.load().json("/column_container.json", COLUMN_CONTAINER_COMPONENT_PATH);
		context.load().json("/column_container1.json", COLUMN_CONTAINER_COMPONENT_PATH_1);
		context.addModelsForClasses(ColumnContainerModel.class);
	}
	
	/*
	 * The below method will test each property values and match with column_container.json
	 */
	
	@Test
	public void Test_whenPropExists() {

		Resource resource = context.resourceResolver().getResource(COLUMN_CONTAINER_COMPONENT_PATH);
		columnContainerModel = resource.adaptTo(ColumnContainerModel.class);
		String setUp = columnContainerModel.getSetup();
		String colOverride = columnContainerModel.getColOverride();
		String col1Val = columnContainerModel.getColumn1Val();
		String col2Val = columnContainerModel.getColumn2Val();
		String col3Val = columnContainerModel.getColumn3Val();
		String col4Val = columnContainerModel.getColumn4Val();
		String col5Val = columnContainerModel.getColumn5Val();
		String col6Val = columnContainerModel.getColumn6Val();
		String alignment = columnContainerModel.getAlignment();
		String colPadding = columnContainerModel.getColPadding();
		String fixedContainerCheck = columnContainerModel.getFixedContainerCheck();
		String carouselMode = columnContainerModel.getCarouselMode();
		String containerID=columnContainerModel.getFlexColumnContainerId();
		Assert.assertEquals("two-50-50",setUp);
		Assert.assertEquals("true",colOverride);
		Assert.assertEquals("10",col1Val);
		Assert.assertEquals("20",col2Val);
		Assert.assertEquals("10",col3Val);
		Assert.assertEquals("20",col4Val);
		Assert.assertEquals("10",col5Val);
		Assert.assertEquals("10",col6Val);
		Assert.assertEquals("flex-start",alignment);
		Assert.assertEquals("true",colPadding);
		Assert.assertEquals("true",fixedContainerCheck);
		Assert.assertEquals("true",carouselMode);
		Assert.assertEquals("12345",containerID);
		

	}
	
	@Test
	public void Test_whenPropNotExists() {

		Resource resource = context.resourceResolver().getResource(COLUMN_CONTAINER_COMPONENT_PATH_1);
		columnContainerModel = resource.adaptTo(ColumnContainerModel.class);
		String setUp = columnContainerModel.getSetup();
		String colOverride = columnContainerModel.getColOverride();
		String col1Val = columnContainerModel.getColumn1Val();
		String col2Val = columnContainerModel.getColumn2Val();
		String col3Val = columnContainerModel.getColumn3Val();
		String col4Val = columnContainerModel.getColumn4Val();
		String col5Val = columnContainerModel.getColumn5Val();
		String col6Val = columnContainerModel.getColumn6Val();
		String alignment = columnContainerModel.getAlignment();
		String colPadding = columnContainerModel.getColPadding();
		String fixedContainerCheck = columnContainerModel.getFixedContainerCheck();
		String carouselMode = columnContainerModel.getCarouselMode();
		String containerID=columnContainerModel.getFlexColumnContainerId();
		
		Assert.assertNotNull(setUp);
		Assert.assertNull(colOverride);
		Assert.assertNull(col1Val);
		Assert.assertNull(col2Val);
		Assert.assertNull(col3Val);
		Assert.assertNull(col4Val);
		Assert.assertNull(col5Val);
		Assert.assertNull(col6Val);
		Assert.assertNull(alignment);
		Assert.assertNull(colPadding);
		Assert.assertNull(fixedContainerCheck);
		Assert.assertNull(carouselMode);
		Assert.assertNull(containerID);
		

	}
	
	@Test
	public void Test_NotNullComp() {
		Resource resource = context.resourceResolver().getResource(COLUMN_CONTAINER_COMPONENT_PATH);
		columnContainerModel = resource.adaptTo(ColumnContainerModel.class);
		Assert.assertNotNull(columnContainerModel);
	}
}
