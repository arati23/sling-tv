package com.sling.tv.core.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class ContainerModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private ContainerModel container;
	private static final String COMPONENT_PATH = "/content/slingtv/en/jcr:content/par/container";

	@Before
	public void setup() {
		
		context.load().json("/container.json", COMPONENT_PATH);
		context.addModelsForClasses(ContainerModel.class);
	}
	
	
	
	@Test
	public void Test_whenContainerPropExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		container = resource.adaptTo(ContainerModel.class);
		String cntId = container.getContainerCmpId();
		String cntTheme = container.getContainerTheme();
		String fullWidth = container.getFullWidthOptions();
		String brdr = container.getBorder();
		String tag = container.getAnchorTag();
		String mblContent = container.getMobileContent();
		String maxWidth = container.getMaxWidthOptions();
		String paddingRgt = container.getPaddingRight();
		String paddingLft = container.getPaddingLeft();
		String paddingRgtMbl = container.getPaddingRightMobile();
		String paddingLftMbl = container.getPaddingLeftMobile();
		Assert.assertEquals("container-id",cntId);
		Assert.assertEquals("lg-theme",cntTheme);
		Assert.assertEquals("custom",fullWidth);
		Assert.assertEquals("true",brdr);
		Assert.assertEquals("container-tag",tag);
		Assert.assertEquals("true",mblContent);
		Assert.assertEquals("container--max-width",maxWidth);
		Assert.assertEquals("10",paddingRgt);
		Assert.assertEquals("10",paddingLft);
		Assert.assertEquals("10",paddingRgtMbl);
		Assert.assertEquals("10",paddingLftMbl);
	}

	@Test
	public void nullTest() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		container = resource.adaptTo(ContainerModel.class);
		Assert.assertNotNull(container);

	}
	

}
