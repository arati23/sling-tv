package com.sling.tv.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dileep Muraleedharan
 *
 */

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CTADropDownModel {

    private static Logger LOG = LoggerFactory.getLogger(CTADropDownModel.class);

    @Inject
    private Resource ctaPathSet;
    
    @Inject
    private String dynamicCtaDropdownId;
    
    @Inject
    private String dropDownLogo;
	
	public String getDropDownLogo() {
		return dropDownLogo;
	}


	public String getDynamicCtaDropdownId() {
		return dynamicCtaDropdownId;
	}


	private List<CTADropDownItemsModel> dynamicCtaItems;
	
	
	public List<CTADropDownItemsModel> getDynamicCTAItems() {
				return this.dynamicCtaItems;
			}
	

    @PostConstruct
    public final void init() {
        try {
        	dynamicCtaItems =	getCTAListItems();
        } catch(Exception ex) {
            LOG.error("Error in init :::: ", ex);
        }
    }
    
    private List<CTADropDownItemsModel> getCTAListItems() {
	  	List<CTADropDownItemsModel> dymanicCTAList = new ArrayList<>();
	 	if (null != ctaPathSet ) {
			Iterator<Resource> resourceChildren = ctaPathSet.listChildren();
			int count=0;
			while (resourceChildren.hasNext())
			{
			
				CTADropDownItemsModel ctaDropDwonVO=resourceChildren.next().adaptTo(CTADropDownItemsModel.class);
				if(ctaDropDwonVO!=null) {
					
					ctaDropDwonVO.setResourceType(ctaDropDwonVO.getCtaPath());
			    	ctaDropDwonVO.setId("cta"+(1+count));
					if(count == 0) {
						ctaDropDwonVO.setInfo("true");
					}else {
						ctaDropDwonVO.setInfo("false");
					}
					count++;
					dymanicCTAList.add(ctaDropDwonVO);
				}
					
			}
			
		}

		return dymanicCTAList;
	}

    
}
