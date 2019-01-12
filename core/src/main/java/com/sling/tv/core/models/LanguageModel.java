package com.sling.tv.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sling.tv.core.utils.LinkCheckerUtil;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class LanguageModel {
	
	private static Logger LOG = LoggerFactory.getLogger(LanguageModel.class);
	
	LinkCheckerUtil lcu = new LinkCheckerUtil();

	@Inject
	private String languageName;
	
	@Inject
	private String languageLink;


	public String getLanguageName() {
		return languageName;
	}

	public String getLanguageLink() {
		return lcu.absoluteUrl(languageLink);
	}
	
}
