package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class BonusChannelsModel {


    @Inject
    private String bonusChannelsId;


    @Inject
    private Resource allChannels;

    private List<BonusChannelsItemModel> bonusChannelItemsList;


    @PostConstruct
    public final void init() {
        // Populate multifield
        bonusChannelItemsList = getChannels();
    }



    private List<BonusChannelsItemModel> getChannels() {

        List<BonusChannelsItemModel> bonusChannelItems = new ArrayList<>();

        if(null != allChannels){
            Iterator<Resource> resourceIterator = allChannels.listChildren();
            while(resourceIterator.hasNext()){
                bonusChannelItems.add(resourceIterator.next().adaptTo(BonusChannelsItemModel.class));
            }
        }
        return bonusChannelItems;
    }


    public String getBonusChannelsId() {
        return bonusChannelsId;
    }

    public List<BonusChannelsItemModel> getAllChannels() {
        return bonusChannelItemsList;
    }

}
