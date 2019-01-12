package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class GridInteractionModel {

    /*
     * @author Krishna Gunturu
     *
     */

    /** Default log. */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private String gridInteractionId;

    public String getGridInteractionId() {
        return gridInteractionId;
    }
}