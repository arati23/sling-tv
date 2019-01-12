package com.sling.tv.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.MockitoAnnotations.Mock;
import org.powermock.api.mockito.PowerMockito;

import com.adobe.acs.commons.models.injectors.impl.AemObjectInjector;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.junit.Assert;






public class NavigationComponentModelTest {

	private NavigationComponentModel ncm;
	private static final String COMPONENT_PATH = "/content/navPages/en/header";
	private static final String OFFER_PAGE_PATH = "/";
	

	@Rule
	public final AemContext context = new AemContext();

	@Before
	public void setup() {
		context.load().json("/header-page.json", "/content/navPages");
		context.load().json("/header.json", "/content/navPages/en/header");
		context.addModelsForClasses(NavigationComponentModel.class);
		context.addModelsForClasses(RegionTextLinkModel.class);
		context.addModelsForClasses(RegionLogoLinkModel.class);
		context.addModelsForClasses(NavigationPage.class);
		

	}
	
	
	
	@Test
	public void whenFooterIdExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedFooterId = "theFooterId";
		String actualFooterId = ncm.getFooterId();

		Assert.assertEquals(expectedFooterId, actualFooterId);
	}
	
	@Test
	public void whenThemeTypeExists() {
		
		

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		Resource navResource = context.resourceResolver().getResource("/content/navPages/en");
		PageManager pgm = context.pageManager();
		PageManager pgmMock = PowerMockito.mock(PageManager.class);
		Page navigationPage = pgm.getContainingPage(navResource);
		Page cPage = pgm.getContainingPage(resource);
		
		ncm = resource.adaptTo(NavigationComponentModel.class);
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedThemeType = "dark";
		String actualThemeType = ncm.getThemeType();

		Assert.assertEquals(expectedThemeType, actualThemeType);
	}
	
	@Test
	public void whenSocialLinkComponentExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedSocialLinkComponent = "true";
		String actualSocialLinkComponent = ncm.getSocialLinkComponent();

		Assert.assertEquals(expectedSocialLinkComponent, actualSocialLinkComponent);
	}

	@Test
	public void Test_whenPropertiesexists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		NavigationComponentModel ncm = resource.adaptTo(NavigationComponentModel.class);
		List<RegionTextLinkModel> rtlm = ncm.getRegionTextLinksList();

		String expectedSignInText = "Sign In";
		String actualSignInText = ncm.getSignInTextNew();

		String expectedRegionName = "Sling Latino";
		String actualRegionName = rtlm.get(0).getRegionName();

		Assert.assertEquals(expectedSignInText, actualSignInText);
		Assert.assertEquals(expectedRegionName, actualRegionName);


		
	}
	
	@Test
	public void whenHeaderTypeExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedHeaderType = "newHeader";
		String actualHeaderType = ncm.getHeaderType();

		Assert.assertEquals(expectedHeaderType, actualHeaderType);
	}
	
	
	@Test
	public void whenHideLanguageSelExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedHideLanguageSel = "false";
		String actualHideLanguageSel = ncm.getHideLanguageSel();

		Assert.assertEquals(expectedHideLanguageSel, actualHideLanguageSel);
	}
	
	
	@Test
	public void whenLogoAltTextExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedLogoAltText = "Sling";
		String actualLogoAltText = ncm.getLogoAltText();

		Assert.assertEquals(expectedLogoAltText, actualLogoAltText);
	}
	
	
	
	
	@Test
	public void whenMemberTextExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		ncm = resource.adaptTo(NavigationComponentModel.class);
		
		String expectedMemberText = "Member";
		String actualMemberText = ncm.getMemberText();

		Assert.assertEquals(expectedMemberText, actualMemberText);
	}
	
	@Test
	public void Test_whenIncludeSubNavComponentExists()
	{
		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		ncm = resource.adaptTo(NavigationComponentModel.class);
		
		String expectedIncludeSubNavComponent = "true";
		String actualIncludeSubNavComponent = ncm.getIncludeSubNavComponent();
		
		Assert.assertEquals(expectedIncludeSubNavComponent, actualIncludeSubNavComponent);
	}

	@Test
	public void Test_whenNewRegionTextLinksExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		ncm = resource.adaptTo(NavigationComponentModel.class);
		List<RegionTextLinkModel> rtlm = ncm.getRegionTextLinksListNew();

		Assert.assertNotNull(rtlm);

		String theNewSignInText = ncm.getSignInTextNew();
		Assert.assertEquals("Sign In", theNewSignInText);
		Assert.assertEquals("Sling Latino New", rtlm.get(0).getRegionNameNew());
		Assert.assertEquals("Sling International New", rtlm.get(1).getRegionNameNew());

	}

	@Test
	public void Test_whenNewRegionLogoLinksExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		ncm = resource.adaptTo(NavigationComponentModel.class);
		List<RegionLogoLinkModel> rllm = ncm.getRegionLogoLinksList();

		Assert.assertNotNull(rllm);
		String expectedLogoPathString = "/content/dam/sling-tv/misc/Icon - Al Jazeera.svg";
		String expectedLogoLinkString = "https://www.sling.com/2/Al_Jazeera";

		String theNewSignInText = ncm.getSignInTextNew();
		Assert.assertEquals(expectedLogoPathString, rllm.get(0).getRegionLogo());
		Assert.assertEquals(expectedLogoLinkString, rllm.get(1).getRegionLink());

	}

	@Test
	public void Test_Links() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "/link-tests/scenario1");
		ncm = resource.adaptTo(NavigationComponentModel.class);
		String expectedSignInLink = "/sign-in";
		String actualSignInLink = ncm.getSignInLink();
		Assert.assertEquals(expectedSignInLink, actualSignInLink);

		String expectedMyAccountLink = "/my-account";
		String actualMyAccountLink = ncm.getMyAccountLink();
		Assert.assertEquals(expectedMyAccountLink, actualMyAccountLink);

		String expectedLogoLinkNew = "/logo-link-new";
		String actualLogoLinkNew = ncm.getLogoLinkNew();
		Assert.assertEquals(expectedLogoLinkNew, actualLogoLinkNew);

	}

	@Test
	public void Test_LinksWithPipe() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "/link-tests/scenario2");
		ncm = resource.adaptTo(NavigationComponentModel.class);
		String expectedSignInLink = "/sign-in%7Cfield2%7Cfield3";
		String actualSignInLink = ncm.getSignInLink();
		Assert.assertEquals(expectedSignInLink, actualSignInLink);

		String expectedMyAccountLink = "/my-account%7Cfield2%7Cfield3";
		String actualMyAccountLink = ncm.getMyAccountLink();
		Assert.assertEquals(expectedMyAccountLink, actualMyAccountLink);

	}

	@Test
	public void Test_LinksWithExtensionAddition() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "/link-tests/scenario3");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedLogoLinkNew = "/content/something/logoLink.html";
		String actualLogoLinkNew = ncm.getLogoLinkNew();
		Assert.assertEquals(expectedLogoLinkNew, actualLogoLinkNew);

	}
	
	@Test
	public void Test_SignOutText() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedSignOutText = "Sign Out";
		String actualSignOutText = ncm.getSignOutText();
		Assert.assertEquals(expectedSignOutText, actualSignOutText);

	}
	
	@Test
	public void Test_SignOutTextNew() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedSignOutTextNew = "Sign Out";
		String actualSignOutTextNew = ncm.getSignOutTextNew();
		Assert.assertEquals(expectedSignOutTextNew, actualSignOutTextNew);

	}
	
	@Test
	public void Test_ShowReferAFriend() {
		
		Page currentPage = PowerMockito.mock(Page.class);
		PageManager pgm = PowerMockito.mock(PageManager.class);
		ResourceResolver rr = PowerMockito.mock(ResourceResolver.class);
		Resource resource = PowerMockito.mock(Resource.class);
		ValueMap vm = PowerMockito.mock(ValueMap.class);
		
		
		PowerMockito.when(resource.getResourceResolver()).thenReturn(rr);
		PowerMockito.when(rr.adaptTo(PageManager.class)).thenReturn(pgm);
		PowerMockito.when(pgm.getContainingPage(resource)).thenReturn(currentPage);
		PowerMockito.when(currentPage.getProperties()).thenReturn(vm);
		PowerMockito.when(vm.get("showReferAFriend", (String) null)).thenReturn("true");

		
		NavigationComponentModel ncm = new NavigationComponentModel(resource);
		
		ncm.init();
		
		String expectedShowReferAFriend = "true";
		String actualShowReferAFriend = ncm.getShowReferAFriend();
		Assert.assertEquals(expectedShowReferAFriend, actualShowReferAFriend);
		

	}
	
	@Test
	public void Test_ShowReferAFriendWhenCurrentPageNull() {
		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		Page currentPage = null;
		PageManager pgm = PowerMockito.mock(PageManager.class);
		ResourceResolver rr = PowerMockito.mock(ResourceResolver.class);
		ValueMap vm = PowerMockito.mock(ValueMap.class);
		
		
		PowerMockito.when(resource.getResourceResolver()).thenReturn(rr);
		PowerMockito.when(rr.adaptTo(PageManager.class)).thenReturn(pgm);
		PowerMockito.when(pgm.getContainingPage(resource)).thenReturn(null);
		
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedShowReferAFriend = "";
		String actualShowReferAFriend = ncm.getShowReferAFriend();
		Assert.assertNull(actualShowReferAFriend);

	}
	
	@Test
	public void Test_LogoLink() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedLogoLink = "/link";
		String actualLogoLink = ncm.getLogoLink();
		Assert.assertEquals(expectedLogoLink, actualLogoLink);

	}
	
	@Test
	public void Test_SignInLinkNew() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedSignInLinkNew = "/sing-in-link-new";
		String actualSignInLinkNew = ncm.getSignInLinkNew();
		Assert.assertEquals(expectedSignInLinkNew, actualSignInLinkNew);

	}
	
	@Test
	public void Test_SignOutLink() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "/link-tests/scenario1");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedSignOutLink = "/sign-out";
		String actualSignOutLink = ncm.getSignOutLink();
		Assert.assertEquals(expectedSignOutLink, actualSignOutLink);

	}
	
	@Test
	public void Test_SignOutLinkNew() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "/link-tests/scenario1");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedSignOutLinkNew = "/sign-out-link-new";
		String actualSignOutLinkNew = ncm.getSignOutLinkNew();
		Assert.assertEquals(expectedSignOutLinkNew, actualSignOutLinkNew);

	}
	
	@Test
	public void Test_HeaderText() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedHeaderText = "Header Text";
		String actualHeaderText = ncm.getHeaderText();
		Assert.assertEquals(expectedHeaderText, actualHeaderText);

	}
	
	
	@Test
	public void Test_HeaderId() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedHeaderId = "HeaderID";
		String actualHeaderId = ncm.getHeaderId();
		Assert.assertEquals(expectedHeaderId, actualHeaderId);

	}
	
	@Test
	public void Test_NewHeaderId() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedNewHeaderId = "HeaderIDNew";
		String actualNewHeaderId = ncm.getNewHeaderId();
		Assert.assertEquals(expectedNewHeaderId, actualNewHeaderId);

	}
	
	@Test
	public void Test_MyAccountLabelTextNew() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedMyAccountLabelTextNew = "My Account Label New";
		String actualMyAccountLabelTextNew = ncm.getMyAccountLabelTextNew();
		Assert.assertEquals(expectedMyAccountLabelTextNew, actualMyAccountLabelTextNew);

	}
	
	@Test
	public void Test_HideHeaderNav() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedHideHeaderNav = "true";
		String actualHideHeaderNav = ncm.getHideHeaderNav();
		Assert.assertEquals(expectedHideHeaderNav, actualHideHeaderNav);

	}
	
	@Test
	public void Test_LanguageTitle() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedLanguageTitle = "Language Title";
		String actualLanguageTitle = ncm.getLanguageTitle();
		Assert.assertEquals(expectedLanguageTitle, actualLanguageTitle);

	}
	
	@Test
	public void Test_LogoImage() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedLogoImage = "/content/dam/sling-tv/sling-logos/domestic_logo (NEW).png";
		String actualLogoImage = ncm.getLogoImage();
		Assert.assertEquals(expectedLogoImage, actualLogoImage);

	}
	
	@Test
	public void Test_LogoImageNew() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedLogoImageNew = "/content/dam/sling-tv/sling-logos/domestic_logo_stroke.png";
		String actualLogoImageNew = ncm.getLogoImageNew();
		Assert.assertEquals(expectedLogoImageNew, actualLogoImageNew);

	}
	
	@Test
	public void Test_HeaderNavPath() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH + "");
		ncm = resource.adaptTo(NavigationComponentModel.class);

		String expectedHeaderNavPath = "/content/navPages/en";
		String actualHeaderNavPath = ncm.getHeaderNavPath();
		Assert.assertEquals(expectedHeaderNavPath, actualHeaderNavPath);

	}
	
	
	@Test
	public void Test_Header() {
		
//		PageManager pgm = PowerMockito.mock(PageManager.class);
		ResourceResolver rr = PowerMockito.mock(ResourceResolver.class);
		
		//createNavPages();
		
		Resource resource = context.resourceResolver().getResource("/content/navPages/en/header");
		Resource navResource = context.resourceResolver().getResource("/content/navPages/en");
//		PageManager pgm = context.pageManager();
//		PageManager pgmMock = PowerMockito.mock(PageManager.class);
//		Page navigationPage = pgm.getContainingPage(navResource);
//		Page cPage = pgm.getContainingPage(resource);
		
		ncm = resource.adaptTo(NavigationComponentModel.class);
		
//		PowerMockito.when(resource.getResourceResolver()).thenReturn(rr);
//		PowerMockito.when(rr.adaptTo(PageManager.class)).thenReturn(pgmMock);
//		PowerMockito.when(pgmMock.getContainingPage(resource)).thenReturn(cPage);
		//PowerMockito.when(currentPage.getPageManager()).thenReturn(pgm);

		String expectedHeaderNavPath = "English-two-one";
		NavigationPage nvp = ncm.getHeader();
		String actualHeaderNavPath = nvp.getChildren().get(1).getChildren().get(0).getTitle();
//		String actualHeaderNavPath = cPage.getTitle();
		Assert.assertEquals(expectedHeaderNavPath, actualHeaderNavPath);

	}
	@Test
	public void Test_Footer() {
		
//		PageManager pgm = PowerMockito.mock(PageManager.class);
		ResourceResolver rr = PowerMockito.mock(ResourceResolver.class);
		
		//createNavPages();
		
		Resource resource = context.resourceResolver().getResource("/content/navPages/en/header");
		Resource navResource = context.resourceResolver().getResource("/content/navPages/en");
//		PageManager pgm = context.pageManager();
//		PageManager pgmMock = PowerMockito.mock(PageManager.class);
//		Page navigationPage = pgm.getContainingPage(navResource);
//		Page cPage = pgm.getContainingPage(resource);
		
		ncm = resource.adaptTo(NavigationComponentModel.class);
		
//		PowerMockito.when(resource.getResourceResolver()).thenReturn(rr);
//		PowerMockito.when(rr.adaptTo(PageManager.class)).thenReturn(pgmMock);
//		PowerMockito.when(pgmMock.getContainingPage(resource)).thenReturn(cPage);
		//PowerMockito.when(currentPage.getPageManager()).thenReturn(pgm);

		String expectedHeaderNavPath = "Footer-Two-One";
		NavigationPage nvp = ncm.getFooter();
		String actualHeaderNavPath = nvp.getChildren().get(1).getChildren().get(0).getTitle();
//		String actualHeaderNavPath = cPage.getTitle();
		Assert.assertEquals(expectedHeaderNavPath, actualHeaderNavPath);

	}	



	private void createNavPages() {
		// TODO Auto-generated method stub
		context.create().page("/content/sample/en", "/apps/sample/template/homepage");
		context.create().page("/content/sample/en/one", "/apps/sample/template/homepage");
		context.create().page("/content/sample/en/two", "/apps/sample/template/homepage");
	}


}
