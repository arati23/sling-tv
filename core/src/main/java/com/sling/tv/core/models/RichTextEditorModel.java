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

/**
 * @author Dileep Muraleedharan
 *
 */

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class RichTextEditorModel {
	
	private String textIsRich;
	
		
	 public String getTextIsRich() {
		return textIsRich;
	}

	public String getText() {
		return text;
	}

	public String getSectionId() {
		return sectionId;
	}

	@Inject
     private String  text;
	 
	 @Inject
     private String  sectionId;
	
	
}
