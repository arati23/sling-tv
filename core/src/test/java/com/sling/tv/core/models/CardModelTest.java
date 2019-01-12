package com.sling.tv.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.wcm.testing.mock.aem.junit.AemContext;

public class CardModelTest {
	
	/*
	 * 
	 * @author ganesh 
	 * 
	 */

	@Rule
	public final AemContext context = new AemContext();

	private CardModel card;
	private List<CardItemModel> cardItems;
	private static final String COMPONENT_PATH = "/content/slingtv/en/card/jcr:content/par/card";

	@Before
	public void setup() {

		context.load().json("/card.json", COMPONENT_PATH);
		context.addModelsForClasses(CardModel.class);
		context.addModelsForClasses(CardItemModel.class);
	}

	@Test
	public void Test_whenCardIDexists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		card = resource.adaptTo(CardModel.class);
		String theID = card.getCardId();
		Assert.assertEquals("09151991", theID);
	}

	@Test
	public void Test_whenCardTypeExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		card = resource.adaptTo(CardModel.class);
		String cardtype = card.getCardType();
		Assert.assertEquals("someRandomType", cardtype);
	}

	@Test
	public void Test_equalsTextCardItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		card = resource.adaptTo(CardModel.class);
		List<CardItemModel> cardItems = card.getTextCardItems();
		Assert.assertEquals("TestTextTitle", cardItems.get(0).getTextCardTitle());
		Assert.assertEquals("TestTextPackage", cardItems.get(0).getTextCardPackage());
		Assert.assertEquals("TestTextDescription", cardItems.get(0).getTextCardDesc());
	}

	@Test
	public void Test_NotNullTextCardItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		card = resource.adaptTo(CardModel.class);
		List<CardItemModel> cardItems = card.getTextCardItems();
		Assert.assertNotNull(cardItems.get(0).getTextCardTitle());
		Assert.assertNotNull(cardItems.get(0).getTextCardPackage());
		Assert.assertNotNull(cardItems.get(0).getTextCardDesc());
	}

	@Test
	public void Test_NullTextCardItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		card = resource.adaptTo(CardModel.class);
		List<CardItemModel> cardItems = card.getTextCardItems();
		Assert.assertNull(cardItems.get(1).getTextCardTitle());
		Assert.assertNull(cardItems.get(1).getTextCardPackage());
		Assert.assertNull(cardItems.get(1).getTextCardDesc());
	}

	@Test
	public void Test_equalsLogoCardItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		card = resource.adaptTo(CardModel.class);
		List<CardItemModel> cardItems = card.getLogoCardItems();
		Assert.assertEquals("TestLogoTitle", cardItems.get(0).getLogoCardTitle());
		Assert.assertEquals("TestLogoPackage", cardItems.get(0).getLogoCardPackage());
		Assert.assertEquals("TestLogoReferencePath", cardItems.get(0).getReferencePath());
	}

	@Test
	public void Test_NotNullLogoCardItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		card = resource.adaptTo(CardModel.class);
		List<CardItemModel> cardItems = card.getLogoCardItems();
		Assert.assertNotNull(cardItems.get(0).getLogoCardTitle());
		Assert.assertNotNull(cardItems.get(0).getLogoCardPackage());
		Assert.assertNotNull(cardItems.get(0).getReferencePath());
	}

	@Test
	public void Test_NullLogoCardItems() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		card = resource.adaptTo(CardModel.class);
		List<CardItemModel> cardItems = card.getLogoCardItems();
		Assert.assertNull(cardItems.get(1).getLogoCardTitle());
		Assert.assertNull(cardItems.get(1).getLogoCardPackage());
		Assert.assertNull(cardItems.get(1).getReferencePath());
	}

}
