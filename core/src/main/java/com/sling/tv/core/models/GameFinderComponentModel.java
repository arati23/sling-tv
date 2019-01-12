package com.sling.tv.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class GameFinderComponentModel {
	
	
  private static Logger LOG = LoggerFactory.getLogger(GameFinderComponentModel.class);


	@Inject
	private String errorHeadline;
  
	

	@Inject
	private String errorNoGames;
  
	@Inject
	private Resource sports;

	private List<GameFinderSportModel> sportList;

	@Inject
	private String preselection;
	
	@Inject
	private String calendarFebruary;
	
	@Inject
	private String logosFolder;
	
	@Inject
	private String matchSeparator;
	
	@Inject
	private String errorServer;
	

	@Inject
	private String calendarJanuary;
	
	
	@Inject
	private String blackoutText;
	
	@Inject
	private String searchText;
	
	@Inject
	private String calendarOctober;
	
	@Inject
	private String sheetUrl;
	
	@Inject
	private String calendarDecember;
	
	@Inject
	private String calendarJuly;
	
	@Inject
	private String calendarJune;
	
	@Inject
	private String zipText;
	
	@Inject
	private String errorNoResults;
	
	@Inject
	private String calendarAugust;
	
	@Inject
	private String calendarButton;
	
	
	@Inject
	private String calendarTuesday;
	
	@Inject
	private String calendarFriday;
	
	@Inject
	private String paginationText;

	@Inject
	private String rowsPerPage;
	
	@Inject
	private String calendarMonday;
	
	@Inject
	private String zipMismatchText;
	
	@Inject
	private String calendarSunday;
	
	@Inject
	private String calendarMay;
	
	@Inject
	private String calendarThursday;
	
	@Inject
	private String calendarNovember;
	
	@Inject
	private String disclaimerText;
	
	@Inject
	private String calendarWednesday;
	
	@Inject
	private String calendarSaturday;
	
	@Inject
	private String calendarSeptember;
	
	@Inject
	private String packagePrefix;
	
	@Inject
	private String initialRows;
	
	@Inject
	private String errorClear;
	
	@Inject
	private String collapsed;
	
	@Inject
	private String calendarApril;
	
	@Inject
	private String calendarMarch;
	
	@Inject
	private String filePath;
	
	
	
	
	
	
	
	
	
	
	
	
	public String getErrorHeadline() {

		return this.errorHeadline;

	}


	public String getErrorNoGames() {

		return this.errorNoGames;

	}



	public String getPreselection() {

		return this.preselection;

	}



	public String getCalendarFebruary() {

		return this.calendarFebruary;

	}



	public String getLogosFolder() {

		return this.logosFolder;

	}



	public String getMatchSeparator() {

		return this.matchSeparator;

	}



	public String getErrorServer() {

		return this.errorServer;

	}


	public String getCalendarJanuary() {

		return this.calendarJanuary;

	}



	public String getBlackoutText() {

		return this.blackoutText;

	}



	public String getSearchText() {

		return this.searchText;

	}



	public String getCalendarOctober() {

		return this.calendarOctober;

	}



	public String getSheetUrl() {

		return this.sheetUrl;

	}



	public String getCalendarDecember() {

		return this.calendarDecember;

	}



	public String getCalendarJuly() {

		return this.calendarJuly;

	}



	public String getCalendarJune() {

		return this.calendarJune;

	}



	public String getZipText() {

		return this.zipText;

	}



	public String getErrorNoResults() {

		return this.errorNoResults;

	}



	public String getCalendarAugust() {

		return this.calendarAugust;

	}



	public String getCalendarButton() {

		return this.calendarButton;

	}



	public String getCalendarTuesday() {

		return this.calendarTuesday;

	}



	public String getCalendarFriday() {

		return this.calendarFriday;

	}


	public String getPaginationText() {

		return this.paginationText;

	}



	public String getRowsPerPage() {

		return this.rowsPerPage;

	}



	public String getCalendarMonday() {

		return this.calendarMonday;

	}



	public String getZipMismatchText() {

		return this.zipMismatchText;

	}



	public String getCalendarSunday() {

		return this.calendarSunday;

	}



	public String getCalendarMay() {

		return this.calendarMay;

	}



	public String getCalendarThursday() {

		return this.calendarThursday;

	}



	public String getCalendarNovember() {

		return this.calendarNovember;

	}



	public String getDisclaimerText() {

		return this.disclaimerText;

	}



	public String getCalendarWednesday() {

		return this.calendarWednesday;

	}



	public String getCalendarSaturday() {

		return this.calendarSaturday;

	}



	public String getInitialRows() {

		return this.initialRows;

	}



	public String getPackagePrefix() {

		return this.packagePrefix;

	}



	public String getCalendarSeptember() {

		return this.calendarSeptember;

	}



	public String getErrorClear() {

		return this.errorClear;

	}



	public String getCollapsed() {

		return this.collapsed;

	}



	public String getCalendarApril() {

		return this.calendarApril;

	}



	public String getCalendarMarch() {

		return this.calendarMarch;

	}



	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<GameFinderSportModel> getSports() {
		return this.sportList;
	}
	
	@PostConstruct
	private void init()
	{
		LOG.debug("inside init" );
		this.sportList = populateSportList();
	}
	

	private List<GameFinderSportModel> populateSportList() {
		
		LOG.debug("inside populate Sportlist. sport node null is {}", sports ==null );
		List<GameFinderSportModel> tGameFinderSportList = new ArrayList<>();
		
		if (this.sports != null)
		{
			Iterator<Resource> resourceChildren = sports.listChildren();

			while (resourceChildren.hasNext())
			{
				tGameFinderSportList.add(resourceChildren.next().adaptTo(GameFinderSportModel.class));
			}
			
		}
		
		return tGameFinderSportList;
	}
}
