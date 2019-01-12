package com.sling.tv.core.models;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import io.wcm.testing.mock.aem.junit.AemContext;
import org.junit.Assert;

public class SubNavigationModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private SubNavigationModel subNavigation;
	private List<SubNavigationItemsModel> subNavigationItems;
	private static final String SUB_NAV_COMPONENT_PATH = "/content/slingtv/en/services/jcr:content/par/sub_navigation";

	@Before
	public void setup() {
		
		context.load().json("/sub-navigation.json", SUB_NAV_COMPONENT_PATH);
		context.addModelsForClasses(SubNavigationModel.class);
		context.addModelsForClasses(SubNavigationItemsModel.class);
	}
	
	
	
	@Test
	public void Test_equalsNavItems() {

		Resource resource = context.resourceResolver().getResource(SUB_NAV_COMPONENT_PATH);
		subNavigation = resource.adaptTo(SubNavigationModel.class);
		List<SubNavigationItemsModel> navItems = subNavigation.getSubNavItems();
		Assert.assertEquals("Channels",navItems.get(0).getSubNavTitle());
		Assert.assertEquals("channelid",navItems.get(0).getTargetId());
	}
	
	@Test
	public void Test_NotNullNavItems() {

		Resource resource = context.resourceResolver().getResource(SUB_NAV_COMPONENT_PATH);
		subNavigation = resource.adaptTo(SubNavigationModel.class);
		List<SubNavigationItemsModel> navItems = subNavigation.getSubNavItems();
		Assert.assertNotNull(navItems.get(0).getSubNavTitle());
		Assert.assertNotNull(navItems.get(0).getTargetId());
	}
	
	@Test
	public void Test_NullNavItems() {

		Resource resource = context.resourceResolver().getResource(SUB_NAV_COMPONENT_PATH);
		subNavigation = resource.adaptTo(SubNavigationModel.class);
		List<SubNavigationItemsModel> navItems = subNavigation.getSubNavItems();
		Assert.assertNull(navItems.get(1).getSubNavTitle());
		Assert.assertNull(navItems.get(1).getTargetId());
	}
	
	
	
	

}
