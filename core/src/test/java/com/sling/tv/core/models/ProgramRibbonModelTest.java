/**
 * 
 */
package com.sling.tv.core.models;

import java.util.List;

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
public class ProgramRibbonModelTest {
	
	@Rule
	public final AemContext context = new AemContext();
	
	private ProgramRibbonModel programRibbon;
	private static final String PROGRAM_RIBBON_COMPONENT_PATH = "/content/sling-tv/en/test/new-pgm-ribbon/jcr:content/par/program_ribbon";

	
	@Before
	public void setup() {

		context.load().json("/program-ribbon.json", PROGRAM_RIBBON_COMPONENT_PATH);
		
		context.addModelsForClasses(ProgramRibbonModel.class);
		context.addModelsForClasses(ProgramRibbonModelItem.class);
	}
	


/*
 * The below method will test each property values
 */

@Test
public void Test_whenPropExists() {

	Resource resource = context.resourceResolver().getResource(PROGRAM_RIBBON_COMPONENT_PATH);
	programRibbon = resource.adaptTo(ProgramRibbonModel.class);
	String programRibbonId = programRibbon.getProgramRibbonId(); 
	List<ProgramRibbonModelItem> programRibbonItems = programRibbon.getPgmRibbonLinks();
	Assert.assertEquals("12345",programRibbonId);
	Assert.assertEquals("/content/dam/sling-tv/devices/device-ui-screenshot/dvr-ui/Screen Shot 2017-05-30 at 3.14.31 PM.png",programRibbonItems.get(0).getImagePath());
	Assert.assertEquals("/content/dam/sling-tv/misc/Desi Video Image.png",programRibbonItems.get(1).getImagePath());
	Assert.assertEquals("/content/dam/sling-tv/devices/device-ui-screenshot/dvr-ui/Screen Shot 2017-05-30 at 3.15.10 PM.png",programRibbonItems.get(2).getImagePath());
	Assert.assertEquals("/content/dam/sling-tv/marquees/domestic/device-lps/BRA0175_GetPicky_DeviceMarquees_640x251_LG_V3.jpg",programRibbonItems.get(3).getImagePath());
	Assert.assertEquals("/content/dam/sling-tv/devices/device-ui-screenshot/dvr-ui/Screen Shot 2017-05-30 at 3.13.20 PM.png",programRibbonItems.get(4).getImagePath());
	Assert.assertEquals("/content/dam/sling-tv/devices/device-ui-screenshot/dvr-ui/Screen Shot 2017-05-30 at 3.13.56 PM.png",programRibbonItems.get(5).getImagePath());
	Assert.assertEquals("/content/dam/sling-tv/devices/device-ui-screenshot/dvr-ui/Screen Shot 2017-05-30 at 3.15.52 PM.png",programRibbonItems.get(6).getImagePath());

}
	
	
}