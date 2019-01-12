package com.sling.tv.core.beans;

import java.util.List;


public class TabbedPageBean {

    private String tabbedPageTitle;
    private String tabbedPageCategoryTitle;

    private List<SubCategoryPageBean> subCategoryPages;

    public String getTabbedPageTitle() {
        return tabbedPageTitle;
    }
    public void setTabbedPageTitle(String tabbedPageTitle) {
        this.tabbedPageTitle = tabbedPageTitle;
    }
    public String getTabbedPageCategoryTitle() {
        return tabbedPageCategoryTitle;
    }
    public void setTabbedPageCategoryTitle(String tabbedPageCategoryTitle) {
        this.tabbedPageCategoryTitle = tabbedPageCategoryTitle;
    }
    public List<SubCategoryPageBean> getSubCategoryPages() {
        return subCategoryPages;
    }
    public void setSubCategoryPages(List<SubCategoryPageBean> subCategoryPages) {
        this.subCategoryPages = subCategoryPages;
    }
}
