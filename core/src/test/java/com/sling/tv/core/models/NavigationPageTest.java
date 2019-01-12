package com.sling.tv.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;


import io.wcm.testing.mock.aem.junit.AemContext;
import org.junit.Assert;

public class NavigationPageTest {

	private NavigationPage np;
	private static final String COMPONENT_PATH = "/content/slingtv/navigation-page";
	
    @Rule
    public final AemContext context = new AemContext();

	
	@Before
	public void setup() {
		
		context.load().json("/navigation-page.json", COMPONENT_PATH);
		context.addModelsForClasses(NavigationPage.class);

	}
	
	
	
	@Test
	public void Test_whenexpectedHideInDesktopNav() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		np = resource.adaptTo(NavigationPage.class);
		
		String expectedHideInDesktopNav = "false";
		String actualHideInDesktopNav = np.getHideInDesktopNav();
		Assert.assertEquals(expectedHideInDesktopNav, actualHideInDesktopNav);
	}
	
	@Test
	public void Test_whenHideInMobileNav() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		np = resource.adaptTo(NavigationPage.class);

		String expectedHideInMobileNav = "false";
		String actualHideInMobileNav = np.getHideInMobileNav();
		Assert.assertEquals(expectedHideInMobileNav, actualHideInMobileNav);
	}
	
	@Test
	public void Test_whenWindowSelection() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		np = resource.adaptTo(NavigationPage.class);

		String expectedWindowSelection = "_self";
		String actualWindowSelection = np.getWindowSelection();
		Assert.assertEquals(expectedWindowSelection, actualWindowSelection);
	}
	
	

}
