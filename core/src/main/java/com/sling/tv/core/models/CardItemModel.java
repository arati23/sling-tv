package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CardItemModel {

	@Inject
	private String textCardTitle;

	@Inject
	private String textCardPackage;

	@Inject
	private String textCardDesc;

	@Inject
	private String logoCardTitle;

	@Inject
	private String logoCardPackage;

	@Inject
	private String referencePath;

	public String getTextCardTitle() {
		return textCardTitle;
	}

	public String getTextCardPackage() {
		return textCardPackage;
	}

	public String getTextCardDesc() {
		return textCardDesc;
	}

	public String getLogoCardTitle() {
		return logoCardTitle;
	}

	public String getLogoCardPackage() {
		return logoCardPackage;
	}

	public String getReferencePath() {
		return referencePath;
	}
}
