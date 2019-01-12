package com.sling.tv.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sling.tv.core.services.OsgiConfigService;

@Model(adaptables = SlingHttpServletRequest.class)
public class EnvironmentModel {
    private static Logger LOG= LoggerFactory.getLogger(EnvironmentModel.class);

    @Inject
    OsgiConfigService osgiConfigService;


    private String tealiumEnv;

    private String tealiumSyncScriptUrl;

    private String tealiumAsyncScriptUrl;

    public String getTealiumSyncScriptUrl() {
        return tealiumSyncScriptUrl;
    }

    public String getTealiumAsyncScriptUrl() {
        return tealiumAsyncScriptUrl;
    }

    public String getTealiumScriptEnvironment() {
        return tealiumEnv;
    }

    @PostConstruct
    protected void init(){

        this.tealiumEnv=osgiConfigService.getSlingTealiumEnvironment();
        LOG.debug("tealiumEnvironment{}",tealiumEnv);
       this.tealiumSyncScriptUrl = "//tags.tiqcdn.com/utag/dish/sling/"+tealiumEnv+"/utag.sync.js";
        LOG.debug("tealiumSyncScriptUrl{}",tealiumSyncScriptUrl);
        this.tealiumAsyncScriptUrl = "//tags.tiqcdn.com/utag/dish/sling/"+tealiumEnv+"/utag.js";
        LOG.debug("tealiumAsyncScriptUrl{}",tealiumAsyncScriptUrl);

    }

}