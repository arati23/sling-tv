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
public class ResponsiveTabsModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private ResponsiveTabsModel respTabs;
	private static final String RESPONSIVE_TABS_COMPONENT_PATH = "/content/sling-tv/en/test/responsive-tabs/jcr:content/par/responsive_tab";
	
	@Before
	public void setup() {

		context.load().json("/responsive-tabs.json", RESPONSIVE_TABS_COMPONENT_PATH);
		
		context.addModelsForClasses(ResponsiveTabsModel.class);
		context.addModelsForClasses(ResponsiveTabsItemsModel.class);
	}
	/*
	 * The below method will test each property values
	 */
	
	@Test
	public void Test_whenPropExists() {

		Resource resource = context.resourceResolver().getResource(RESPONSIVE_TABS_COMPONENT_PATH);
		respTabs = resource.adaptTo(ResponsiveTabsModel.class);
		String tabsId  = respTabs.getTabsId();
		List<ResponsiveTabsItemsModel> lstRespTabsItems  = respTabs.getTabs();
		Assert.assertEquals("res1234",tabsId);
		
		Assert.assertEquals("true",lstRespTabsItems.get(0).getDefaultTab());
		Assert.assertEquals("/content/dam/sling-tv/misc/slinglogo-125x50.png",lstRespTabsItems.get(0).getTabIcon());
		Assert.assertEquals("One",lstRespTabsItems.get(0).getTabTitle());
		
		
		Assert.assertEquals("true",lstRespTabsItems.get(1).getHideTab());
		Assert.assertEquals("/content/dam/sling-tv/misc/slinglogo-125x50.png",lstRespTabsItems.get(1).getTabIcon());
		Assert.assertEquals("Two",lstRespTabsItems.get(1).getTabTitle());

	}
	
	/*
	 * The below method will test not null
	 */
	
	@Test
	public void Test_NotNullComp() {

		Resource resource = context.resourceResolver().getResource(RESPONSIVE_TABS_COMPONENT_PATH);
		respTabs = resource.adaptTo(ResponsiveTabsModel.class);
		Assert.assertNotNull(respTabs);
	}
}
