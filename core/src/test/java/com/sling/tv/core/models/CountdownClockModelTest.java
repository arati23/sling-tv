/**
 * 
 */
package com.sling.tv.core.models;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.day.cq.commons.date.InvalidDateException;

import io.wcm.testing.mock.aem.junit.AemContext;

/**
 * @author Nithya Nair
 *
 */
public class CountdownClockModelTest {
	
	@Rule
	public final AemContext context = new AemContext();
	
	protected  CountdownClockModel countdownClock;

	
	private static final String COUNTDOWN_CLOCK_COMPONENT_PATH = "/content/slingtv/en/services/jcr:content/par/countdown_clock";
    
	//json test date taken to format and match irrespective of time zone
	private final String strJsonDate = "2018-07-12T16:30:00.000-05:00";
	
	
	
	@Before
	
	public void setup() {
		context.load().json("/countdown-clock.json", COUNTDOWN_CLOCK_COMPONENT_PATH) ;
		
		context.addModelsForClasses(CountdownClockModel.class);
		
	}
	
	@Test
	public void Test_whenPropExists() throws InvalidDateException {

		Resource resource = context.resourceResolver().getResource(COUNTDOWN_CLOCK_COMPONENT_PATH);
		countdownClock = resource.adaptTo(CountdownClockModel.class);
		
		String ctdwnStDt = countdownClock.getCountdownStartDate();
		String showTxtVer = countdownClock.getShowTextVersion();
		String dayMnthTxt = countdownClock.getDayMonthText();
		String txtSep = countdownClock.getTextSeparator();
		String timeZoneTxt = countdownClock.getTimeZoneText();
		String daysCntLmt = countdownClock.getDaysCountLimit();
		String cntdwnDayTxt = countdownClock.getCountdownDayText();
		String cntdwnHrTxt = countdownClock.getCountdownHourText();
		String cntdwnMinTxt = countdownClock.getCountdownMinutesText();
		String cntdwnSecTxt = countdownClock.getCountdownSecondsText();
		String cntdwnEndTxt = countdownClock.getCountdownEndText();
		String cntdwnTime = countdownClock.getCountdownTime();
		String cntdwnDays = countdownClock.getCountdownDays();
		String imgPath = countdownClock.getImagePath();
		String gameDesc = countdownClock.getGameDesc();
		String cntdwnclkId=countdownClock.getCountdownClockId();
		
	    //To get the formatted date irrespective of time zone 
	    String strFormattedDate = getCountdownStartDate();
		
		Assert.assertEquals(strFormattedDate,ctdwnStDt);
		Assert.assertEquals("true",showTxtVer);
		Assert.assertEquals("July 12",dayMnthTxt);
		Assert.assertEquals("-",txtSep);
		Assert.assertEquals("EST 4:30PM",timeZoneTxt);
		Assert.assertEquals("7",daysCntLmt);
		Assert.assertEquals("Days",cntdwnDayTxt);
		Assert.assertEquals("Hours",cntdwnHrTxt);
		Assert.assertEquals("Minutes",cntdwnMinTxt);
		Assert.assertEquals("Seconds",cntdwnSecTxt);
		Assert.assertEquals("Game started",cntdwnEndTxt);
		Assert.assertEquals("true",cntdwnTime);
		Assert.assertEquals("true",cntdwnDays);
		Assert.assertEquals("/content/dam/sling-tv/misc/slinglogo-125x50.png",imgPath);
		Assert.assertEquals("Game Desc",gameDesc);
		Assert.assertEquals("countdown123",cntdwnclkId);
				
	}
	
	@Test
	public void Test_NotNullComp() {

		Resource resource = context.resourceResolver().getResource(COUNTDOWN_CLOCK_COMPONENT_PATH);
		countdownClock = resource.adaptTo(CountdownClockModel.class);
		Assert.assertNotNull(countdownClock);
		
		

	}
	
	/**
	 * Gets the formatted date irrespective of timezone
	 * @return the countdown start formated dates
	 * @throws InvalidDateException the invalid date exception
	 */
	
	public String getCountdownStartDate() throws InvalidDateException {
		String dateArr[] = this.strJsonDate.split("T");		
		return dateArr[0]+" "+StringUtils.substring(dateArr[1], 0, 8);
	}


}