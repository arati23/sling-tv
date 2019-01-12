package com.sling.tv.core.utils;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sling.tv.core.models.AnalyticsModel;


public class LinkCheckerUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkCheckerUtil.class);
	
	public String absoluteUrl(final String link)
	{
		if (link == null)
		{
			return "";
		}
		String newLink = link;
		if (!link.isEmpty() && link.contains("|"))
		{
			String[] splits = newLink.split("\\|");
			newLink = String.join("%7C", splits);
		}
		
		
		try {
			URI uri = new URI(newLink);
			
	         String path = uri.getPath();
	         String fragment = uri.getFragment();
	         String scheme = uri.getScheme();
			
	         //remove jcr content from path
	         if (StringUtils.endsWith(path, "/jcr:content")) {
	             path = path.substring(0, path.length() - 11);
	             
	         }
	         
	         
	         //if the existing path is relative, and has no extension, add .html
	         if (scheme==null && StringUtils.startsWith(path, "/content") && path.indexOf(".")  == -1) {
	             path += ".html";
	         }
	         
	         
	         
	       //build and return the new URI
	         URI newUri = new URI(uri.getScheme(), uri.getAuthority(), path, uri.getQuery(), fragment);
	         
	         return newUri.toString();
	         
	         
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Error thrown from LinkCheckerUtil");;
		}
		
		
		
		return newLink;
		
	}

}
