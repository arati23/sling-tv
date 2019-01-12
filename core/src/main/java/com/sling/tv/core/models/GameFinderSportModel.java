package com.sling.tv.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;


@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class GameFinderSportModel {

    @Inject
    private String keyName;

    public String getKeyName() {

        return this.keyName;

    }
    
    @Inject
    private String showHide;

    public String getShowHide() {

        return this.keyName;

    }
    
    @Inject
    private String checked;

    public String getChecked() {

        return this.keyName;

    }

    @Inject
    private String sportName;

    public String getSportName() {

        return this.sportName;

    }
}
