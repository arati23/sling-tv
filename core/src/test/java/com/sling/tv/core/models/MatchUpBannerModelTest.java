package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.wcm.testing.mock.aem.junit.AemContext;

public class MatchUpBannerModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	protected MatchUpBannerModel matchUpBannerModel;
	private static final String MATCHUP_BANNER_COMPONENT_PATH = "/content/slingtv/en/services/jcr:content/par/matchup-banner";
	

	@Before
	public void setup() {

		context.load().json("/matchup-banner.json", MATCHUP_BANNER_COMPONENT_PATH);
		context.addModelsForClasses(MatchUpBannerModel.class);
	}
	
	/*
	 * The below method will test each property values and match with matchup-banner.json
	 */
	
	@Test
	public void Test_whenPropExists() {

		Resource resource = context.resourceResolver().getResource(MATCHUP_BANNER_COMPONENT_PATH);
		matchUpBannerModel = resource.adaptTo(MatchUpBannerModel.class);
		String bannerID=matchUpBannerModel.getMatchupBannerId();
		String matchTitle=matchUpBannerModel.getMatchupTitle();
		String matchTeam1=matchUpBannerModel.getMatchupTeam1();
		String matchTeam2=matchUpBannerModel.getMatchupTeam2();
		String matchSeparator=matchUpBannerModel.getMatchupSeparator();
		Assert.assertEquals("12345",bannerID);
		Assert.assertEquals("Match Title",matchTitle);
		Assert.assertEquals("TeamA",matchTeam1);
		Assert.assertEquals("to",matchSeparator);
		Assert.assertEquals("TeamB",matchTeam2);
		

	}
	
	
	@Test
	public void Test_NotNullComp() {
		Resource resource = context.resourceResolver().getResource(MATCHUP_BANNER_COMPONENT_PATH);
		matchUpBannerModel = resource.adaptTo(MatchUpBannerModel.class);
		Assert.assertNotNull(matchUpBannerModel);
	}
}
