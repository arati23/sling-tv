/**
 * 
 */
package com.sling.tv.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Nithya Nair
 *
 */

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class ProgramRibbonModel {
	
	private static final Logger	LOGGER	= LoggerFactory.getLogger(ProgramRibbonModel.class);
	
	@Inject
	private Resource allImagePaths;
	
	 
    @Inject
	private String programRibbonId;


	public Resource getAllImagePaths() {
		return allImagePaths;
	}


	public String getProgramRibbonId() {
		return programRibbonId;
	}
    
    
	private List<ProgramRibbonModelItem> PgmRibbonLinks;


	public List<ProgramRibbonModelItem> getPgmRibbonLinks() {
		return PgmRibbonLinks;
	}
	
	@PostConstruct
	public final void init() {
			
		PgmRibbonLinks = getProgramRibbonLinks();
		
		}
	
	private List<ProgramRibbonModelItem> getProgramRibbonLinks() {
	  	List<ProgramRibbonModelItem> lstPgmRibLinks = new ArrayList<>();
	 	if (null != allImagePaths ) {
			Iterator<Resource> resourceChildren = allImagePaths.listChildren();
			while (resourceChildren.hasNext())
			{
				lstPgmRibLinks.add(resourceChildren.next().adaptTo(ProgramRibbonModelItem.class));
			}
			
		}
	 	LOGGER.debug("Number of Images added {}",lstPgmRibLinks.size());
		return lstPgmRibLinks;
	}

}
