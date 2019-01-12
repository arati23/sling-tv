package com.sling.tv.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import org.apache.sling.models.annotations.Model;

/*
 * This class will be the sling model for all the properties in MatchUp Banner Component
 */

@Model(adaptables = Resource.class,

		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL

)

public class MatchUpBannerModel {
	
	@Inject
	private String matchupTeam1;
	
	@Inject
	private String matchupSeparator;
	
	@Inject
	private String matchupTeam2;
	
	@Inject
	private String matchupTitle;
	
	@Inject
	private String matchupBannerId;

	public String getMatchupTeam2() {
		return matchupTeam2;
	}

	public String getMatchupSeparator() {
		return matchupSeparator;
	}

	public String getMatchupTeam1() {
		return matchupTeam1;
	}

	public String getMatchupTitle() {
		return matchupTitle;
	}

	public String getMatchupBannerId() {
		return matchupBannerId;
	}

		
}
