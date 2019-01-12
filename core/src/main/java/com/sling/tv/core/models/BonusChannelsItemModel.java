package com.sling.tv.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class BonusChannelsItemModel {

    private static Logger log = LoggerFactory.getLogger(BonusChannelsItemModel.class);

    public String getChannelName() {
        return channelName;
    }

    public String getChannelAlt() {
        return channelAlt;
    }

    public String getChannelLogo() {
        return channelLogo;
    }

    @Inject
    private String channelName;

    @Inject
    private String channelAlt;

    @Inject
    private String channelLogo;

}
