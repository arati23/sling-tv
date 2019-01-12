
package com.sling.tv.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.sling.tv.core.utils.LinkCheckerUtil;

/**
 *
 * This Sling Model will populate the Footer links.
 *
 * @author deou
 *
 */
//@Model(adaptables = { Resource.class, SlingHttpServletRequest.class })
 @Model(adaptables = Resource.class)
public class NavigationComponentModel {

	/** The log. */
	private static Logger LOG = LoggerFactory.getLogger(NavigationComponentModel.class);
	
	LinkCheckerUtil lcu = new LinkCheckerUtil();

	private Page currentPage;

	Resource thisResource;

//	/** The xssapi. */
//	@Inject
////	@Optional /* Required for JUnit execution.*/
//	private XSSAPI xssapi;

	/** The footer nav path. */
	@Inject
	@Optional
	
	private String footerNavPath;

	/** The header nav path. */
	@Inject
	@Optional
	
	private String headerNavPath;

	/** The region text links. */
	@Inject
	@Optional
	
	private List<Resource> regionTextLinks;

	/** The New region text links. */
	@Inject
	@Optional
	private List<Resource> regionTextLinksNew;

	/** The region logo links. */
	@Inject
	@Optional
	private List<Resource> regionLogoLinks;

	/** The header. */
	private NavigationPage header;

	/** The footer. */
	private NavigationPage footer;

	/** The region text links list. */
	private List<RegionTextLinkModel> regionTextLinksList;

	/** The new region text links list. */
	private List<RegionTextLinkModel> regionTextLinksListNew;

	/** The region logo links list. */
	private List<RegionLogoLinkModel> regionLogoLinksList;

	/** F13367 - Header ID. **/
	@Inject
	@Optional
	private String headerId;

	/** F13367 - Header Type. **/
	@Inject
	@Optional
	private String headerType;

	/** F13367 - New Header ID **/
	@Inject
	@Optional
	private String newHeaderId;

	/** F13367 - Logo Image Path **/
	@Inject
	@Optional
	
	private String logoImage;

	/** F13367 - Logo Alt Text **/
	@Inject
	@Optional
	
	private String logoAltText;

	/** F13367 - Header Text **/
	@Inject
	@Optional
	
	private String headerText;

	/** F13367 - Hide Language Selection **/
	@Inject
	@Optional
	
	private String hideLanguageSel;

	/** F13367 - Logo Link **/
	@Inject
	@Optional
	
	private String logoLink;

	/** F13367 - Language Title **/
	@Inject
	@Optional
	
	private String languageTitle;

	/** F13367 - Member Text **/
	@Inject
	@Optional
	
	private String memberText;

	/** F13367 - Sign Out Text **/
	@Inject
	@Optional
	
	private String signOutText;

	/** F13367 - Sign Out Text New **/
	@Inject
	@Optional
	
	private String signOutTextNew;

	/** F13367 - My Account Label Text New **/
	@Inject
	@Optional
	
	private String myAccountLabelTextNew;

	/** F13367 - nclude Sub Nav **/
	@Inject
	@Optional
	
	private String includeSubNavComponent;

	/** F13367 - Sign In Text New **/
	@Inject
	@Optional
	private String signInTextNew;

	/** F13367 - Logo Image New **/
	@Inject
	@Optional
	
	private String logoImageNew;

	/** F13367 - Hide Header Nav **/
	@Inject
	@Optional
	private String hideHeaderNav;
	
	
	@Inject
	@Optional
	private String signInLink;
	
	
	@Inject
	@Optional
	private String logoLinkNew;
	
	
	@Inject
	@Optional
	private String signInLinkNew;
	
	@Inject
	@Optional
	private String myAccountLink;
	
	
	@Inject
	@Optional
	private String signOutLink;

	
	@Inject
	@Optional
	private String signOutLinkNew;
	
	
	@Inject
	@Optional
	
	private String footerId;
	
	@Inject
	@Optional
	
