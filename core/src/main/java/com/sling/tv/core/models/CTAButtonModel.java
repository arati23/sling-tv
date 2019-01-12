package com.sling.tv.core.models;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.sling.tv.core.SlingTvConstants;
import com.sling.tv.core.services.ReadOSGIConfigValues;
import com.sling.tv.core.utils.LinkCheckerUtil;

/**
 * 
 *
 * @author Dileep Muraleedharan
 *
 */
@Model(adaptables = { SlingHttpServletRequest.class,
		Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CTAButtonModel {

	private static Logger LOG = LoggerFactory.getLogger(CTAButtonModel.class);

	LinkCheckerUtil lcu = new LinkCheckerUtil();

	@Inject
	ReadOSGIConfigValues osgi;

	@Inject
	@Optional
	@Via("resource")
	private String destination;

	public String getDestination() {
		return destination;
	}

	@Inject
	@Via("resource")
	private String ctaURL;

	@Inject
	@Via("resource")
	private String offerDescription;

	@Inject
	@Via("resource")
	private String title;

	@Inject
	@Via("resource")
	private String offerDetailsLabel;

	@Inject
	@Via("resource")
	private String offerPublishedDate;

	@Inject
	@Via("resource")
	private String offerDetailsHeading;

	@Inject
	@Via("resource")
	private String offerDetailsDateAttr;

	@Inject
	@Via("resource")
	private String id;

	@Inject
	@Via("resource")
	private String ctaId;

	@Inject
	@Via("resource")
	private String buttonText;

	@Inject
	@Via("resource")
	private String ctaHeaderOptions;

	@Inject
	@Via("resource")
	private String alignment;

	@Inject
	@Via("resource")
	private String hideCta;

	@Inject
	@Via("resource")
	private String stickyMobile;

	@Inject
	@Via("resource")
	private String ctaBorder;

	@Inject
	@Via("resource")
	private String ctaOptions;

	@Inject
	@Via("resource")
	private String ctaWindowSelection;

	@Inject
	@Via("resource")
	private String caretOptions;

	public String getId() {
		return id;
	}

	public String getCtaId() {
		return ctaId;
	}

	public String getButtonText() {
		return buttonText;
	}

	public String getCtaHeaderOptions() {
		return ctaHeaderOptions;
	}

	public String getAlignment() {
		return alignment;
	}

	public String getHideCta() {
		return hideCta;
	}

	public String getStickyMobile() {
		return stickyMobile;
	}

	public String getCtaBorder() {
		return ctaBorder;
	}

	public String getCtaOptions() {
		return ctaOptions;
	}

	public String getCtaWindowSelection() {
		return ctaWindowSelection;
	}

	public String getCaretOptions() {
		return caretOptions;
	}

	public String getTitle() {
		return title;
	}

	public String getOfferDetailsLabel() {
		return offerDetailsLabel;
	}

	public String getOfferPublishedDate() {
		return offerPublishedDate;
	}

	public String getOfferDetailsHeading() {
		return offerDetailsHeading;
	}

	public String getOfferDetailsDateAttr() {
		return offerDetailsDateAttr;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	@Inject
	@Self
	private SlingHttpServletRequest request;

	@Inject
	private PageManager pageManager;

	@Inject
	ResourceResolver resourceResolver;

	@Inject
	private Page currentPage;

	@Inject
	@Optional
	@Via("resource")
	@Named("targetURL")
	private String targetUrl;

	@Inject
	@Optional
	@Named("classification")
	@Via("resource")
	private String ctaClassification;

	@Inject
	@Optional
	@Named(SlingTvConstants.CTA_OVERRIDE_OFFER)
	@Via("resource")
	private String overrideOffer;

	@Inject
	@Optional
	@Named(SlingTvConstants.CTA_OVERRIDE_DISCLAIMER)
	@Via("resource")
	private String overrideDisclaimer;

	@Inject
	@Optional
	@Named("overrideCTAPath")
	@Via("resource")
	private String ctaPath;
	@Inject
	@Optional
	@Via("resource")
	private String cartFlow;

	@Inject
	@Optional
	@Via("resource")
	private String cartStep;

	@Inject
	@Optional
	@Via("resource")
	private String deviceType;

	@Inject
	@Optional
	@Via("resource")
	private String planId;

	@Inject
	@Optional
	@Via("resource")
	private String offerId;

	@Inject
	@Optional
	@Via("resource")
	private String sb;

	@Inject
	@Optional
	@Via("resource")
	private String ats;

	@Inject
	@Optional
	@Via("resource")
	private String sp;

	@Inject
	@Optional
	@Via("resource")
	@Named("raf")
	private String Raf;

	@Inject
	@Optional
	@Via("resource")
	@Named("dsc")
	private String dsc;

	@Inject
	@Optional
	@Via("resource")
	private String text;

	private String affiliate = "rafFlow";
	private String hd = "1";

	public void setOfferDetailsDateAttr(String offerDetailsDateAttr) {
		this.offerDetailsDateAttr = offerDetailsDateAttr;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setOfferDetailsLabel(String offerDetailsLabel) {
		this.offerDetailsLabel = offerDetailsLabel;
	}

	public void setOfferPublishedDate(String offerPublishedDate) {
		this.offerPublishedDate = offerPublishedDate;
	}

	public void setOfferDetailsHeading(String offerDetailsHeading) {
		this.offerDetailsHeading = offerDetailsHeading;
	}

	/**
	 * The hero text bean.
	 * 
	 * @throws RepositoryException
	 */

	@PostConstruct
	public final void init() {

		if (!StringUtils.isNotEmpty(destination)) {

			destination = "";

		}
		if (StringUtils.isNotEmpty(destination) && destination.equals("old-cart")) {

			targetUrl = "/signup";

		} else if (StringUtils.isNotEmpty(destination) && destination.equals("new-cart")) {

			if (osgi != null) {

				targetUrl = osgi.getOsgiConfigValue("cart.url");
			}

		}

		
		if (targetUrl != null && !destination.equals("link")) {
			final String queryParams = getQueryParams();
			if (targetUrl.contains("?")) {
				ctaURL = StringUtils.isNotEmpty(queryParams) ? targetUrl + "&" + queryParams : targetUrl;
			} else {
				ctaURL = StringUtils.isNotEmpty(queryParams) ? targetUrl + "?" + queryParams : targetUrl;
			}
		}else if(targetUrl != null && destination.equals("link")) {
        	
			ctaURL = targetUrl;
        }

		if ("no".equalsIgnoreCase(overrideOffer)) {
			final Page offerPage = pageManager.getPage(ctaPath); 

			if (offerPage != null) {
				
				LOG.info("CTA Page Path is" + offerPage.getPath());
				
				ValueMap pageProperties = offerPage.getProperties();

				if (pageProperties.containsKey("offerDetailsDateAttr")) {
					
					setOfferDetailsDateAttr(pageProperties.get("offerDetailsDateAttr").toString());
				}
				if (pageProperties.containsKey("jcr:title")) {
					
					setTitle(pageProperties.get("jcr:title").toString());
				}
				if (pageProperties.containsKey("offerDetailsLabel")) {
					
					setOfferDetailsLabel(pageProperties.get("offerDetailsLabel").toString());
				}
				if (pageProperties.containsKey("offerPublishedDate")) {
					
					setOfferPublishedDate(pageProperties.get("offerPublishedDate").toString());
				}
				if (pageProperties.containsKey("offerDetailsHeading")) {
					
					setOfferDetailsHeading(pageProperties.get("offerDetailsHeading").toString());
				}
				final Resource offerResource = offerPage.getContentResource().getChild("par/rich_text");
				
				if (offerResource != null) {
					
					offerDescription = (String) offerResource.getValueMap().get(SlingTvConstants.CTA_OFFER_DESCRIPTION);
				}
			} // offer page null check
		} else {
			offerDescription = text;
		}

		LOG.debug("CTA URL {}", ctaURL);

	}

	private String getQueryParams()  {

		String locale = "en";
		Locale localeLan=currentPage.getLanguage(false);
	        if(localeLan!=null) {
	        	locale = localeLan.getLanguage();
	        }
		String queryParams = StringUtils.EMPTY;

		if (!queryParams.contains(SlingTvConstants.CTA_LOCALE)) {
			queryParams = addQueryParam(SlingTvConstants.CTA_LOCALE, locale, queryParams);
		}
		sp = StringUtils.isNotBlank(sp) ? sp : StringUtils.replace(currentPage.getName(), "-", "_");
		queryParams = addQueryParam(SlingTvConstants.CTA_CLASSIFICATION, ctaClassification, queryParams);
		queryParams = addQueryParam("flow", cartFlow, queryParams);
		queryParams = addQueryParam("step", cartStep, queryParams);
		queryParams = addQueryParam("device", deviceType, queryParams);
		queryParams = addQueryParam("plan", planId, queryParams);
		queryParams = addQueryParam("offer_id", offerId, queryParams);
		queryParams = addQueryParam("sb", sb, queryParams);
        queryParams = addQueryParam("ats", ats, queryParams);

		if (targetUrl != null) {
			 if (checkTargetUrl()) {
				queryParams = addQueryParam("sp", sp, queryParams);
			}
		}
		
/*		ValueMap currentPageProp=currentPage.getProperties();
		
		if(currentPageProp.containsKey("checkRaf")) {
			Raf=currentPageProp.get("checkRaf").toString();
		}
*/
	/*	if (Raf != null && targetUrl != null) {
			if (Raf.equals("true") && checkTargetUrl()) {
				LOG.info("inside the Raf is true flow");
				queryParams = addQueryParam("affiliate", affiliate, queryParams);
			}

		}
*/
		queryParams = getDesclaimerDetails(queryParams);

		if (queryParams.length() > 1) {
			queryParams = queryParams.substring(0, queryParams.length() - 1);
		}
		return queryParams;

	}
	
	private boolean checkTargetUrl() {
		
	boolean flag=false;
	
	if(targetUrl.contains("/signup") || targetUrl.contains("cart.q.sling.com") || targetUrl.contains("cart.b.sling.com") || targetUrl.contains("cart.sling.com")) {
		
		flag=true;
	}
	
	return flag;
		
		
	}

	private String getDesclaimerDetails(String queryParams) {

		if ("yes".equalsIgnoreCase(overrideDisclaimer)) {
			if (dsc != null) {
				final Page disclaimerPage = pageManager.getPage(dsc);
				String disclaimerName = "";
				if (disclaimerPage != null) { // if disclaimerPage is null no need to process further
					Resource resourceDisclaimer = resourceResolver.getResource(dsc);
					Node nodeDisclaimer = resourceDisclaimer.adaptTo(Node.class);
					try {
						disclaimerName = nodeDisclaimer.getName();
						LOG.info("CTA Disclaimer Text Path is" + nodeDisclaimer.getName() + "----"
								+ disclaimerPage.getPath());
					} catch (RepositoryException e) {
						LOG.error("There is some exception while reading disclaimer path {}", e);
					}

					queryParams = addQueryParam("dsc", disclaimerName, queryParams);
				}
			}
		} else if ("no".equalsIgnoreCase(overrideDisclaimer)) {
			if (checkTargetUrl()) {
				queryParams = addQueryParam("hd", hd, queryParams);
			}
		}

		return queryParams;

	}

	private String addQueryParam(final String queryParamName, final String queryParamValue, final String queryParams) {
		if (StringUtils.isNotEmpty(queryParamValue)) {
			final StringBuilder queryParam = new StringBuilder(queryParams);
			queryParam.append(queryParamName);
			queryParam.append("=");
			queryParam.append(queryParamValue);
			queryParam.append("&");
			return queryParam.toString();
		}
		return queryParams;

	}

	public String getCtaURL() {
		return lcu.absoluteUrl(this.ctaURL);
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public String getCtaClassification() {
		return ctaClassification;
	}

	public String getOverrideOffer() {
		return overrideOffer;
	}

	public String getOverrideDisclaimer() {
		return overrideDisclaimer;
	}

	public String getCtaPath() {
		return ctaPath;
	}

	public String getCartFlow() {
		return cartFlow;
	}

	public String getCartStep() {
		return cartStep;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public String getPlanId() {
		return planId;
	}

	public String getOfferId() {
		return offerId;
	}

	public String getSb() {
		return sb;
	}

	public String getAts() {
		return ats;
	}

	public String getSp() {
		return sp;
	}

	public String getRaf() {
		return Raf;
	}

	public String getDsc() {
		return dsc;
	}

	public String getText() {
		return text;
	}

	public String getAffiliate() {
		return affiliate;
	}

	public String getHd() {
		return hd;
	}
}