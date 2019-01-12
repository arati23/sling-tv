package com.sling.tv.core.utils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.day.cq.commons.inherit.HierarchyNodeInheritanceValueMap;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

/**
 * This utility class is used to get the footer and header path
 * 
 * @author deou
 *
 */
@Model(adaptables = Resource.class)
public class HeaderFooterUtils {

    public static final int LOCALE_LEVEL = 3;

    public static final String HEADER_PATH = "header";
    public static final String NEW_HEADER_PATH = "newHeader";
    public static final String FOOTER_PATH = "footer";
    public static final String PROP_OVERRIDE_FOOTER = "overridefooter";
    public static final String PROP_OVERRIDE_HEADER = "overrideheader";
	public static final String PROP_OVERRIDE_NEW_HEADER = "overridenewheader";
	

    @Self
    private Resource resource;

    @Inject
    private PageManager pageManager;

    @Inject
    @Optional
    private HierarchyNodeInheritanceValueMap pageProperties;

    @Inject
    @Optional
    private String overrideFooter;

    @Inject
    @Optional
    private String overrideHeader;

    @Inject
    @Optional
    private String overrideNewHeader;

    private String headerPath;



    private String newHeaderPath;
    private String footerPath;

    @PostConstruct
    public final void init() {

        final Page currentPage = pageManager.getContainingPage(resource);

        footerPath = getFooterPath(currentPage);
        if (footerPath == null) {
            footerPath = currentPage.getContentResource().getPath() + "/" + FOOTER_PATH;
        }

        headerPath = getHeaderPath(currentPage);
        if (headerPath == null) {
            headerPath = currentPage.getContentResource().getPath() + "/" + HEADER_PATH;
        }
        newHeaderPath = getNewHeaderPath(currentPage);
        if(newHeaderPath == null){
            newHeaderPath = currentPage.getContentResource().getPath() + "/" + NEW_HEADER_PATH;
        }
    }

    /**
     * Get the path to where the header should be included.
     *
     * @param page
     *            a page to check.
     * @return recurses up the page tree to find where to store header content. can return null, in which case the
     *         caller should include it at the current page.
     */

    private String getNewHeaderPath(final Page page) {
        if (page == null) {
            return null;
        } else if (page.getDepth() <= LOCALE_LEVEL) {
            return page.getContentResource().getPath() + "/" + NEW_HEADER_PATH;
        }
 final boolean isNewHeaderOverride = page.getProperties().get(PROP_OVERRIDE_NEW_HEADER, false);
		 if (isNewHeaderOverride) {
            newHeaderPath = page.getContentResource().getPath() + "/" + NEW_HEADER_PATH;
        } else {
            newHeaderPath = getNewHeaderPath(page.getParent());
        }

        
        return newHeaderPath;
    }
    private String getHeaderPath(final Page page) {
        if (page == null) {
            // if page is null, return null and let caller deal with it
            return null;
        } else if (page.getDepth() <= LOCALE_LEVEL) {
            // if we have recursed up to locale level or above, then use this path.
            return page.getContentResource().getPath() + "/" + HEADER_PATH;
        }

        // else check if the page has override header checked, and either use that page or recurse up one level
        final boolean isHeaderOverride = page.getProperties().get(PROP_OVERRIDE_HEADER, false);
        if (isHeaderOverride) {
            headerPath = page.getContentResource().getPath() + "/" + HEADER_PATH;
        } 
        else {
            headerPath = getHeaderPath(page.getParent());
        } 
		
		
		
		
        
        return headerPath;

    }

    /**
     * Get the path to where the footer should be included.
     *
     * @param page
     *            a page to check.
     * @return recurses up the page tree to find where to store footer content. can return null, in whcih case the
     *         caller should include it at the current page.
     */
    private String getFooterPath(final Page page) {
        if (page == null) {
            // if page is null, return null and let caller deal with it
            return null;
        } else if (page.getDepth() <= LOCALE_LEVEL) {
            // if we have recursed up to locale level or above, then use this path.
            return page.getContentResource().getPath() + "/" + FOOTER_PATH;
        }

        // else check if the page has override footer checked, and either use that page or recurse up one level
        final boolean isFooterOverride = page.getProperties().get(PROP_OVERRIDE_FOOTER, false);
        if (isFooterOverride) {
            footerPath = page.getContentResource().getPath() + "/" + FOOTER_PATH;
        } else {
            footerPath = getFooterPath(page.getParent());
        }

        return footerPath;
    }

    public String getHeaderPath() {
        return headerPath;
    }
	

    public String getNewHeaderPath() {
        return newHeaderPath;
    }

    public final String getFooterPath() {
        return footerPath;
    }

    public final void setFooterPath(final String footerPath) {
        this.footerPath = footerPath;
    }

}
