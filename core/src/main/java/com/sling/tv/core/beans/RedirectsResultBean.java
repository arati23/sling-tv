package com.sling.tv.core.beans;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class RedirectsResultBean {

    //protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private String source;

    @Inject
    private String destination;

    @Inject
    private String temporary;

    @Inject
    private String legacy;

    @Inject
    private String query;

    @Inject
    private String created_date;


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTemporary() {
        return temporary;
    }

    public void setTemporary(String temporary) {
        this.temporary = temporary;
    }

    public String getLegacy() {
        return legacy;
    }

    public void setLegacy(String legacy) {
        this.legacy = legacy;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCreated_Date() {
        return created_date;
    }

    public void setCreated_Date(String created_date) {
        this.created_date = created_date;
    }

}