package com.sling.tv.core.models;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import io.wcm.testing.mock.aem.junit.AemContext;
import org.junit.Assert;

public class CustomVideoModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private CustomVideoModel customVideo;
	private static final String COMPONENT_PATH = "/content/slingtv/en/services/jcr:content/par/customvideo";

	@Before
	public void setup() {
		
		context.load().json("/custom-video.json", COMPONENT_PATH);
		context.addModelsForClasses(CustomVideoModel.class);
	}

	@Test
	public void whenCustomVideoPropExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		customVideo = resource.adaptTo(CustomVideoModel.class);
		String altTxt = customVideo.getAltText();
		String imgWidth = customVideo.getImageWidth();
		String imgLink = customVideo.getImageLink();
		String autoPlay = customVideo.getAutoPlay();
		String cmpId = customVideo.getCustomVideoComponentId();
		String damLink = customVideo.getDamVideoLink();
		String paddLeft = customVideo.getPaddingLeft();
		String paddRight = customVideo.getPaddingRight();
		String videoLink = customVideo.getVideoLink();
		Assert.assertEquals("gvk",altTxt);
		Assert.assertEquals("80",imgWidth);
		Assert.assertEquals("/content/dam/sling-tv/misc/Desi Video Image.png",imgLink);
		Assert.assertEquals("false",autoPlay);
		Assert.assertEquals("CustomID",cmpId);
		Assert.assertEquals("/content/dam/sling-tv/BannerVideos/Sling - Reality Daily Cricket Bollywood_300118_A.mp4",damLink);
		Assert.assertEquals("5",paddLeft);
		Assert.assertEquals("3",paddRight);
		Assert.assertEquals("https://youtube.com/abcd",videoLink);

	}

	@Test
	public void nullTest() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		customVideo = resource.adaptTo(CustomVideoModel.class);
		Assert.assertNotNull(customVideo);

	}

}
