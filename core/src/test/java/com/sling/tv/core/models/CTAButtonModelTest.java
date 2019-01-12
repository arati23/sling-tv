/**
 * 
 */
package com.sling.tv.core.models;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import javax.jcr.Node;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.modules.junit4.PowerMockRunner;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import io.wcm.testing.mock.aem.junit.AemContext;

/**
 * @author Dileep Muraleedharan
 *
 */
@RunWith(PowerMockRunner.class)
public class CTAButtonModelTest {
	
		@Mock
		Page page;
		
		@Mock
		PageManager pageManager;
		
		@Mock
		ResourceResolver resourceResolver;
		
		@Mock
		Resource resource;
		
		@Mock
		Node node;
		
		@Mock
		ValueMap valueMap;
		
		@Mock
		Locale locale;
			  	
		@Rule
		public final AemContext context = new AemContext();
	
	
		@Before
		public void setup() {
			
			page = PowerMockito.mock(Page.class);
			pageManager = PowerMockito.mock(PageManager.class);
			resourceResolver = PowerMockito.mock(ResourceResolver.class);
			resource = PowerMockito.mock(Resource.class);
			node = PowerMockito.mock(Node.class);
			locale = PowerMockito.mock(Locale.class);
			valueMap = PowerMockito.mock(ValueMap.class);
		
		}
		
		@Test
		public void Test_CTAItemsLink() {
			

			
			CTAButtonModel ctaButtonModelObj = new CTAButtonModel();
			try {
				MemberModifier.field(CTAButtonModel .class, "destination").set(ctaButtonModelObj , "link");
				MemberModifier.field(CTAButtonModel .class, "targetUrl").set(ctaButtonModelObj , "https://wwww.sling.com/airtv");
				MemberModifier.field(CTAButtonModel .class, "buttonText").set(ctaButtonModelObj , "Find More Details");
				MemberModifier.field(CTAButtonModel .class, "ctaWindowSelection").set(ctaButtonModelObj , "_self");
				MemberModifier.field(CTAButtonModel .class, "alignment").set(ctaButtonModelObj , "text-center");
				MemberModifier.field(CTAButtonModel .class, "ctaBorder").set(ctaButtonModelObj , "cta-button--solid");
				MemberModifier.field(CTAButtonModel .class, "ctaOptions").set(ctaButtonModelObj , "cta-button--large");
				MemberModifier.field(CTAButtonModel .class, "caretOptions").set(ctaButtonModelObj , "cta-button_caret--solid");
				MemberModifier.field(CTAButtonModel .class, "ctaHeaderOptions").set(ctaButtonModelObj , "js-cta-header");
				MemberModifier.field(CTAButtonModel .class, "hideCta").set(ctaButtonModelObj , "true");
				MemberModifier.field(CTAButtonModel .class, "stickyMobile").set(ctaButtonModelObj , "true");
				MemberModifier.field(CTAButtonModel .class, "offerDetailsHeading").set(ctaButtonModelObj , "OfferDetails Heading");
				MemberModifier.field(CTAButtonModel .class, "text").set(ctaButtonModelObj , "<p>This is the offer details description</p>");
				MemberModifier.field(CTAButtonModel .class, "offerDetailsLabel").set(ctaButtonModelObj , "Offerdetails Label");
				MemberModifier.field(CTAButtonModel .class, "offerPublishedDate").set(ctaButtonModelObj , "October 18 2018");
				
				
		    	} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			PowerMockito.when(page.getLanguage(false)).thenReturn(null);
		    PowerMockito.when(page.getName()).thenReturn("homepage");
		    PowerMockito.when(page.getProperties()).thenReturn(valueMap);
		  	ctaButtonModelObj.init();
			String ctaUrl = ctaButtonModelObj.getCtaURL();
			String targetWindow=ctaButtonModelObj.getCtaWindowSelection();
			String buttonText = ctaButtonModelObj.getButtonText();
			String buttonAlignment = ctaButtonModelObj.getAlignment();
			String buttonStyle=ctaButtonModelObj.getCtaBorder();
			String buttonSize=ctaButtonModelObj.getCtaOptions();
			String buttonCaretOption=ctaButtonModelObj.getCaretOptions();
			String buttonHeaderOption=ctaButtonModelObj.getCtaHeaderOptions();
			String ctaHide=ctaButtonModelObj.getHideCta();
			String ctaMobileView = ctaButtonModelObj.getStickyMobile();
			String offerDetailsHeading=ctaButtonModelObj.getOfferDetailsHeading();
			String offerDescription=ctaButtonModelObj.getOfferDescription();
			String offerLabel = ctaButtonModelObj.getOfferDetailsLabel();
			String offerPublishedDate = ctaButtonModelObj.getOfferPublishedDate();
			assertEquals("Find More Details",buttonText);
			assertEquals("text-center",buttonAlignment);
			assertEquals("cta-button--solid",buttonStyle);
			assertEquals("cta-button--large",buttonSize);
			assertEquals("cta-button_caret--solid",buttonCaretOption);
			assertEquals("js-cta-header",buttonHeaderOption);
			assertEquals("true",ctaHide);
			assertEquals("true",ctaMobileView);
			assertEquals("OfferDetails Heading",offerDetailsHeading);
			assertEquals("Offerdetails Label",offerLabel);
			assertEquals("October 18 2018",offerPublishedDate);
			assertEquals("_self",targetWindow);
			assertEquals("<p>This is the offer details description</p>",offerDescription);
			assertEquals("https://wwww.sling.com/airtv",ctaUrl);
			assertEquals("Find More Details",buttonText);
			assertEquals("_self",targetWindow);
		
			
		}


		
		
