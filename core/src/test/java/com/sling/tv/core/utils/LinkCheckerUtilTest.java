package com.sling.tv.core.utils;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import io.wcm.testing.mock.aem.junit.AemContext;
import org.junit.Assert;

public class LinkCheckerUtilTest {

	
	private LinkCheckerUtil lcu;

	@Before
	public void setup() {
		
		lcu = new LinkCheckerUtil();
	}
	
	@Test
	public void testWithPipeValues()
	{
		String testURL = "/test.html|another|another2";
		String expectedURL = "/test.html%7Canother%7Canother2";
		
		String returnedURL = lcu.absoluteUrl(testURL);
		
		Assert.assertEquals(expectedURL, returnedURL);
		
	}
	
	@Test
	public void testEndingWithJcrContent()
	{
		String testURL = "/test/another2/jcr:content";
		String expectedURL = "/test/another2/";
		
		String returnedURL = lcu.absoluteUrl(testURL);
		
		Assert.assertEquals(expectedURL, returnedURL);
		
	}
	
	
	@Test
	public void testEndingWithRelativePathNotHavingHTMLExtension()
	{
		String testURL = "/content/another2";
		String expectedURL = "/content/another2.html";
		
		String returnedURL = lcu.absoluteUrl(testURL);
		
		Assert.assertEquals(expectedURL, returnedURL);
		
	}
	
	@Test
	public void testEndingWithSchemeAndPathNotHavingHTMLExtension()
	{
		String testURL = "http:/content/another2";
		String expectedURL = "http:/content/another2";
		
		String returnedURL = lcu.absoluteUrl(testURL);
		
		Assert.assertEquals(expectedURL, returnedURL);
		
	}
	
	@Test
	public void testRelativePathNotBeginningInContent()
	{
		String testURL = "/notcontent/another2";
		String expectedURL = "/notcontent/another2";
		
		String returnedURL = lcu.absoluteUrl(testURL);
		
		Assert.assertEquals(expectedURL, returnedURL);
		
	}
	
	
	
	
		

}
