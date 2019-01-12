package com.sling.tv.core.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class ChannelLogosModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private ChannelLogosModel channelLogos;
	private List<ChannelLogosItemModel> channelLogosItems;
	private static final String COMPONENT_PATH = "/content/slingtv/en/image/jcr:content/par/channel-logos";

	@Before
	public void setup() {
		
		context.load().json("/channel-logos.json", COMPONENT_PATH);
		context.addModelsForClasses(ChannelLogosModel.class);
		context.addModelsForClasses(ChannelLogosItemModel.class);
	}

	@Test
	public void Test_equalsComponentId() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		channelLogos = resource.adaptTo(ChannelLogosModel.class);
		String cmpId = channelLogos.getChannelId();
		Assert.assertEquals("component Id",cmpId);
	}


	@Test
	public void Test_equalsChannelItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		channelLogos = resource.adaptTo(ChannelLogosModel.class);
		List<ChannelLogosItemModel> channelItems = channelLogos.getChannelLogos();
		Assert.assertEquals("image1",channelItems.get(0).getAltName());
		Assert.assertEquals("/content/dam/sling-tv/devices/device-logos/Roku - Black.png",channelItems.get(0).getChannelImage());
	}
	
	@Test
	public void Test_NotNullChannelItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		channelLogos = resource.adaptTo(ChannelLogosModel.class);
		List<ChannelLogosItemModel> channelItems = channelLogos.getChannelLogos();
		Assert.assertNotNull(channelItems.get(0).getAltName());
		Assert.assertNotNull(channelItems.get(0).getChannelImage());
	}
	
	@Test
	public void Test_NullChannelItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		channelLogos = resource.adaptTo(ChannelLogosModel.class);
		List<ChannelLogosItemModel> channelItems = channelLogos.getChannelLogos();
		Assert.assertNull(channelItems.get(1).getAltName());
		Assert.assertNull(channelItems.get(1).getChannelImage());
	}

}
