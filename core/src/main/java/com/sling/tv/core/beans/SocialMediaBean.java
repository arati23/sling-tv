package com.sling.tv.core.beans;

/**
 *
 * This class is used to store Social Media components properties
 * 
 * @author deou
 *
 */

public class SocialMediaBean {

    private String icon;
    private String link;
    private String windowOption;

    public final String getIcon() {
        return icon;
    }

    public final void setIcon(final String icon) {
        this.icon = icon;
    }

    public final String getLink() {
        return link;
    }

    public final void setLink(final String link) {
        this.link = link;
    }

    public String getWindowOption() {
        return windowOption;
    }

    public void setWindowOption(final String windowOption) {
        this.windowOption = windowOption;
    }

}
