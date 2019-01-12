package com.sling.tv.core.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class BonusChannelModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private BonusChannelsModel bonusChannelsModel;
	private List<BonusChannelsItemModel> bonusChannelsItemModels;
	private static final String COMPONENT_PATH = "/content/slingtv/international/hindi/jcr:content/par/bonuschannels";

	@Before
	public void setup() {
		
		context.load().json("/bonuschannel.json", COMPONENT_PATH);
		context.addModelsForClasses(BonusChannelsModel.class);
		context.addModelsForClasses(BonusChannelsItemModel.class);
	}
	
	
	
	@Test
	public void Test_whenIDexists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		bonusChannelsModel = resource.adaptTo(BonusChannelsModel.class);
		String theID = bonusChannelsModel.getBonusChannelsId();
		Assert.assertEquals("ABCD123",theID);
	}

	@Test
	public void Test_whenChannelNameExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		BonusChannelsModel channelsModel = resource.adaptTo(BonusChannelsModel.class);
		List<BonusChannelsItemModel> bonusChannelsItem = channelsModel.getAllChannels();
		
		Assert.assertEquals("AAJTK",bonusChannelsItem.get(0).getChannelName());
	}
	
	@Test
	public void Test_whenChannelNameDoesNotExist() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		bonusChannelsModel = resource.adaptTo(BonusChannelsModel.class);
		List<BonusChannelsItemModel> bonusChannelsItems = bonusChannelsModel.getAllChannels();
		Assert.assertNull(bonusChannelsItems.get(1).getChannelName());
	}
	
	
	@Test
	public void nullTest() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		bonusChannelsModel = resource.adaptTo(BonusChannelsModel.class);
		Assert.assertNotNull(bonusChannelsModel);

	}
	

}
