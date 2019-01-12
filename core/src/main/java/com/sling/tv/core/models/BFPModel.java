package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class BFPModel {
	
	@Inject
	private String bfpId;
	
	@Inject
	private String previewId;

	@Inject
	private String channelId;

	@Inject
	private String registerTime;

	@Inject
	private String cartTime;

	@Inject
	private String ctaUrl;

	@Inject
	private String posterImage;

	@Inject
	private String overlayImage;

	@Inject
	private String bfpMessage;

	@Inject
	private String bfpDefaultError;

	@Inject
	private String email;

	@Inject
	private String bfpEmail;

	@Inject
	private String errorEmailText;

	@Inject
	private String password;

	@Inject
	private String bfpPassword;

	@Inject
	private String errorPasswordText;

	@Inject
	private String buttonText;


	public String getBfpId() {
		return bfpId;
	}

	public String getPreviewId() {
		return previewId;
	}

	public String getChannelId() {
		return channelId;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public String getCartTime() {
		return cartTime;
	}

	public String getCtaUrl() {
		return ctaUrl;
	}

	public String getPosterImage() {
		return posterImage;
	}

	public String getOverlayImage() {
		return overlayImage;
	}

	public String getBfpMessage() {
		return bfpMessage;
	}

	public String getBfpDefaultError() {
		return bfpDefaultError;
	}

	public String getEmail() {
		return email;
	}

	public String getBfpEmail() {
		return bfpEmail;
	}

	public String getErrorEmailText() {
		return errorEmailText;
	}

	public String getPassword() {
		return password;
	}

	public String getBfpPassword() {
		return bfpPassword;
	}

	public String getErrorPasswordText() {
		return errorPasswordText;
	}

	public String getButtonText() {
		return buttonText;
	}


}
