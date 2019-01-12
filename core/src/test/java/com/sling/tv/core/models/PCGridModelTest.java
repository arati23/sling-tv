/**
 * 
 */
package com.sling.tv.core.models;

import java.io.InputStream;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;

import io.wcm.testing.mock.aem.junit.AemContext;

/**
 * @author Nithya Nair
 *
 */

public class PCGridModelTest {
	
	
	@Rule
	public final AemContext context = new AemContext(ResourceResolverType.JCR_MOCK);
	
	private PCGridModel pcGrid;
	private static final String PC_GRID_COMPONENT_PATH = "/content/sling-tv/home/jcr:content/par/pc_grid";
	

	@Before
	public void setup() {

		context.load().json("/pc-grid.json", PC_GRID_COMPONENT_PATH);
		
		context.addModelsForClasses(PCGridModel.class);
		
	}
	
	@Test
	public void Test_whenPropExists() {
		
		ClassLoader classLoader = getClass().getClassLoader();
    	InputStream inputS = classLoader.getResourceAsStream("pcgridmodelpackage.json");
		context.create().resource("/content/sling-tv/api-data/us/one-week-promo-packages.json/jcr:content", ImmutableMap.<String, Object>builder().put("jcr:data", inputS).build());

		Resource resource = context.resourceResolver().getResource(PC_GRID_COMPONENT_PATH);
		pcGrid = resource.adaptTo(PCGridModel.class);
		Assert.assertEquals("SLING BLUE",pcGrid.getPlanTwoSubTitle());
		Assert.assertEquals("$40",pcGrid.getPlanThreeTitle());
		Assert.assertEquals("/content/dam/sling-tv/channels/AllLOBLogos/Color",pcGrid.getChannelLogoPath() );
		Assert.assertEquals("BEST VALUE SLING ORANGE + SLING BLUE",pcGrid.getPlanThreePackageOneTitle());
		Assert.assertEquals("SAVE 20%",pcGrid.getPlanThreeSubTitle() );
		Assert.assertEquals("domestic",pcGrid.getPlanOnePackageOne() );
		Assert.assertEquals("one-week-promo",pcGrid.getPlanOne() );
		Assert.assertEquals("one-week-promo",pcGrid.getPlanTwo() );
		Assert.assertEquals("$25",pcGrid.getPlanTwoTitle() );
		Assert.assertEquals("FS1, FX, & BRAVO",pcGrid.getPlanTwoPackageOneTitle() );
		Assert.assertEquals("false",pcGrid.getStickyTabsMobile() );
		Assert.assertEquals("us",pcGrid.getClassificationOne() );
		Assert.assertEquals("us",pcGrid.getClassificationTwo() );
		Assert.assertEquals("sling-combo",pcGrid.getPlanThreePackageOne() );
		Assert.assertEquals("defaultTab1",pcGrid.getDefaultTab() );
		Assert.assertEquals("us",pcGrid.getClassificationThree() );
		Assert.assertEquals("SLING ORANGE",pcGrid.getPlanOneSubTitle() );
		Assert.assertEquals("ESPN, DISNEY, & MORE",pcGrid.getPlanOnePackageOneTitle() );
		Assert.assertEquals("sling-mss",pcGrid.getPlanTwoPackageOne() );
		Assert.assertEquals("one-week-promo",pcGrid.getPlanThree() );
		Assert.assertEquals("stickyTabs",pcGrid.getStickyTabs() );
		Assert.assertEquals("planOneTitle",pcGrid.getPlanOneTitle() );
		Assert.assertEquals("planOnePackageTwoTitle",pcGrid.getPlanOnePackageTwoTitle());
		Assert.assertEquals("planOnePackageThreeTitle",pcGrid.getPlanOnePackageThreeTitle() );
		Assert.assertEquals("planTwoPackageTwoTitle",pcGrid.getPlanTwoPackageTwoTitle() );
		Assert.assertEquals("planTwoPackageThreeTitle",pcGrid.getPlanTwoPackageThreeTitle() );
		Assert.assertEquals("planThreePackageTwoTitle",pcGrid.getPlanThreePackageTwoTitle() );
		Assert.assertEquals("planThreePackageThreeTitle",pcGrid.getPlanThreePackageThreeTitle() );
		Assert.assertEquals("planOnePackageOneSubTitle",pcGrid.getPlanOnePackageOneSubTitle() );
		Assert.assertEquals("planOnePackageTwoSubTitle",pcGrid.getPlanOnePackageTwoSubTitle() );
		Assert.assertEquals("planOnePackageThreeSubTitle",pcGrid.getPlanOnePackageThreeSubTitle() );
		Assert.assertEquals("planTwoPackageOneSubTitle",pcGrid.getPlanTwoPackageOneSubTitle() );
		Assert.assertEquals("planTwoPackageTwoSubTitle",pcGrid.getPlanTwoPackageTwoSubTitle());
		Assert.assertEquals("planTwoPackageThreeSubTitle",pcGrid.getPlanTwoPackageThreeSubTitle() );
		Assert.assertEquals("planThreePackageOneSubTitle",pcGrid.getPlanThreePackageOneSubTitle() );
		Assert.assertEquals("planThreePackageTwoSubTitle",pcGrid.getPlanThreePackageTwoSubTitle() );
		Assert.assertEquals("planThreePackageThreeSubTitle",pcGrid.getPlanThreePackageThreeSubTitle() );
		Assert.assertEquals("planOnePackageTwo",pcGrid.getPlanOnePackageTwo());
		Assert.assertEquals("planOnePackageThree",pcGrid.getPlanOnePackageThree());
		Assert.assertEquals("planTwoPackageTwo",pcGrid.getPlanTwoPackageTwo());
		Assert.assertEquals("planTwoPackageThree",pcGrid.getPlanTwoPackageThree());
		Assert.assertEquals("planThreePackageTwo", pcGrid.getPlanThreePackageTwo());
		Assert.assertEquals("planThreePackageThree",pcGrid.getPlanThreePackageThree());
		}
	
	
	/*
	 * The below method will test not null
	 */
	
	@Test
	public void Test_NotNullComp() {
		Resource resource = context.resourceResolver().getResource(PC_GRID_COMPONENT_PATH);
		pcGrid = resource.adaptTo(PCGridModel.class);
		Assert.assertNotNull(pcGrid);
	}
	

}
