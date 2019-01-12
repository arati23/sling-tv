package com.sling.tv.core.models;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.sling.tv.core.utils.LinkCheckerUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestParameterMap;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.inherit.HierarchyNodeInheritanceValueMap;
import com.day.cq.commons.inherit.InheritanceValueMap;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.sling.tv.core.SlingTvConstants;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

/**
 * This class is used to create CTA URL based on external , CTA and page
 * properties parameter
 *
 * @author deou
 *
 */
@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CTAModel {

    private static Logger LOG = LoggerFactory.getLogger(CTAModel.class);

    LinkCheckerUtil lcu = new LinkCheckerUtil();

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
    ResourceResolver resourceResolver ;

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
    private String  ctaChkPreselectParent = StringUtils.EMPTY;
    private String  ctaChkPreselectForCurrentPage = StringUtils.EMPTY;
    private String  ctaAtsParent = StringUtils.EMPTY;
    private String  ctaSbParent = StringUtils.EMPTY;
    private String  ctaChkPreselectChild = StringUtils.EMPTY;
    private String  ctaAtsChild = StringUtils.EMPTY;
    private String  ctaSbChild = StringUtils.EMPTY;

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

    /** The hero text bean.
     * @throws RepositoryException */

    @PostConstruct
    public final void init() throws RepositoryException {

        final InheritanceValueMap pagePropertiesMap = new HierarchyNodeInheritanceValueMap(
                currentPage.getContentResource());
        // final RequestParameterMap slingRequestParams =
        // request.getRequestParameterMap();

        final String queryParams = getQueryParams(pagePropertiesMap);
        if (targetUrl != null) {
            if (targetUrl.contains("?")) {
                ctaURL = StringUtils.isNotEmpty(queryParams) ? targetUrl + "&" + queryParams : targetUrl;
            } else {
                ctaURL = StringUtils.isNotEmpty(queryParams) ? targetUrl + "?" + queryParams : targetUrl;
            }
        }

        if ("no".equalsIgnoreCase(overrideOffer)) {
            final Page offerPage = pageManager.getPage(ctaPath); // 
            
            if(offerPage!=null) { //if the page object is null then no need to check further..
	            LOG.info("CTA Page Path is" + offerPage.getPath());
	            ValueMap pageProperties = offerPage.getProperties();
	            // title = pageProperties.get("jcr:title").toString();
	            // offerDetailsLabel =
	            // pageProperties.get("offerDetailsLabel").toString();
	            // offerPublishedDate =
	            // pageProperties.get("offerPublishedDate").toString();
	            // offerDetailsHeading =
	            // pageProperties.get("offerDetailsHeading").toString();	
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
            } //offer page null check
        } else {
            offerDescription = text;
        }

        LOG.debug("CTA URL {}", ctaURL);


    }

    private String getQueryParams(final ValueMap pagePropertiesMap) throws RepositoryException {

        Page parentPageObj = currentPage.getParent();
        Page currentPageObj = currentPage;
        Page currentPagePreSelectObj = currentPage;
        //The condition will not execute for experience fragment
        if(currentPage.getDepth()>4 && !currentPage.getPath().contains("/experience-fragments/"))
        {
            parentPageObj = currentPage.getParent(2);
            currentPageObj = currentPage.getParent(1);
        }
        ValueMap parentPageProperties = parentPageObj.getProperties();
        ValueMap childPageProperties = currentPageObj.getProperties();
        ValueMap currentPagePreSelectOptionCheck = currentPagePreSelectObj.getProperties();

        if(parentPageProperties.containsKey("disablePreselect")){
            ctaChkPreselectParent = parentPageProperties.get("disablePreselect").toString();
        }
        if(parentPageProperties.containsKey("disableAts")){
            ctaAtsParent = parentPageProperties.get("disableAts").toString();
        }
        if(parentPageProperties.containsKey("disableSb")){
            ctaSbParent = parentPageProperties.get("disableSb").toString();
        }
        if(childPageProperties.containsKey("disablePreselect")){
            ctaChkPreselectChild = childPageProperties.get("disablePreselect").toString();
        }
        if(childPageProperties.containsKey("disableAts")){
            ctaAtsChild = childPageProperties.get("disableAts").toString();
        }
        if(childPageProperties.containsKey("disableSb")){
            ctaSbChild = childPageProperties.get("disableSb").toString();
        }
        if(currentPagePreSelectOptionCheck.containsKey("enablePreselectForThisPage")){
            ctaChkPreselectForCurrentPage = currentPagePreSelectOptionCheck.get("enablePreselectForThisPage").toString();
        }

        final String pageClassification = (String) pagePropertiesMap.getOrDefault(SlingTvConstants.CTA_CLASSIFICATION,
                StringUtils.EMPTY);
        final String pageDevicePartner = (String) pagePropertiesMap.getOrDefault("devicePartner", StringUtils.EMPTY);
        final String pageSalesPartner = (String) pagePropertiesMap.getOrDefault("salesChannelPartner",
                StringUtils.EMPTY);

        final String classification = StringUtils.isNotEmpty(ctaClassification) ? ctaClassification
                : pageClassification;

        final String locale = currentPage.getLanguage(false).getLanguage();
        String queryParams = StringUtils.EMPTY;
        if (!queryParams.contains(SlingTvConstants.CTA_DEVICE_PARTNER)) {
            queryParams = addQueryParam(SlingTvConstants.CTA_DEVICE_PARTNER, pageDevicePartner, queryParams);
        }
        if (!queryParams.contains(SlingTvConstants.CTA_SALES_CHANNEL_PARTNER)) {
            queryParams = addQueryParam(SlingTvConstants.CTA_SALES_CHANNEL_PARTNER, pageSalesPartner, queryParams);
        }
        if (!queryParams.contains(SlingTvConstants.CTA_LOCALE)) {
            queryParams = addQueryParam(SlingTvConstants.CTA_LOCALE, locale, queryParams);
        }
        sp = StringUtils.isNotBlank(sp) ? sp : StringUtils.replace(currentPage.getName(), "-", "_");
        queryParams = addQueryParam(SlingTvConstants.CTA_CLASSIFICATION, classification, queryParams);
        queryParams = addQueryParam("flow", cartFlow, queryParams);
        queryParams = addQueryParam("step", cartStep, queryParams);
        queryParams = addQueryParam("device", deviceType, queryParams);
        queryParams = addQueryParam("plan", planId, queryParams);
        queryParams = addQueryParam("offer_id", offerId, queryParams);
        if(ctaChkPreselectParent.isEmpty() && ctaChkPreselectChild.isEmpty()){
            queryParams = addQueryParam("sb", sb, queryParams);
            queryParams = addQueryParam("ats", ats, queryParams);
        }
        if(!ctaChkPreselectForCurrentPage.isEmpty()){
            queryParams = addQueryParam("sb", sb, queryParams);
            queryParams = addQueryParam("ats", ats, queryParams);
        }
        if(!ctaChkPreselectParent.isEmpty() && ctaChkPreselectChild.isEmpty())
        {
            if(!ctaAtsParent.isEmpty())
            {
                if(ctaAtsParent.contains("*"))
                {

                }
                else if(ats != null && ctaAtsParent.contains(",") && ats.contains(",") ) {
                    String[] AtsParent = ctaAtsParent.split(",");

                    for (String strAtsVal: AtsParent) {

                        if(ats.contains(strAtsVal.trim())){
                            ats =ats.replace(strAtsVal,"").trim();
                        }
                    }
                    String[] AtsIds = ats.split(",");
                    if(AtsIds.length >0) {
                        String atsStr = StringUtils.EMPTY;
                        for (String strAtsIds: AtsIds) {
                            if(strAtsIds.length() != 0 ) {
                                if(atsStr.length() ==0)
                                {
                                    atsStr = strAtsIds.trim();
                                }
                                else
                                {
                                    atsStr = atsStr.trim() + "," + strAtsIds.trim();
                                }
                            }
                        }
                        ats = atsStr.trim();
                        queryParams = addQueryParam("ats", ats, queryParams);
                    }
                } else if(ats != null && !ctaAtsParent.contains(",") && ats.contains(",") && !ctaAtsParent.contains("*")) {

                    if (ats.contains(ctaAtsParent)) {
                        ats = ats.replace(ctaAtsParent, "").trim();
                    }
                    String[] AtsIds = ats.split(",");
                    if (AtsIds.length > 0) {
                        String atsStr = StringUtils.EMPTY;
                        for (String strAtsIds : AtsIds) {
                            if (strAtsIds.length() != 0) {
                                if (atsStr.length() == 0) {
                                    atsStr = strAtsIds.trim();
                                } else {
                                    atsStr = atsStr.trim() + "," + strAtsIds.trim();
                                }
                            }
                        }
                        ats = atsStr.trim();
                        queryParams = addQueryParam("ats", ats, queryParams);
                    }
                } else if(ats != null && ctaAtsParent.contains(",") && !ats.contains(",") && ats.length() != 0 && !ctaAtsParent.contains("*")) {
                    if (ctaAtsParent.equalsIgnoreCase(ats.trim())) {

                    } else
                    {
                        queryParams = addQueryParam("ats", ats, queryParams);
                    }
                } else {
                    queryParams = addQueryParam("ats", sb, queryParams);
                }

            } else {
                queryParams = addQueryParam("ats", ats, queryParams);
            }
            if(!ctaSbParent.isEmpty()) {
                if(sb != null && !ctaSbParent.contains(",")) {
                    if (sb != null && ctaSbParent.contains("*") || ctaSbParent.equalsIgnoreCase(sb)) {
                    } else {
                        queryParams = addQueryParam("sb", sb, queryParams);
                    }
                }else if(sb != null && ctaSbParent.contains(",")){
                    if(ctaSbParent.contains(sb.trim()))
                    {

                    }
                    else{
                        queryParams = addQueryParam("sb", sb.trim(), queryParams);
                    }
                }

            }else {
                queryParams = addQueryParam("sb", sb, queryParams);
            }
        }
        if((!ctaChkPreselectParent.isEmpty() && !ctaChkPreselectChild.isEmpty()) || (ctaChkPreselectParent.isEmpty() && !ctaChkPreselectChild.isEmpty())) {

            if (!ctaAtsChild.isEmpty()) {
                if (ats != null && ctaAtsChild.contains(",") && ats.contains(",")) {
                    String[] AtsChild = ctaAtsChild.split(",");

                    for (String strAtsVal : AtsChild) {
                        if (ats.contains(strAtsVal)) {
                            ats = ats.replace(strAtsVal, "").trim();
                        }
                    }
                    String[] AtsIds = ats.split(",");
                    if (AtsIds.length > 0) {
                        String atsStr = StringUtils.EMPTY;
                        for (String strAtsIds : AtsIds) {
                            if (strAtsIds.length() != 0) {
                                if (atsStr.length() == 0) {
                                    atsStr = strAtsIds.trim();
                                } else {
                                    atsStr = atsStr.trim() + "," + strAtsIds.trim();
                                }
                            }
                        }
                        ats = atsStr.trim();
                        queryParams = addQueryParam("ats", ats, queryParams);
                    }
                } else if(ats != null && !ctaAtsChild.trim().contains(",") && ats.trim().contains(",") && !ctaAtsChild.contains("*")) {

                    if (ats.contains(ctaAtsChild)) {
                        ats = ats.replace(ctaAtsChild, "").trim();
                    }
                    String[] AtsIds = ats.split(",");
                    if (AtsIds.length > 0) {
                        String atsStr = StringUtils.EMPTY;
                        for (String strAtsIds : AtsIds) {
                            if (strAtsIds.length() != 0) {
                                if (atsStr.length() == 0) {
                                    atsStr = strAtsIds.trim();
                                } else {
                                    atsStr = atsStr.trim() + "," + strAtsIds.trim();
                                }
                            }
                        }
                        ats = atsStr.trim();
                        queryParams = addQueryParam("ats", ats, queryParams);
                    }
                } else if(ats != null && ctaAtsChild.trim().contains(",") && !ats.trim().contains(",") ) {
                    if (ctaAtsChild.trim().contains(ats)) {

                    } else
                    {
                        queryParams = addQueryParam("ats", ats, queryParams);
                    }
                } else if (ats != null && ctaAtsChild.trim().contains("*") || ctaAtsChild.trim().equalsIgnoreCase(ats)) {
                } else {
                    queryParams = addQueryParam("ats", ats, queryParams);
                }

            }else {
                queryParams = addQueryParam("ats", ats, queryParams);
            }

            if (!ctaSbChild.isEmpty()) {
                if(sb != null && !ctaSbChild.trim().contains(",")) {
                    if (sb != null && ctaSbChild.contains("*") || ctaSbChild.equalsIgnoreCase(sb)) {
                    } else {
                        queryParams = addQueryParam("sb", sb, queryParams);
                    }
                }else if(sb != null && ctaSbChild.contains(",")){
                    if(ctaSbChild.contains(sb.trim()))
                    {
                    }
                    else{
                        queryParams = addQueryParam("sb", sb.trim(), queryParams);
                    }
                }
            } else {
                queryParams = addQueryParam("sb", sb, queryParams);
            }
        }

        if (targetUrl != null) {
            if (targetUrl.contains(("/signup")) ||targetUrl.contains(("cart.q.sling.com")) || targetUrl.contains(("cart.b.sling.com")) || targetUrl.contains(("cart.sling.com"))) {
                queryParams = addQueryParam("sp", sp, queryParams);
            }
        }

        if (Raf != null && targetUrl != null) {
            if (Raf.equals("true") && (targetUrl.contains(("/signup")) || targetUrl.contains(("cart.q.sling.com")) || targetUrl.contains(("cart.b.sling.com")) || targetUrl.contains(("cart.sling.com")))) {
                LOG.info("inside the Raf is true flow");
                queryParams = addQueryParam("affiliate", affiliate, queryParams);
            }

        }

        if ("yes".equalsIgnoreCase(overrideDisclaimer)) {
            if (dsc != null) {
                final Page disclaimerPage = pageManager.getPage(dsc);
                if(disclaimerPage!=null) { //if disclaimerPage is null no need to process further
	                Resource resourceDisclaimer = resourceResolver.getResource(dsc); //disclaimerPage.getContentResource();
	                Node nodeDisclaimer = resourceDisclaimer.adaptTo(Node.class);
	                LOG.info("CTA Disclaimer Text Path is" + nodeDisclaimer.getName()+"----"+disclaimerPage.getPath());
	                dsc = nodeDisclaimer.getName();
	                queryParams = addQueryParam("dsc", dsc, queryParams);
                }
            }
        }

        if ("no".equalsIgnoreCase(overrideDisclaimer)) {
            if (targetUrl.contains(("/signup")) || targetUrl.contains(("cart.q.sling.com")) || targetUrl.contains(("cart.b.sling.com")) || targetUrl.contains(("cart.sling.com"))) {
                queryParams = addQueryParam("hd", hd, queryParams);
            }
        }

        queryParams = queryParams.substring(0, queryParams.length() - 1);
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
