package com.sling.tv.core.beans;

/**
 * This class is Bean class used to store title and link for Language Page
 * 
 * @author deou
 *
 */
public class LanguageBean {

    private String langTitle;
    private String langLink;

    public String getLangLink() {
        return langLink;
    }

    public void setLangLink(final String langLink) {
        this.langLink = langLink;
    }

    public String getLangTitle() {
        return langTitle;
    }

    public void setLangTitle(final String langTitle) {
        this.langTitle = langTitle;
    }

}
