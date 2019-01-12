package com.sling.tv.core.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BFPModelTest {
	
	/*
	 * 
	 * @author Arati
	 * 
	 */

	@Rule
	public final AemContext context = new AemContext();

	private BFPModel bfp;
	private static final String COMPONENT_PATH = "/content/sling-tv/save-this/bfp/jcr:content/par/container_new/content/bfp";

	@Before
	public void setup() {

		context.load().json("/bfp.json", COMPONENT_PATH);
		context.addModelsForClasses(BFPModel.class);
	}

	@Test
	public void Test_whenPreviewIdDexists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		bfp = resource.adaptTo(BFPModel.class);
		String previewId = bfp.getPreviewId();
		Assert.assertEquals("7DFDOM001", previewId);
	}

	@Test
	public void Test_whenCartTimeExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		bfp = resource.adaptTo(BFPModel.class);
		String cardTime = bfp.getCartTime();
		Assert.assertEquals("5", cardTime);
	}

	@Test
	public void Test_whenBfpIdExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		bfp = resource.adaptTo(BFPModel.class);
		String bfpId = bfp.getBfpId();
		Assert.assertEquals("BFPTest", bfpId);
	}

	@Test
	public void Test_whenCartUrlExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		bfp = resource.adaptTo(BFPModel.class);
		String ctaUrl = bfp.getCtaUrl();
		Assert.assertEquals("/signup?locale=en&classification=us&flow=alacartetv&step=0&device=roku-express&plan=two-month-pre-pay&sp=roku&cvosrc=affiliate.roku.devicepage&affiliate=roku&utm_source=roku&utm_medium=referral&utm_campaign=rokudevice", ctaUrl);
	}

	@Test
	public void Test_whenChannelIdExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		bfp = resource.adaptTo(BFPModel.class);
		String channelId = bfp.getChannelId();
		Assert.assertEquals("2081", channelId);
	}

	@Test
	public void Test_whenPopupImagePathExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		bfp = resource.adaptTo(BFPModel.class);
		String overlayImage = bfp.getOverlayImage();
		Assert.assertEquals("/content/dam/sling-tv/misc/BFP_Overlay.png", overlayImage);
	}

	@Test
	public void Test_whenFallBackImagePathExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		bfp = resource.adaptTo(BFPModel.class);
		String posterImage = bfp.getPosterImage();
		Assert.assertEquals("/content/dam/sling-tv/marquees/domestic/campaign-assets/Marquee_Cut the Cord_Mobile.jpg", posterImage);
	}

	@Test
	public void Test_whenBFPMessageExists() {

		Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
		bfp = resource.adaptTo(BFPModel.class);
		String bfpMessage = bfp.getBfpMessage();
		Assert.assertEquals("15 minutes Free", bfpMessage);
	}

    @Test
    public void Test_whenButtonTextExists() {

        Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
        bfp = resource.adaptTo(BFPModel.class);
        String ButtonText = bfp.getButtonText();
        Assert.assertEquals("SIGN UP NOW", ButtonText);
    }


    @Test
    public void Test_whenRegisterTimeExists() {

        Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
        bfp = resource.adaptTo(BFPModel.class);
        String RegisterTime = bfp.getRegisterTime();
        Assert.assertEquals("2", RegisterTime);
    }

    @Test
    public void Test_whenBfpDefaultErrorExists() {

        Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
        bfp = resource.adaptTo(BFPModel.class);
        String BfpDefaultError = bfp.getBfpDefaultError();
        Assert.assertEquals("It Broke!", BfpDefaultError);
    }

    @Test
    public void Test_whenBfpEmailExists() {

        Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
        bfp = resource.adaptTo(BFPModel.class);
        String bfpemail = bfp.getBfpEmail();
        Assert.assertEquals("Email", bfpemail);
    }

    @Test
    public void Test_whenEmailExists() {

        Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
        bfp = resource.adaptTo(BFPModel.class);
        String email = bfp.getEmail();
        Assert.assertEquals("EMAIL", email);
    }

    @Test
    public void Test_whenErrorEmailText() {

        Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
        bfp = resource.adaptTo(BFPModel.class);
        String ErrorEmailText = bfp.getErrorEmailText();
        Assert.assertEquals("Invalid Email", ErrorEmailText);
    }

    @Test
    public void Test_whenPasswordExists() {

        Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
        bfp = resource.adaptTo(BFPModel.class);
        String Password = bfp.getPassword();
        Assert.assertEquals("Password", Password);
    }

    @Test
    public void Test_whenBfpPasswordExists() {

        Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
        bfp = resource.adaptTo(BFPModel.class);
        String BfpPassword = bfp.getBfpPassword();
        Assert.assertEquals("Password", BfpPassword);
    }

    @Test
    public void Test_whenErrorPasswordTextExists() {

        Resource resource = context.resourceResolver().getResource(COMPONENT_PATH);
        bfp = resource.adaptTo(BFPModel.class);
        String ErrorPasswordText = bfp.getErrorPasswordText();
        Assert.assertEquals("Passwords must be at least 4 characters", ErrorPasswordText);
    }
}
