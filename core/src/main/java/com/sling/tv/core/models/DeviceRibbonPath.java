
package com.sling.tv.core.models;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;

import com.day.cq.wcm.api.Page;

/**
 *
 * This Sling Model will populate the Device pages.
 *
 * @author Hafeez
 *
 */
@Model(adaptables = SlingHttpServletRequest.class)
public class DeviceRibbonPath {

    @Inject
    private Page currentPage;

    @Inject
    @Optional
    @Via("resource")
    private String deviceRibbonPath;

	private DevicePage deviceList;

    /**
     * This method gets the Device Ribbon Navigation values from the Device Ribbon pages.
     *
     */
    @PostConstruct
    public final void init() {

        if (StringUtils.isNotEmpty(deviceRibbonPath)) {
        	deviceList = buildDevicePath(deviceRibbonPath);
        }

    }

    private DevicePage buildDevicePath(final String navigationStructurePath) {
    
    	DevicePage devicePage = null;

        if (StringUtils.isNotEmpty(navigationStructurePath)) {
            final Page page = currentPage.getPageManager().getContainingPage(navigationStructurePath);
            if (page != null && page.getContentResource() != null) {
            	devicePage = page.getContentResource().adaptTo(DevicePage.class);
                final Iterator<Page> leftChildren = page.listChildren();
                buildDeviceTree(devicePage, leftChildren);
            }
        }
        return devicePage;
    }

    /**
     * @param page
     * @param children
     *
     *            Method to recursively populate the navigation tree of pages.
     */
    private void buildDeviceTree(final DevicePage page, final Iterator<Page> children) {
        while (children.hasNext()) {
            final Page child = children.next();
            if (child != null && child.getContentResource() != null) {
                final DevicePage subPage = child.getContentResource().adaptTo(DevicePage.class);
                if (subPage != null) {
                	buildDeviceTree(subPage, child.listChildren());
                    page.getChildren().add(subPage);
                }
            }
        }
    }

	public DevicePage getDeviceList() {
		return deviceList;
	}
	
	public String getDeviceRibbonPath() {
		return deviceRibbonPath;
	}
	
    /**
     * @return
     */	
    
}
