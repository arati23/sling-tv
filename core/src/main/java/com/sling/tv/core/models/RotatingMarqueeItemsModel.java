/**
 * 
 */
package com.sling.tv.core.models;

import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import com.sling.tv.core.utils.LinkCheckerUtil;

/**
 * @author Dileep Muraleedharan
 *
 */


@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class RotatingMarqueeItemsModel {
	
	
	@Inject
	private String mediaType;
	
	@Inject
	private String carousalImage;
	
	@Inject
	private String altName;
	
	@Inject
	private String imageURL;
	
	@Inject
	private String videoPath;
	
	@Inject
	private String videoURL;

	@Inject
	private String fallBackImageUrl;
	
	private String id;
	
	private String info;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMediaType() {
		return mediaType;
	}

	public String getCarousalImage() {
		return carousalImage;
	}

	public String getAltName() {
		return altName;
	}

	public String getImageURL() {
		return imageURL;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public String getFallBackImageUrl() {
		return fallBackImageUrl;
	}
	

	

	
	
}
