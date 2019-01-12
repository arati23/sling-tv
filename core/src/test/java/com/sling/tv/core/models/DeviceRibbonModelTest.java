package com.sling.tv.core.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class DeviceRibbonModelTest {

	@Rule
	public final AemContext context = new AemContext();


	private List<DevicePage> devicePage;
	private static final String COMPONENT_PATH = "/content/sling-tv/en/test/jcr:content/par/device_ribbon";

	@Before
	public void setup() {
		
		context.load().json("/device-ribbon.json", COMPONENT_PATH);
		context.addModelsForClasses(DeviceRibbonModel.class);
	}

	@Test
	public void Test_whenIDexists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		DeviceRibbonModel deviceRibbonPath = resource.adaptTo(DeviceRibbonModel.class);
		String theID = deviceRibbonPath.getDeviceRibbonSharedComponentId();
		Assert.assertEquals("deviceRibbonID",theID);
	}

	@Test
	public void Test_whenDeviceRibbonPathexists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		DeviceRibbonModel deviceRibbonPath = resource.adaptTo(DeviceRibbonModel.class);
		String deviceRibbonPathVal  = deviceRibbonPath.getDeviceRibbonPath();
		Assert.assertEquals("/content/sling-tv/en/deviceribbon/whpdeviceribbon",deviceRibbonPathVal);
	}

	@Test
	public void Test_whenOneRowexists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		DeviceRibbonModel deviceRibbonPath = resource.adaptTo(DeviceRibbonModel.class);
		String oneRow  = deviceRibbonPath.getOneRow();
		Assert.assertEquals("true",oneRow);
	}

	
	@Test
	public void nullTest() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		DeviceRibbonModel deviceRibbonPath = resource.adaptTo(DeviceRibbonModel.class);
		Assert.assertNotNull(deviceRibbonPath);

	}
	

}
