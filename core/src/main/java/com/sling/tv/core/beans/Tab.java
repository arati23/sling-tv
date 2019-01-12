package com.sling.tv.core.beans;

import com.day.cq.commons.jcr.JcrUtil;

/**
 * DTO object for the responsive tabs component
 */
public class Tab {

    private String tabTitle;
    private String tabIcon;
    private String tabSelected;
    private String hideTab;
    private String tabUrl;

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public String getTabId() {
        return JcrUtil.createValidName(this.tabTitle);
    }

	public String getTabIcon() {
		return tabIcon;
	}

	public void setTabIcon(String tabIcon) {
		this.tabIcon = tabIcon;
	}

	public String getTabUrl() {
		return tabUrl;
	}

	public void setTabUrl(String tabUrl) {
		this.tabUrl = tabUrl;
	}


	public String getTabSelected() {
		return tabSelected;
	}

	public void setTabSelected(String tabSelected) {
		this.tabSelected = tabSelected;
	}

	public String getHideTab() {
		return hideTab;
	}

	public void setHideTab(String hideTab) {
		this.hideTab = hideTab;
	}

}