		@Test
		public void Test_CTANewCart() {
			
			CTAButtonModel ctaButtonModelObj = new CTAButtonModel();
			try {
					MemberModifier.field(CTAButtonModel .class, "targetUrl").set(ctaButtonModelObj , "https://cart.sling.com");
					MemberModifier.field(CTAButtonModel .class, "destination").set(ctaButtonModelObj , "new-cart");
					MemberModifier.field(CTAButtonModel .class, "currentPage").set(ctaButtonModelObj , page);
					MemberModifier.field(CTAButtonModel .class, "ctaClassification").set(ctaButtonModelObj , "cta-classification");
					MemberModifier.field(CTAButtonModel .class, "cartFlow").set(ctaButtonModelObj , "cta-flow");
					MemberModifier.field(CTAButtonModel .class, "planId").set(ctaButtonModelObj , "cta-planId");
					MemberModifier.field(CTAButtonModel .class, "sb").set(ctaButtonModelObj , "sb-test");
					MemberModifier.field(CTAButtonModel .class, "ats").set(ctaButtonModelObj , "ats-test");
					MemberModifier.field(CTAButtonModel .class, "offerId").set(ctaButtonModelObj , "cta-offerID");
					MemberModifier.field(CTAButtonModel .class, "deviceType").set(ctaButtonModelObj , "cta-device");
					MemberModifier.field(CTAButtonModel .class, "cartStep").set(ctaButtonModelObj , "cta-step");
				//	MemberModifier.field(CTAButtonModel .class, "sp").set(ctaButtonModelObj , "homepage");
					//MemberModifier.field(CTAButtonModel .class, "Raf").set(ctaButtonModelObj , "true");
					MemberModifier.field(CTAButtonModel .class, "affiliate").set(ctaButtonModelObj , "cta_affiliate");
					MemberModifier.field(CTAButtonModel .class, "buttonText").set(ctaButtonModelObj , "Find More Details");
		    	} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			PowerMockito.when(page.getLanguage(false)).thenReturn(null);
		    PowerMockito.when(page.getName()).thenReturn("homepage");
		    PowerMockito.when(page.getProperties()).thenReturn(valueMap);
		    PowerMockito.when(valueMap.containsKey("checkRaf")).thenReturn(false);
			ctaButtonModelObj.init();
			String ctaUrl = ctaButtonModelObj.getCtaURL();
			String buttonText = ctaButtonModelObj.getButtonText();
			String spText = ctaButtonModelObj.getSp();
			assertEquals("https://cart.sling.com?locale=en&classification=cta-classification&flow=cta-flow&step=cta-step&device=cta-device&plan=cta-planId&offer_id=cta-offerID&sb=sb-test&ats=ats-test&sp=homepage",ctaUrl);
			assertEquals("Find More Details",buttonText);
			assertEquals("homepage",spText);
			

		}
		
		@Test
		public void Test_CTAItemsForOldCart() {
			
			CTAButtonModel ctaButtonModelObj = new CTAButtonModel();
			try {
				MemberModifier.field(CTAButtonModel .class, "destination").set(ctaButtonModelObj , "old-cart");
				MemberModifier.field(CTAButtonModel .class, "currentPage").set(ctaButtonModelObj , page);
				MemberModifier.field(CTAButtonModel .class, "ctaClassification").set(ctaButtonModelObj , "cta-classification");
				MemberModifier.field(CTAButtonModel .class, "cartFlow").set(ctaButtonModelObj , "cta-flow");
				MemberModifier.field(CTAButtonModel .class, "planId").set(ctaButtonModelObj , "cta-planId");
				MemberModifier.field(CTAButtonModel .class, "offerId").set(ctaButtonModelObj , "cta-offerID");
				MemberModifier.field(CTAButtonModel .class, "deviceType").set(ctaButtonModelObj , "cta-device");
				MemberModifier.field(CTAButtonModel .class, "cartStep").set(ctaButtonModelObj , "cta-step");
			//	MemberModifier.field(CTAButtonModel .class, "sp").set(ctaButtonModelObj , "homepage");
				//MemberModifier.field(CTAButtonModel .class, "Raf").set(ctaButtonModelObj , "true");
				MemberModifier.field(CTAButtonModel .class, "affiliate").set(ctaButtonModelObj , "cta_affiliate");
				MemberModifier.field(CTAButtonModel .class, "buttonText").set(ctaButtonModelObj , "Find More Details");
				
				
				
				
		    	} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			PowerMockito.when(page.getLanguage(false)).thenReturn(null);
		    PowerMockito.when(page.getName()).thenReturn("homepage");
		    PowerMockito.when(page.getProperties()).thenReturn(valueMap);
		    PowerMockito.when(valueMap.containsKey("checkRaf")).thenReturn(false);
		  	ctaButtonModelObj.init();
			String ctaUrl = ctaButtonModelObj.getCtaURL();
			String buttonText = ctaButtonModelObj.getButtonText();
			String spText = ctaButtonModelObj.getSp();
			System.out.println(ctaUrl);
			assertEquals("/signup?locale=en&classification=cta-classification&flow=cta-flow&step=cta-step&device=cta-device&plan=cta-planId&offer_id=cta-offerID&sp=homepage",ctaUrl);
			assertEquals("Find More Details",buttonText);
			assertEquals("homepage",spText);
		}
		
		
}
