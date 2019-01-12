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
public class SocialMediaModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private SocialMediaModel socialMedia;
	private static final String SOCIAL_MEDIA_COMPONENT_PATH = "/content/slingtv/en/services/jcr:content/par/social-media";
	
	@Before
	public void setup() {

		context.load().json("/social-media.json", SOCIAL_MEDIA_COMPONENT_PATH);
		
		context.addModelsForClasses(SocialMediaModel.class);
		context.addModelsForClasses(SocialMediaItemsModel.class);
	}
	
	/*
	 * The below method will test each property values
	 */
	
	@Test
	public void Test_whenPropExists() {

		Resource resource = context.resourceResolver().getResource(SOCIAL_MEDIA_COMPONENT_PATH);
		socialMedia = resource.adaptTo(SocialMediaModel.class);
		String compId = socialMedia.getSocialMediaLinksId();
		List<SocialMediaItemsModel> socialLinkItems = socialMedia.getSocialMediaLinks();
		Assert.assertEquals("12345",compId);
		Assert.assertEquals("fa fa-facebook-official",socialLinkItems.get(0).getSocialMediaIcon());
		Assert.assertEquals("https://www.facebook.com",socialLinkItems.get(0).getSocialMediaLink());

	}
	
	/*
	 * The below method will test not null
	 */
	
	@Test
	public void Test_NotNullComp() {

		Resource resource = context.resourceResolver().getResource(SOCIAL_MEDIA_COMPONENT_PATH);
		socialMedia = resource.adaptTo(SocialMediaModel.class);
		Assert.assertNotNull(socialMedia);
	}

}
