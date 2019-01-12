package com.sling.tv.core.beans;

import java.util.Date;


public class MatchInfo implements Comparable<MatchInfo> {
	
	private Date dateTime;
	
	private String dateStr;
	private String timeStr;
	private String channel;
	private String channelLink;
	private String matchUp;
	private String matchupMobile;
	private String matchDescriptionWithZip;
	private String matchDescriptionWithNoZip;
	
	public String getChannelLink() {
		return channelLink;
	}

	public void setChannelLink(String channelLink) {
		this.channelLink = channelLink;
	}

	public String getMatchDescriptionWithZip() {
		return matchDescriptionWithZip;
	}

	public void setMatchDescriptionWithZip(String matchDescriptionWithZip) {
		this.matchDescriptionWithZip = matchDescriptionWithZip;
	}

	public String getMatchDescriptionWithNoZip() {
		return matchDescriptionWithNoZip;
	}

	public void setMatchDescriptionWithNoZip(String matchDescriptionWithNoZip) {
		this.matchDescriptionWithNoZip = matchDescriptionWithNoZip;
	}

	public Date getDateTime() {
	    return dateTime;
	}

	public void setDateTime(Date datetime) {
		this.dateTime = datetime;
	}

	@Override
	public int compareTo(MatchInfo matchInfo) {
		return getDateTime().compareTo(matchInfo.getDateTime());
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getMatchUp() {
		return matchUp;
	}

	public void setMatchUp(String matchUp) {
		this.matchUp = matchUp;
	}

	public String getMatchupMobile() {
		return matchupMobile;
	}

	public void setMatchupMobile(String matchupMobile) {
		this.matchupMobile = matchupMobile;
	}

}
