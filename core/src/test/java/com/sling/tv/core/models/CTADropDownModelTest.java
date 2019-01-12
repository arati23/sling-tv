package com.sling.tv.core.models;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import io.wcm.testing.mock.aem.junit.AemContext;
import org.junit.Assert;

public class CTADropDownModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private CTADropDownModel ctaDropDownModel;
	private List<CTADropDownItemsModel> ctaDropDownModelItems;
	private static final String CTA_DROPDOWN_COMPONENT_PATH = "/content/sling-tv/en/domestic/jcr:content/par/cta_dropdown";

	@Before
	public void setup() {
		
		context.load().json("/cta-dropdown.json", CTA_DROPDOWN_COMPONENT_PATH);
		context.addModelsForClasses(CTADropDownModel.class);
		context.addModelsForClasses(CTADropDownItemsModel.class);
	}
	
	
	
	@Test
	public void Test_equalsNavItems() {

		Resource resource = context.resourceResolver().getResource(CTA_DROPDOWN_COMPONENT_PATH);
		ctaDropDownModel = resource.adaptTo(CTADropDownModel.class);
		
		String compID=ctaDropDownModel.getDynamicCtaDropdownId();
		String dropDownLogo=ctaDropDownModel.getDropDownLogo();
		Assert.assertEquals("2000",compID);
		Assert.assertEquals("/content/dam/sling-tv/misc/new-home-page-videos/extra-icons/BroadcastExtra.svg",dropDownLogo);
		
		
		
	}

	@Test
	public void Test_whenCTADropDownItemsExists() {

		Resource resource = context.resourceResolver().getResource(CTA_DROPDOWN_COMPONENT_PATH);
		CTADropDownModel ctaDropDownModel = resource.adaptTo(CTADropDownModel.class);
		List<CTADropDownItemsModel> ctaDrowDown = ctaDropDownModel.getDynamicCTAItems();
		Assert.assertEquals("/content/dam/sling-tv/channels/Language-Logos/caribe.png",ctaDrowDown.get(0).getCtaLogo());
		Assert.assertEquals("/content/sling-tv/en/shared-components/latino_ctas/jcr:content/par/cta_component",ctaDrowDown.get(0).getCtaPath());

	}

	@Test
	public void Test_whenCTADropDownItemsDoesNotExists() {

		Resource resource = context.resourceResolver().getResource(CTA_DROPDOWN_COMPONENT_PATH);
		CTADropDownModel ctaDropDownModel = resource.adaptTo(CTADropDownModel.class);
		List<CTADropDownItemsModel> ctaDrowDownItems = ctaDropDownModel.getDynamicCTAItems();
		Assert.assertNull(ctaDrowDownItems.get(1).getCtaLogo());
		Assert.assertNull(ctaDrowDownItems.get(1).getCtaPath());

	}

	@Test
	public void Test_NotNullComp() {

		Resource resource = context.resourceResolver().getResource(CTA_DROPDOWN_COMPONENT_PATH);
		ctaDropDownModel = resource.adaptTo(CTADropDownModel.class);
		Assert.assertNotNull(ctaDropDownModel);
	}
	
		
	
	

}
