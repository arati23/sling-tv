/**
* 
 */
package com.sling.tv.core.models;
 
import java.sql.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.day.cq.commons.date.InvalidDateException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
* @author Nithya
*
*/
 
 
@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CountdownClockModel {
	
	/** The Constant LOGGER. */
	private static final Logger	LOGGER	= LoggerFactory.getLogger(CountdownClockModel.class);
                
                @Inject
                private String countdownStartDate;
                
                @Inject
                private String showTextVersion ;
                
                @Inject
                private String dayMonthText ;
                
                @Inject
                private String textSeparator;
                
                @Inject
                private String timeZoneText;
                
                @Inject
                private String daysCountLimit;
                
                @Inject
                private String countdownDayText;
                
                @Inject
                private String countdownHourText;
                
                @Inject
                private String countdownMinutesText;
                
                @Inject
                private String countdownSecondsText;
                
                @Inject
                private String countdownEndText;
                
                @Inject
                private String timeDuration;
                
                @Inject
                private String countdownTime;
                
                @Inject
                private String countdownDays;
                
                @Inject
                private String imagePath;
                
                @Inject
                private String gameDesc;
                
                @Inject
                private String countdownClockId;

				
				/**
				 * Inits the.
				 * @throws InvalidDateException
				 */
				@PostConstruct
				private void init() throws InvalidDateException {
							
				}

				/**
				 * Gets the countdown start formated date.
				 * @return the countdown start formated date
				 * @throws InvalidDateException the invalid date exception
				 */
				public String getCountdownStartDate() throws InvalidDateException {
					LOGGER.info("Original Date"+countdownStartDate);
					String dateArr[] = this.countdownStartDate.split("T");
					return dateArr[0]+" "+StringUtils.substring(dateArr[1], 0, 8);
				}


				public String getShowTextVersion() {
					return showTextVersion;
				}

				public String getDayMonthText() {
					return dayMonthText;
				}

				public String getTextSeparator() {
					return textSeparator;
				}

				public String getTimeZoneText() {
					return timeZoneText;
				}

				public String getDaysCountLimit() {
					return daysCountLimit;
				}

				public String getCountdownDayText() {
					return countdownDayText;
				}

				public String getCountdownHourText() {
					return countdownHourText;
				}

				public String getCountdownMinutesText() {
					return countdownMinutesText;
				}

				public String getCountdownSecondsText() {
					return countdownSecondsText;
				}

				public String getCountdownEndText() {
					return countdownEndText;
				}

				public String getTimeDuration() {
					return timeDuration;
				}

				public String getCountdownTime() {
					return countdownTime;
				}

				public String getCountdownDays() {
					return countdownDays;
				}

				public String getImagePath() {
					return imagePath;
				}

				public String getGameDesc() {
					return gameDesc;
				}

				public String getCountdownClockId() {
					return countdownClockId;
				}
                
                
                
 
                              
 
}

