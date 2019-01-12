package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

public class CardComponentModel extends WCMUsePojo {

	private static Logger LOG = LoggerFactory.getLogger(CardComponentModel.class);

	@Override
	public void activate() throws Exception {

	}

	/**
	 * Gets the Card items resource.
	 *
	 * @return the Card items resource
	 */
	public Resource getTextCardItemsResource() {

		if (this.getResource().hasChildren()) {
			final Resource textCardItemsRes = this.getResource().getChild("textCardItems");
			return textCardItemsRes;
		}
		return null;
	}

	public Resource getLogoCardItemsResource() {

		if (this.getResource().hasChildren()) {
			final Resource logoCardItemsRes = this.getResource().getChild("logoCardItems");
			return logoCardItemsRes;
		}
		return null;
	}

}
