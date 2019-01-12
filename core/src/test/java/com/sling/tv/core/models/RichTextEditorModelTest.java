/**
 * 
 */
package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.wcm.testing.mock.aem.junit.AemContext;

/**
 * @author Dileep Muraleedharan
 *
 */
public class RichTextEditorModelTest {
	
	@Rule
	public final AemContext context = new AemContext();
	
	protected RichTextEditorModel rteModel;
	private static final String RTE_COMPONENT_PATH = "/content/sling-tv/en/domestic/test-page/jcr:content/par/rich_text";
	

	@Before
	public void setup() {

		context.load().json("/richtext-editor.json", RTE_COMPONENT_PATH);
		context.addModelsForClasses(RichTextEditorModel.class);
	}
	
	/*
	 * The below method will test each property values and match with richtext-editor.json
	 */
	
	@Test
	public void Test_whenPropExists() {

		Resource resource = context.resourceResolver().getResource(RTE_COMPONENT_PATH);
		rteModel = resource.adaptTo(RichTextEditorModel.class);
		String rteText=rteModel.getText();
		String sectionID=rteModel.getSectionId();
		Assert.assertEquals("<p>This is the <span class=\"water\">sample </span>RTE Text</p>",rteText);
		Assert.assertEquals("2000",sectionID);
		
		
	}
	
	
	@Test
	public void Test_NotNullComp() {
		Resource resource = context.resourceResolver().getResource(RTE_COMPONENT_PATH);
		rteModel = resource.adaptTo(RichTextEditorModel.class);
		Assert.assertNotNull(rteModel);
	}


}
