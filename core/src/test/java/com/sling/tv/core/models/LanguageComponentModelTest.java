package com.sling.tv.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.junit.Assert;

public class LanguageComponentModelTest {

	private LanguageComponentModel lcm;
	private static final String COMPONENT_PATH = "/content/slingtv/en/domestic/header";

	@Rule
	public final AemContext context = new AemContext();

	@Before
	public void setup() {

		context.load().json("/header.json", COMPONENT_PATH);
		context.addModelsForClasses(LanguageComponentModel.class);
		context.addModelsForClasses(LanguageModel.class);

	}

	@Test
	public void Test_whenLanguageLinksexists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH );
		lcm = resource.adaptTo(LanguageComponentModel.class);
		
		Assert.assertNotNull(lcm);
		List<LanguageModel> lm = lcm.getLanguageList();
		String expectedLanguageLink = "/arabic-ar";
		String actualLanguageLink = lm.get(0).getLanguageLink();

		Assert.assertEquals(expectedLanguageLink, actualLanguageLink);
		
	}
	
	@Test
	public void whenLanguageNameExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		lcm = resource.adaptTo(LanguageComponentModel.class);
		List<LanguageModel> lml = lcm.getLanguageList();

		String expectedLanguageName = "Sinhala";
		String actualLanguageName = lml.get(1).getLanguageName();

		Assert.assertEquals(expectedLanguageName, actualLanguageName);
	}
	
	
	
}
