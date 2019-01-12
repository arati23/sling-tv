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
 * @author Dileep Muraleedharan
 *
 */

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class RotatingMarqueeModel {
	
	private static Logger LOG = LoggerFactory.getLogger(RotatingMarqueeModel.class);
	
	
	 @Inject
     private Resource images;
	 
	 @Inject
	 private String rotatatingMarqueeId;
	 
	 private List<Integer> imagesCount;
	 
	 
	 
	 
	 public List<Integer> getImagesCount() {
			return imagesCount;
	}

    public void setImagesCount(List<Integer> imagesCount) {
			this.imagesCount = imagesCount;
	}
	
	


	public String getRotatatingMarqueeId() {
		return rotatatingMarqueeId;
	}

	private List<RotatingMarqueeItemsModel> carouselImages;
	
	
	public List<RotatingMarqueeItemsModel> getCarouselImages() {
				return this.carouselImages;
			}
	
	
	@PostConstruct
	public final void init() {
			
		carouselImages = constructMarqueeList();
		}
	
	private List<RotatingMarqueeItemsModel> constructMarqueeList() {
		LOG.info("Constructing the marquee list");
	  	List<RotatingMarqueeItemsModel> rotatingMarqueeList = new ArrayList<>();
	 	if (null != images ) {
			Iterator<Resource> resourceChildren = images.listChildren();
			List<Integer> imagesCount = new ArrayList<Integer>();
			int count=0;
			while (resourceChildren.hasNext())
			{
				imagesCount.add(count);
				RotatingMarqueeItemsModel marqueeVO=resourceChildren.next().adaptTo(RotatingMarqueeItemsModel.class);
				marqueeVO.setId("img"+count);
				if(count == 0) {
					marqueeVO.setInfo("true");
				}else {
					marqueeVO.setInfo("false");
				}
				count++;
				rotatingMarqueeList.add(marqueeVO);
				
			}
			
			LOG.info("The number of images is {}",count);
			setImagesCount(imagesCount);
			
		}

		return rotatingMarqueeList;
	}

}
