/**
* 
 */
package com.sling.tv.core.models;
 
import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
 
/**
* @author Nithya
*
*/
 
 
@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HoudiniModel {
                
                @Inject
                private String houdiniCompId;
                
                @Inject
                private String showText;
                
                @Inject
                private String hideText;
 
                public String getHoudiniCompId() {
                	return houdiniCompId;
                }
 
                public String getShowText() {
                	return showText;
                }
 
                public String getHideText() {
                	return hideText;
                }
              
 
}

