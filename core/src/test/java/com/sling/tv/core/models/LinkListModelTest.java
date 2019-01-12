package com.sling.tv.core.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class LinkListModelTest {

	/*
	 * @ author Krishna Gunturu
	 *
	 */

	@Rule
	public final AemContext context = new AemContext();
	
	private LinkListModel linkList;
	private List<LinkListItemModel> linkItems;
	private static final String COMPONENT_PATH = "/content/slingtv/en/test/jcr:content/par/link-list";

	@Before
	public void setup() {
		
		context.load().json("/link-list.json", COMPONENT_PATH);
		context.addModelsForClasses(LinkListModel.class);
		context.addModelsForClasses(LinkListItemModel.class);
	}
	
	
	
	@Test
	public void Test_whenPropExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		linkList = resource.adaptTo(LinkListModel.class);
		String cmpId = linkList.getLinkListId();
		String lType = linkList.getLinkType();
		String mHeight = linkList.getMobileHeight();
		String dHeight = linkList.getDesktopHeight();
		String tHeight = linkList.getTabletHeight();

		Assert.assertEquals("componentId",cmpId);
		Assert.assertEquals("cta",lType);
		Assert.assertEquals("200",mHeight);
		Assert.assertEquals("120",dHeight);
		Assert.assertEquals("300",tHeight);

	}

	@Test
	public void Test_equalslinkListItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		linkList = resource.adaptTo(LinkListModel.class);
		List<LinkListItemModel> linkListItems = linkList.getLinkListItems();
		List<LinkListItemModel> linkCTAItems = linkList.getLinkListItemsCTA();
		Assert.assertEquals("arabic",linkListItems.get(0).getTitle());
		Assert.assertEquals("https://www.google.com",linkListItems.get(0).getLink());
		Assert.assertEquals("alacartetv",linkCTAItems.get(0).getCartFlow());
		Assert.assertEquals("0",linkCTAItems.get(0).getCartStep());
		Assert.assertEquals("us",linkCTAItems.get(0).getClassification());
		Assert.assertEquals("roku",linkCTAItems.get(0).getDeviceType());
		Assert.assertEquals("gani",linkCTAItems.get(0).getOfferId());
		Assert.assertEquals("https://www.sling.com",linkCTAItems.get(0).getLinkCTA());
		Assert.assertEquals("hindi",linkCTAItems.get(0).getTitleCTA());
		Assert.assertEquals("one-week-promo",linkCTAItems.get(0).getPlanId());
		Assert.assertEquals("sling-combo",linkCTAItems.get(0).getPackageId());
		Assert.assertEquals("king-ganesh",linkCTAItems.get(0).getExtra());

	}

	@Test
	public void Test_NotNullImageCarouselItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		linkList = resource.adaptTo(LinkListModel.class);
		List<LinkListItemModel> linkListItems = linkList.getLinkListItems();
		List<LinkListItemModel> linkCTAItems = linkList.getLinkListItemsCTA();
		Assert.assertNotNull(linkListItems.get(0).getTitle());
		Assert.assertNotNull(linkListItems.get(0).getLink());
		Assert.assertNotNull(linkCTAItems.get(0).getCartFlow());
		Assert.assertNotNull(linkCTAItems.get(0).getCartStep());
		Assert.assertNotNull(linkCTAItems.get(0).getClassification());
		Assert.assertNotNull(linkCTAItems.get(0).getDeviceType());
		Assert.assertNotNull(linkCTAItems.get(0).getExtra());
		Assert.assertNotNull(linkCTAItems.get(0).getLinkCTA());
		Assert.assertNotNull(linkCTAItems.get(0).getOfferId());
		Assert.assertNotNull(linkCTAItems.get(0).getPackageId());
		Assert.assertNotNull(linkCTAItems.get(0).getPlanId());
		Assert.assertNotNull(linkCTAItems.get(0).getTitleCTA());


	}

	@Test
	public void Test_NullLinkListItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		linkList = resource.adaptTo(LinkListModel.class);
		List<LinkListItemModel> linkListItems = linkList.getLinkListItems();
		List<LinkListItemModel> linkCTAItems = linkList.getLinkListItemsCTA();
		Assert.assertNull(linkListItems.get(1).getTitle());
		Assert.assertNull(linkListItems.get(1).getLink());
		Assert.assertNull(linkCTAItems.get(1).getPlanId());
		Assert.assertNull(linkCTAItems.get(1).getPackageId());
		Assert.assertNull(linkCTAItems.get(1).getOfferId());
		Assert.assertNull(linkCTAItems.get(1).getLinkCTA());
		Assert.assertNull(linkCTAItems.get(1).getExtra());
		Assert.assertNull(linkCTAItems.get(1).getDeviceType());
		Assert.assertNull(linkCTAItems.get(1).getClassification());
		Assert.assertNull(linkCTAItems.get(1).getCartStep());
		Assert.assertNull(linkCTAItems.get(1).getCartFlow());
		Assert.assertNull(linkCTAItems.get(1).getTitleCTA());

	}

}
