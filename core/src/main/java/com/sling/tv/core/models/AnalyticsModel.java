package com.sling.tv.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

/**
 *
 * This Sling Model is for data layer.
 *
 * @author vhs
 *
 */
@Model(adaptables = SlingHttpServletRequest.class)
public class AnalyticsModel {

	public static final int LOCALE_LEVEL = 3;
	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticsModel.class);
    private ObjectMapper objMapper =  new ObjectMapper();
    
	@Inject
	private Page currentPage;

	private String dataLayer;

	@PostConstruct
	private void init() {
		// populate the product info and page info
        ObjectNode matches = objMapper.createObjectNode();
		try {
			
			final ObjectNode dataLayerObj = objMapper.createObjectNode();
			dataLayerObj.putPOJO("pageInfo", createPageInfo());
			dataLayerObj.putPOJO("category", createCategory());
			dataLayer = dataLayerObj.toString();
			LOGGER.debug("dataLayer ::: " + dataLayer);

		} catch (final JsonProcessingException e) {
			LOGGER.error("failed to create data layer.", e);
			dataLayer = "{ 'error': 'failed to create data layer''}";
		}

	}

	private ObjectNode createPageInfo() throws JsonProcessingException {
		final ObjectNode pageInfo = objMapper.createObjectNode();
		Page depthPage = currentPage.getAbsoluteParent(LOCALE_LEVEL - 1);
		if (depthPage!=null && currentPage != null)
		{
			pageInfo.put("pageName", depthPage.getName() + ":" + currentPage.getName());
		}else {
			pageInfo.put("error", "Page cannot be found");
		}


		return pageInfo;
	}

	private ObjectNode createCategory() throws JsonProcessingException {
		final ObjectNode category = objMapper.createObjectNode();
		String firstLevel = getPageNameByDepth(LOCALE_LEVEL);

		category.put("PageType", "");
		category.put("primaryCategory", firstLevel);
		category.put("subCategory1", "");

		StringBuilder subCategory = new StringBuilder();
		String secondLevel = getPageNameByDepth(LOCALE_LEVEL + 1);

		if (secondLevel != null) {
			subCategory.append(firstLevel);
			subCategory.append(":");
			subCategory.append(secondLevel);
			category.put("subCategory1", subCategory.toString());
		}
		return category;

	}

	private String getPageNameByDepth(int depth) {
		String depthPageName = null;
		Page depthPage = currentPage.getAbsoluteParent(depth);
		if (depthPage != null) {
			depthPageName = depthPage.getName();
		}
		return depthPageName;
	}

	public final String getDataLayer() {
		return dataLayer;
	}

}