	private String themeType;
	
	
	@Inject
	@Optional
	private String socialLinkComponent;
	
	
	private String showReferAFriend;
	
	
	public String getThemeType() {
		
		return this.themeType;
	}
	
	public String getSocialLinkComponent() {
		
		return this.socialLinkComponent;
	}
	
	public String getFooterId() {
		
		return this.footerId;
	}
	
	public String getShowReferAFriend() {
		
		return this.showReferAFriend;
	}
	
	
	public String getSignInLinkNew()
	{
		return lcu.absoluteUrl(this.signInLinkNew);
	}
	
	
	public String getSignInLink()
	{
		return lcu.absoluteUrl(this.signInLink);
	}
	

	public String getLogoLinkNew() {
		return lcu.absoluteUrl(logoLinkNew);
	}


	public String getMyAccountLink() {
		return lcu.absoluteUrl(myAccountLink);
	}


	public String getSignOutLink() {
		return lcu.absoluteUrl(signOutLink);
	}


	public String getSignOutLinkNew() {
		return lcu.absoluteUrl(signOutLinkNew);
	}


	/**
	 * This method gets the Footer Navigation values from the navigation pages.
	 *
	 */
	
	
	public NavigationComponentModel(Resource r)
	{
		this.thisResource = r;
		
	}
	
	

	
	@PostConstruct
	public final void init() {
		ResourceResolver resourceResolver = thisResource.getResourceResolver();
		PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
		this.currentPage = pageManager.getContainingPage(thisResource);
		
		
		if (null != currentPage)
		{
			this.showReferAFriend = currentPage.getProperties().get("showReferAFriend", (String) null);
			if (StringUtils.isNotEmpty(headerNavPath)) {
				setHeader(buildLinks(headerNavPath));
			}

			if (StringUtils.isNotEmpty(footerNavPath)) {
				setFooter(buildLinks(footerNavPath));
			}

			if (null != regionTextLinks && !regionTextLinks.isEmpty()) {
				
				regionTextLinksList = buildRegionTextLinks();
			}
			if (null != regionTextLinksNew) {
				
				regionTextLinksListNew = buildRegionTextLinksNew();
			}

			if (null != regionLogoLinks ) {
				this.regionLogoLinksList = buildRegionLogoLinks();
			}			
		}
		
		
		




	}

	/**
	 * Builds the links.
	 *
	 * @param navigationStructurePath
	 *            the navigation structure path
	 * @return the navigation page
	 */
	private NavigationPage buildLinks(final String navigationStructurePath) {
		NavigationPage navigationPage = null;

		if (StringUtils.isNotEmpty(navigationStructurePath)) {
			final Page page = currentPage.getPageManager().getContainingPage(navigationStructurePath);
			if (page != null && page.getContentResource() != null) {
				navigationPage = page.getContentResource().adaptTo(NavigationPage.class);
				final Iterator<Page> leftChildren = page.listChildren();
				buildNavigationTree(navigationPage, leftChildren);
			}
		}
		return navigationPage;
	}

	/**
	 * Builds the navigation tree.
	 *
	 * @param page
	 *            the page
	 * @param children
	 *            Method to recursively populate the navigation tree of pages.
	 */
	private void buildNavigationTree(final NavigationPage page, final Iterator<Page> children) {
		while (children.hasNext()) {
			final Page child = children.next();
			if (child != null && child.getContentResource() != null) {
				final NavigationPage subPage = child.getContentResource().adaptTo(NavigationPage.class);
				if (subPage != null) {
					subPage.setUniqueId(child.getName());
					buildNavigationTree(subPage, child.listChildren());

					page.getChildren().add(subPage);
				}
			}
		}
	}

	/**
	 * Gets the header.
	 *
	 * @return the header
	 */
	public NavigationPage getHeader() {
		return header;
	}

	/**
	 * Sets the header.
	 *
	 * @param header
	 *            the new header
	 */
	public void setHeader(final NavigationPage header) {
		this.header = header;
	}

