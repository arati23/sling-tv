package com.sling.tv.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sling.tv.core.SlingTvConstants;
import com.sling.tv.core.beans.LanguageBean;

/**
 * This class is used to map language page properties to Language Bean resource
 *
 * @author deou
 *
 */
@Model(adaptables = Resource.class)
public class LanguageComponentModel {
    private static Logger LOG = LoggerFactory.getLogger(LanguageComponentModel.class);

    @Inject
    @Optional
    private List<Resource> languages;

    private List<LanguageModel> languageList;

    @PostConstruct
    public final void init() {

        this.languageList = populateLanguages();
    }

    private List<LanguageModel> populateLanguages() {
    	
    	List<LanguageModel> tLanguageModelList = new ArrayList<>();
    	
    	if (this.languages != null) {
            for (final Resource language : languages) {
                LanguageModel lm = language.adaptTo(LanguageModel.class);
                tLanguageModelList.add(lm);
            }
    	}

            
            return tLanguageModelList;
    }

    public List<LanguageModel> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(final List<LanguageModel> languageList) {
        this.languageList = languageList;
    }
}
