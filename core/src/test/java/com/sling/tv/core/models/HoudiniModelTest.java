package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.wcm.testing.mock.aem.junit.AemContext;

/**
 * @author Nithya Nair
 *
 */
public class HoudiniModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private HoudiniModel houdiniModel;
	private static final String HOUDINI_COMPONENT_PATH = "/content/slingtv/en/services/jcr:content/par/houdini";
	private static final String HOUDINI_COMPONENT_PATH_1 = "/content/slingtv/en/services/jcr:content/par/houdini1";
	private static final String HOUDINI_COMPONENT_PATH_2 = "/content/slingtv/en/services/jcr:content/par/houdini2";

	@Before
	public void setup() {

		context.load().json("/houdini.json", HOUDINI_COMPONENT_PATH);
		context.load().json("/houdini1.json", HOUDINI_COMPONENT_PATH_1);
		context.load().json("/houdini2.json", HOUDINI_COMPONENT_PATH_2);
		context.addModelsForClasses(HoudiniModel.class);
	}
	
	/*
	 * The below method will test each property values in the houdini component if property has some values Refer Mock houdini.json
	 */
	
	@Test
	public void Test_whenPropExists() {

		Resource resource = context.resourceResolver().getResource(HOUDINI_COMPONENT_PATH);
		houdiniModel = resource.adaptTo(HoudiniModel.class);
		String houdidniCompID = houdiniModel.getHoudiniCompId();
		String houdidniShowText = houdiniModel.getShowText();
		String houdidniHideText = houdiniModel.getHideText();
		Assert.assertEquals("12345",houdidniCompID);
		Assert.assertEquals("Show More",houdidniShowText);
		Assert.assertEquals("Show Less",houdidniHideText);

	}
	
	/*
	 * The below method will test each property values in the houdini component if the property values are empty, but property name exist Refer Mock houdini1.json
	 */
	
	@Test
	public void Test_whenPropValueNotExists() {

		Resource resource = context.resourceResolver().getResource(HOUDINI_COMPONENT_PATH_1);
		houdiniModel = resource.adaptTo(HoudiniModel.class);
		String houdidniShowText = houdiniModel.getShowText();
		String houdidniHideText = houdiniModel.getHideText();
		Assert.assertEquals("", houdidniShowText);
		Assert.assertEquals("",houdidniHideText);
	}

	/*
	 * The below method will test null check if the few properties are not existing :: Refer Mock houdini2.json
	 */
	
	@Test
	public void Test_whenPropNotExists() {

		Resource resource = context.resourceResolver().getResource(HOUDINI_COMPONENT_PATH_2);
		houdiniModel = resource.adaptTo(HoudiniModel.class);
		String houdidniShowText = houdiniModel.getShowText();
		String houdidniHideText = houdiniModel.getHideText();
		Assert.assertNull(houdidniShowText);
		Assert.assertNull(houdidniHideText);
	}
	
	
	
	
	
	
	
	

}