	/**
	 * Gets the footer.
	 *
	 * @return the footer
	 */
	public NavigationPage getFooter() {
		return footer;
	}

	/**
	 * Sets the footer.
	 *
	 * @param footer
	 *            the new footer
	 */
	public void setFooter(final NavigationPage footer) {
		this.footer = footer;
	}

	/** F13367 - Getters for the new fields **/

	public String getHeaderId() {
		return this.headerId;
	}

	public String getHeaderType() {
		return this.headerType;
	}

	public String getNewHeaderId() {
		return this.newHeaderId;
	}

	public String getLogoImage() {
		return this.logoImage;
	}

	public String getLogoAltText() {
		return this.logoAltText;
	}

	public String getHeaderText() {
		return this.headerText;
	}

	public String getHideLanguageSel() {
		return this.hideLanguageSel;
	}

	public String getLogoLink() {
		return lcu.absoluteUrl(this.logoLink);
	}

	// new
	public String getLanguageTitle() {
		return this.languageTitle;
	}

	public String getMemberText() {
		return this.memberText;
	}

	public String getSignOutText() {
		return this.signOutText;
	}

	public String getSignOutTextNew() {
		return this.signOutTextNew;
	}

	public String getMyAccountLabelTextNew() {
		return this.myAccountLabelTextNew;
	}

	public String getIncludeSubNavComponent() {
		return this.includeSubNavComponent;
	}

	public String getSignInTextNew() {
		return this.signInTextNew;
	}

	public String getLogoImageNew() {
		return this.logoImageNew;
	}

	public String getHideHeaderNav() {
		return this.hideHeaderNav;
	}

	/** F13367 - End of Getters for the new fields **/

	/**
	 * Builds the region text links.
	 *
	 * @param regionTextLinks2
	 *            the region text links
	 */
	private List<RegionTextLinkModel> buildRegionTextLinks() {

		List<RegionTextLinkModel> tRegionTextLinks = new ArrayList<>();

		for (final Resource r : this.regionTextLinks) {
			tRegionTextLinks.add(r.adaptTo(RegionTextLinkModel.class));
		}

		return tRegionTextLinks;

	}

	/**
	 * Builds the new region text links.
	 *
	 * @param new
	 *            regionTextLinks2 the new region text links
	 */
	private List<RegionTextLinkModel> buildRegionTextLinksNew() {
		List<RegionTextLinkModel> tRegionTextLinksListNew = new ArrayList<>();
		
		for (final Resource r : this.regionTextLinksNew) {
			tRegionTextLinksListNew.add(r.adaptTo(RegionTextLinkModel.class));
		}
		return tRegionTextLinksListNew;
	}

	/**
	 * Builds the region logo links.
	 *
	 * @param regionLogoLinks
	 *            the region logo links 2
	 */
	private List<RegionLogoLinkModel> buildRegionLogoLinks() {
		List<RegionLogoLinkModel> tRegionLogoLinksList = new ArrayList<>();
		
		for (final Resource r : this.regionLogoLinks) {
			RegionLogoLinkModel rlm = r.adaptTo(RegionLogoLinkModel.class);
			tRegionLogoLinksList.add(rlm);
		}
		
		
		return tRegionLogoLinksList;
	}

	/**
	 * Gets the region text links list.
	 *
	 * @return the region text links list
	 */
	public List<RegionTextLinkModel> getRegionTextLinksList() {
		return regionTextLinksList;
	}

	/**
	 * Gets the new region text links list.
	 *
	 * @return the new region text links list
	 */
	public List<RegionTextLinkModel> getRegionTextLinksListNew() {
		return this.regionTextLinksListNew;
	}

	/**
	 * Gets the region logo links list.
	 *
	 * @return the region logo links list
	 */
	public List<RegionLogoLinkModel> getRegionLogoLinksList() {
		return regionLogoLinksList;
	}
	
	public String getHeaderNavPath() {
		return this.headerNavPath;
		
	}



}
