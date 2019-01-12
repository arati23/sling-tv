package com.sling.tv.core.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class OTAModelTest {

	@Rule
	public final AemContext context = new AemContext();
	
	private OTAModel ota;
	private List<OTAItemModel> otaItems;
	private static final String COMPONENT_PATH = "/content/slingtv/en/jcr:content/par/ota";

	@Before
	public void setup() {
		
		context.load().json("/ota.json", COMPONENT_PATH);
		context.addModelsForClasses(OTAModel.class);
		context.addModelsForClasses(OTAItemModel.class);
	}
	
	
	
	@Test
	public void Test_whenPropEquals() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		OTAModel ota = resource.adaptTo(OTAModel.class);
     	String cmpId = ota.getOtaId();
		String title = ota.getOtaTitle();
		String addText = ota.getAddressText();
		String errMsgAddField = ota.getErrorMsgAddressField();
		String ctText = ota.getCityText();
		String stText = ota.getStateText();
		String hdCtText = ota.getHideCityField();
		String hfStText = ota.getHideStateField();
		String zpText = ota.getZipText();
		String errZp = ota.getErrorMsgZipField();
		String ctaTxt = ota.getCtaText();
		String add4Error = ota.getAddress400Error();
		String addOtherError = ota.getAddressOtherError();
		String rsHeaderText = ota.getResultsHeaderText();
		String lgText = ota.getLegendTextSling();
		String lgImage = ota.getLegendImageSling();
		String indoorTxt = ota.getLegendTextIndoor();
		String indoorImage = ota.getLegendImageIndoor();
		String outdoorTxt = ota.getLegendTextOutdoor();
		String OutdoorImage = ota.getLegendImageOutdoor();
		String rsDisclaimerTxt = ota.getResultsDisclaimerText();
		String moreTxt = ota.getMoreResultsCTAText();
		String lessTxt = ota.getLessResultsCTAText();
		String rs4Error = ota.getResults400Error();
		String rsOtherError = ota.getResultsOtherError();
		String noResult = ota.getNoResultsMessage();
		String outdoorInfo = ota.getOutDoorInfo();
		String addScrub = ota.getAddressScrubURL();
		String antenna = ota.getAntennaServiceURL();

   	    Assert.assertEquals("OTAId",cmpId);
		Assert.assertEquals("Find Available Local Channels",title);
		Assert.assertEquals("Address",addText);
		Assert.assertEquals("Please enter valid address",errMsgAddField);
		Assert.assertEquals("City",ctText);
		Assert.assertEquals("State",stText);
		Assert.assertEquals("true",hdCtText);
		Assert.assertEquals("true",hfStText);
		Assert.assertEquals("Zip",zpText);
		Assert.assertEquals("Please enter proper Zip",errZp);
		Assert.assertEquals("SEE MORE CHANNELS Button",ctaTxt);
		Assert.assertEquals("Please try after some time",add4Error);
		Assert.assertEquals("Please try after some time",addOtherError);
		Assert.assertEquals("Local Channel Availability For,",rsHeaderText);
		Assert.assertEquals("Available on sling",lgText);
		Assert.assertEquals("/content/image1",lgImage);
		Assert.assertEquals("Indoor Antenna",indoorTxt);
		Assert.assertEquals("/content/image2",indoorImage);
		Assert.assertEquals("Outdoor Antenna",outdoorTxt);
		Assert.assertEquals("/content/image3",OutdoorImage);
		Assert.assertEquals("Local broadcast channel availability varies by geographic location",rsDisclaimerTxt);
		Assert.assertEquals("More Results",moreTxt);
		Assert.assertEquals("Less Results",lessTxt);
		Assert.assertEquals("Titan service 400",rs4Error);
		Assert.assertEquals("Titan Service Other Errors",rsOtherError);
		Assert.assertEquals("No results",noResult);
		Assert.assertEquals("Call 1-855-428-7201 to learn about outdoor antenna options",outdoorInfo);
		Assert.assertEquals("https://ms.d.sling.com/sling-api/alpha/address-scrub",addScrub);
		Assert.assertEquals("https://ms.d.sling.com/sling-api/alpha/ota/antenna-recommendation",antenna);

	}

	@Test
	public void Test_whenOfferDealsExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		OTAModel ota = resource.adaptTo(OTAModel.class);
		List<OTAItemModel> offerItem = ota.getOfferDeals();
		Assert.assertEquals("asdvsbfdgn fhgdf ds dbf gnh fgc vxcf",offerItem.get(0).getOfferCaveat());
		Assert.assertEquals("febsg dw efagrb t grfwedq defgtr twegfwqwdefrtbr dfsfgsdzf da fgsefdvfbgrdfscxfds",offerItem.get(0).getOfferDescription());
		Assert.assertEquals("/content/dam/sling-tv/devices/device-hardware/free-ribbon.png",offerItem.get(0).getOfferFlagImage());
		Assert.assertEquals("Antenna Offer",offerItem.get(0).getOfferHeader());
		Assert.assertEquals("/content/dam/sling-tv/devices/device-hardware/roku/Roku Streaming Stick - wide.png",offerItem.get(0).getOfferImage());
		Assert.assertEquals("free",offerItem.get(0).getOfferPrice());
		Assert.assertEquals("first",offerItem.get(0).getOfferRibbonText());

	}
	
	@Test
	public void Test_whenOfferDealsDoesNotExist() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		ota = resource.adaptTo(OTAModel.class);
		List<OTAItemModel> dealsItems = ota.getOfferDeals();
		Assert.assertNull(dealsItems.get(1).getOfferCaveat());
		Assert.assertNull(dealsItems.get(1).getOfferDescription());
		Assert.assertNull(dealsItems.get(1).getOfferFlagImage());
		Assert.assertNull(dealsItems.get(1).getOfferHeader());
		Assert.assertNull(dealsItems.get(1).getOfferImage());
		Assert.assertNull(dealsItems.get(1).getOfferPrice());
		Assert.assertNull(dealsItems.get(1).getOfferRibbonText());

	}
	
	
	@Test
	public void nullTest() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		ota = resource.adaptTo(OTAModel.class);
		Assert.assertNotNull(ota);

	}
	

}
