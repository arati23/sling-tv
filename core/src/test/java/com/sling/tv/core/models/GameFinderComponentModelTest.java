package com.sling.tv.core.models;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import io.wcm.testing.mock.aem.junit.AemContext;
import org.junit.Assert;

public class GameFinderComponentModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private static final String COMPONENT_PATH = "/content/slingtv/en/services/jcr:content/par/gamefinder";

	@Before
	public void setup() {
		
		context.load().json("/gamefinder.json", COMPONENT_PATH);
		context.addModelsForClasses(GameFinderComponentModel.class);
		context.addModelsForClasses(GameFinderSportModel.class);
	}
	
	
	
@Test
public void whenErrorHeadlineExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedErrorHeadline = "OOPS!";
	String actualErrorHeadline = obj.getErrorHeadline();

	Assert.assertEquals(expectedErrorHeadline, actualErrorHeadline);
}

@Test
public void whenErrorNoGamesExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedErrorNoGames = "Season's Over, Man";
	String actualErrorNoGames = obj.getErrorNoGames();

	Assert.assertEquals(expectedErrorNoGames, actualErrorNoGames);
}

@Test
public void whenPreselectionExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedPreselection = "Chi";
	String actualPreselection = obj.getPreselection();

	Assert.assertEquals(expectedPreselection, actualPreselection);
}


@Test
public void whenCalendarFebruaryExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarFebruary = "February";
	String actualCalendarFebruary = obj.getCalendarFebruary();

	Assert.assertEquals(expectedCalendarFebruary, actualCalendarFebruary);
}


@Test
public void whenLogosFolderExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedLogosFolder = "/content/dam/sling-tv/channels/whitehomepage_logos";
	String actualLogosFolder = obj.getLogosFolder();

	Assert.assertEquals(expectedLogosFolder, actualLogosFolder);
}

@Test
public void whenMatchSeparatorExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedMatchSeparator = "@";
	String actualMatchSeparator = obj.getMatchSeparator();

	Assert.assertEquals(expectedMatchSeparator, actualMatchSeparator);
}

@Test
public void whenErrorServerExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedErrorServer = "It Broke";
	String actualErrorServer = obj.getErrorServer();

	Assert.assertEquals(expectedErrorServer, actualErrorServer);
}

@Test
public void whenCalendarJanuaryExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarJanuary = "January";
	String actualCalendarJanuary = obj.getCalendarJanuary();

	Assert.assertEquals(expectedCalendarJanuary, actualCalendarJanuary);
}

@Test
public void whenBlackoutTextExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedBlackoutText = "UNAVAILABLE";
	String actualBlackoutText = obj.getBlackoutText();

	Assert.assertEquals(expectedBlackoutText, actualBlackoutText);
}

@Test
public void whenSearchTextExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedSearchText = "Search (team, city, state)";
	String actualSearchText = obj.getSearchText();

	Assert.assertEquals(expectedSearchText, actualSearchText);
}

@Test
public void whenCalendarOctoberExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarOctober = "October";
	String actualCalendarOctober = obj.getCalendarOctober();

	Assert.assertEquals(expectedCalendarOctober, actualCalendarOctober);
}

@Test
public void whenSheetUrlExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedSheetUrl = "/content/dam/sling-tv/misc/gamefinder/gamefinder-demo-2.xlsx";
	String actualSheetUrl = obj.getSheetUrl();

	Assert.assertEquals(expectedSheetUrl, actualSheetUrl);
}

@Test
public void whenCalendarDecemberExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarDecember = "December";
	String actualCalendarDecember = obj.getCalendarDecember();

	Assert.assertEquals(expectedCalendarDecember, actualCalendarDecember);
}

@Test
public void whenCalendarJulyExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarJuly = "July";
	String actualCalendarJuly = obj.getCalendarJuly();

	Assert.assertEquals(expectedCalendarJuly, actualCalendarJuly);
}

@Test
public void whenCalendarJuneExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarJune = "June";
	String actualCalendarJune = obj.getCalendarJune();

	Assert.assertEquals(expectedCalendarJune, actualCalendarJune);
}

@Test
public void whenZipTextExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedZipText = "Zip";
	String actualZipText = obj.getZipText();

	Assert.assertEquals(expectedZipText, actualZipText);
}

@Test
public void whenErrorNoResultsExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedErrorNoResults = "No Results";
	String actualErrorNoResults = obj.getErrorNoResults();

	Assert.assertEquals(expectedErrorNoResults, actualErrorNoResults);
}

@Test
public void whenCalendarAugustExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarAugust = "August";
	String actualCalendarAugust = obj.getCalendarAugust();

	Assert.assertEquals(expectedCalendarAugust, actualCalendarAugust);
}

@Test
public void whenCalendarButtonExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarButton = "Game Date";
	String actualCalendarButton = obj.getCalendarButton();

	Assert.assertEquals(expectedCalendarButton, actualCalendarButton);
}

