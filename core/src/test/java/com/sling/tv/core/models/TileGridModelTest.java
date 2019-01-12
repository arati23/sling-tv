package com.sling.tv.core.models;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import io.wcm.testing.mock.aem.junit.AemContext;
import org.junit.Assert;

public class TileGridModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private TileGridModel tileGrid;
	private List<TileGridItemModel> tileGridItems;
	private static final String COMPONENT_PATH = "/content/slingtv/en/services/jcr:content/par/tcg_grid";

	@Before
	public void setup() {
		
		context.load().json("/tilegrid.json", COMPONENT_PATH);
		context.addModelsForClasses(TileGridModel.class);
		context.addModelsForClasses(TileGridItemModel.class);
	}
	
	
	
	@Test
	public void Test_whenIDexists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		tileGrid = resource.adaptTo(TileGridModel.class);
		String theID = tileGrid.getTcgGridId();
		Assert.assertEquals("ABCD123",theID);
	}

	@Test
	public void Test_whenHideBreakoutExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		tileGrid = resource.adaptTo(TileGridModel.class);
		String hideBreakout = tileGrid.getHideBreakout();
		Assert.assertEquals("true",hideBreakout);
	}

	@Test
	public void Test_whenTabTileGridItemsExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		TileGridModel tileGrid = resource.adaptTo(TileGridModel.class);
		List<TileGridItemModel> tileItem = tileGrid.getTiles();
		
		Assert.assertEquals("tab title",tileItem.get(0).getTabTitle());
		Assert.assertEquals("/content/dam/sling-tv/icons/extras/news_icon.svg",tileItem.get(0).getTabIcon());
		Assert.assertEquals("/some_url",tileItem.get(0).getTabUrl());
		Assert.assertEquals("12345",tileItem.get(0).getUniqueId());
	}
	
	@Test
	public void Test_whenTabTitleGridItemsDoesNotExist() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		tileGrid = resource.adaptTo(TileGridModel.class);
		List<TileGridItemModel> gridItems = tileGrid.getTiles();
		Assert.assertNull(gridItems.get(1).getTabTitle());
		Assert.assertNull(gridItems.get(1).getTabIcon());
		Assert.assertNull(gridItems.get(1).getTabUrl());
		Assert.assertNull(gridItems.get(1).getUniqueId());
	}
	
	
	@Test
	public void nullTest() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		tileGrid = resource.adaptTo(TileGridModel.class);
		Assert.assertNotNull(tileGrid);

	}
	

}
