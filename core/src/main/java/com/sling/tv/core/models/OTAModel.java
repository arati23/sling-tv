package com.sling.tv.core.models;

import com.sling.tv.core.services.OsgiConfigService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OTAModel {
	private static Logger LOG = LoggerFactory.getLogger(OTAModel.class);

    @Inject
    private String otaId;

    @Inject
    private String otaTitle;

    @Inject
    private String addressText;

    @Inject
    private String errorMsgAddressField;

    @Inject
    private String cityText;

    @Inject
    private String hideCityField;

    @Inject
    private String stateText;

    @Inject
    private String hideStateField;

    @Inject
    private String zipText;

    @Inject
    private String errorMsgZipField;

    @Inject
    private String ctaText;

    @Inject
    private String address400Error;

    @Inject
    private String addressOtherError;

    @Inject
    private String resultsHeaderText;

    @Inject
    private String legendTextSling;

    @Inject
    private String legendImageSling;

    @Inject
    private String legendTextIndoor;

    @Inject
    private String legendImageIndoor;

    @Inject
    private String legendTextOutdoor;

    @Inject
    private String legendImageOutdoor;

    @Inject
    private String resultsDisclaimerText;

    @Inject
    private String moreResultsCTAText;

    @Inject
    private String lessResultsCTAText;

    @Inject
    private String results400Error;

    @Inject
    private String resultsOtherError;

    @Inject
    private String noResultsMessage;

    @Inject
    private String outDoorInfo;

	@Inject
	OsgiConfigService osgiConfigService;

	@Inject
	private String addressScrubURL;

	@Inject
	private String antennaServiceURL;

    @Inject
    private Resource offerDeals;

    private List<OTAItemModel> offerDealsList;

	@PostConstruct
	protected void init() {
      try {
          this.addressScrubURL = osgiConfigService.getSlingAddressScrubURL();
          this.antennaServiceURL = osgiConfigService.getSlingAntennaServiceURL();
          LOG.debug("AddressScrub URL {}", addressScrubURL);
          LOG.debug("AntennaService URL {}", antennaServiceURL);
      }catch(Exception ex) {
          LOG.error("The error has occured while reading properties{}",ex);
      }
        offerDealsList = getOfferDealsItems();
    }

    private List<OTAItemModel> getOfferDealsItems() {
        List<OTAItemModel> deals = new ArrayList<>();
        if (null != offerDeals ) {
            Iterator<Resource> resourceChildren = offerDeals.listChildren();
            while (resourceChildren.hasNext())
            {
                deals.add(resourceChildren.next().adaptTo(OTAItemModel.class));
            }

        }

        return deals;
    }

    public List<OTAItemModel> getOfferDeals() {
        return this.offerDealsList;
    }

    public String getAddressScrubURL() {
        return addressScrubURL;
    }
	
	public String getAntennaServiceURL() {
        return antennaServiceURL;
    }

    public String getOtaId() {
        return otaId;
    }

    public String getOtaTitle() {
        return otaTitle;
    }

    public String getAddressText() {
        return addressText;
    }

    public String getErrorMsgAddressField() {
        return errorMsgAddressField;
    }

    public String getCityText() {
        return cityText;
    }

    public String getHideCityField() {
        return hideCityField;
    }

    public String getStateText() {
        return stateText;
    }

    public String getHideStateField() {
        return hideStateField;
    }

    public String getZipText() {
        return zipText;
    }

    public String getErrorMsgZipField() {
        return errorMsgZipField;
    }

    public String getCtaText() {
        return ctaText;
    }

    public String getAddress400Error() {
        return address400Error;
    }

    public String getAddressOtherError() {
        return addressOtherError;
    }

    public String getResultsHeaderText() {
        return resultsHeaderText;
    }

    public String getLegendTextSling() {
        return legendTextSling;
    }

    public String getLegendImageSling() {
        return legendImageSling;
    }

    public String getLegendTextIndoor() {
        return legendTextIndoor;
    }

    public String getLegendImageIndoor() {
        return legendImageIndoor;
    }

    public String getLegendTextOutdoor() {
        return legendTextOutdoor;
    }

    public String getLegendImageOutdoor() {
        return legendImageOutdoor;
    }

    public String getResultsDisclaimerText() {
        return resultsDisclaimerText;
    }

    public String getMoreResultsCTAText() {
        return moreResultsCTAText;
    }

    public String getLessResultsCTAText() {
        return lessResultsCTAText;
    }

    public String getResults400Error() {
        return results400Error;
    }

    public String getResultsOtherError() {
        return resultsOtherError;
    }

    public String getNoResultsMessage() {
        return noResultsMessage;
    }

    public String getOutDoorInfo() {
        return outDoorInfo;
    }
}