@Test
public void whenCalendarTuesdayExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarTuesday = "Tuesday";
	String actualCalendarTuesday = obj.getCalendarTuesday();

	Assert.assertEquals(expectedCalendarTuesday, actualCalendarTuesday);
}


@Test
public void whenCalendarFridayExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarFriday = "Friday";
	String actualCalendarFriday = obj.getCalendarFriday();

	Assert.assertEquals(expectedCalendarFriday, actualCalendarFriday);
}

@Test
public void whenPaginationTextExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedPaginationText = "See More";
	String actualPaginationText = obj.getPaginationText();

	Assert.assertEquals(expectedPaginationText, actualPaginationText);
}

@Test
public void whenRowsPerPageExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedRowsPerPage = "10";
	String actualRowsPerPage = obj.getRowsPerPage();

	Assert.assertEquals(expectedRowsPerPage, actualRowsPerPage);
}

@Test
public void whenCalendarMondayExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarMonday = "Monday";
	String actualCalendarMonday = obj.getCalendarMonday();

	Assert.assertEquals(expectedCalendarMonday, actualCalendarMonday);
}

@Test
public void whenZipMismatchTextExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedZipMismatchText = "UNAVAILABLE";
	String actualZipMismatchText = obj.getZipMismatchText();

	Assert.assertEquals(expectedZipMismatchText, actualZipMismatchText);
}

@Test
public void whenCalendarSundayExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarSunday = "Sunday";
	String actualCalendarSunday = obj.getCalendarSunday();

	Assert.assertEquals(expectedCalendarSunday, actualCalendarSunday);
}

@Test
public void whenCalendarMayExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarMay = "May";
	String actualCalendarMay = obj.getCalendarMay();

	Assert.assertEquals(expectedCalendarMay, actualCalendarMay);
}

@Test
public void whenCalendarThursdayExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarThursday = "Thursday";
	String actualCalendarThursday = obj.getCalendarThursday();

	Assert.assertEquals(expectedCalendarThursday, actualCalendarThursday);
}

@Test
public void whenCalendarNovemberExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarNovember = "November";
	String actualCalendarNovember = obj.getCalendarNovember();

	Assert.assertEquals(expectedCalendarNovember, actualCalendarNovember);
}

@Test
public void whenDisclaimerTextExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedDisclaimerText = "Dates and times change";
	String actualDisclaimerText = obj.getDisclaimerText();

	Assert.assertEquals(expectedDisclaimerText, actualDisclaimerText);
}
@Test
public void whenCalendarWednesdayExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarWednesday = "Wednesday";
	String actualCalendarWednesday = obj.getCalendarWednesday();

	Assert.assertEquals(expectedCalendarWednesday, actualCalendarWednesday);
}

@Test
public void whenCalendarSaturdayExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarSaturday = "Saturday";
	String actualCalendarSaturday = obj.getCalendarSaturday();

	Assert.assertEquals(expectedCalendarSaturday, actualCalendarSaturday);
}

@Test
public void whenInitialRowsExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedInitialRows = "10";
	String actualInitialRows = obj.getInitialRows();

	Assert.assertEquals(expectedInitialRows, actualInitialRows);
}


@Test
public void whenPackagePrefixExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedPackagePrefix = "AVAILABLE ON:";
	String actualPackagePrefix = obj.getPackagePrefix();

	Assert.assertEquals(expectedPackagePrefix, actualPackagePrefix);
}

@Test
public void whenCalendarSeptemberExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarSeptember = "September";
	String actualCalendarSeptember = obj.getCalendarSeptember();

	Assert.assertEquals(expectedCalendarSeptember, actualCalendarSeptember);
}


@Test
public void whenErrorClearExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedErrorClear = "See All";
	String actualErrorClear = obj.getErrorClear();

	Assert.assertEquals(expectedErrorClear, actualErrorClear);
}

@Test
public void whenCollapsedExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCollapsed = "true";
	String actualCollapsed = obj.getCollapsed();

	Assert.assertEquals(expectedCollapsed, actualCollapsed);
}

@Test
public void whenCalendarAprilExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarApril = "April";
	String actualCalendarApril = obj.getCalendarApril();

	Assert.assertEquals(expectedCalendarApril, actualCalendarApril);
}

@Test
public void whenCalendarMarchExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedCalendarMarch = "March";
	String actualCalendarMarch = obj.getCalendarMarch();

	Assert.assertEquals(expectedCalendarMarch, actualCalendarMarch);
}

@Test
public void whenSportExists() {

	Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
	GameFinderComponentModel obj = resource.adaptTo(GameFinderComponentModel.class);

	String expectedSportKey = "ncaa-ncf";
	String expectedSportName = "NCAA";
	String expectedShowHide = "true";
	String expectedChecked = "true";
	
	List<GameFinderSportModel> actualSportList = obj.getSports();
	String actualSportKey = actualSportList.get(0).getKeyName();
	String actualSportName = actualSportList.get(0).getSportName();

	Assert.assertEquals(expectedSportKey, actualSportKey);
	Assert.assertEquals(expectedSportName, actualSportName);
}




}
