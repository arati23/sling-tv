package com.sling.tv.core.services;

import java.io.IOException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(label = "CDN Flush Service", immediate = true, enabled = true)
@Property(name = "CDN purge Service. To be used by any service sending requests to CDN", description = "This service will purge the CDN based on path received")

public class CDNFlushService {
	private static final Logger log = LoggerFactory.getLogger(CDNFlushService.class);
	
	@Reference
	OsgiConfigService osgiConfigService;

	public HttpResponse sendRequest(String uri, String paths[]) {
		// TODO Auto-generated method stub

		String parsedURI = "";
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			if (paths.length > 0) {
				parsedURI = uri;
				
				for(String p : paths)
				{
					parsedURI = parsedURI + "&path=" + parsePagePath(p);
				}
				
				HttpGet getRequest = new HttpGet(parsedURI);
				
				HttpResponse httpResponse = httpClient.execute(getRequest);
				log.info("Request sent to {}", parsedURI);
				httpClient.close();
				return httpResponse;
			} else {
				return null;
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			log.error("inside exception ClientProtocolException");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("inside exception IOException");
			e.printStackTrace();
		}catch (Exception e) {
			log.error("General Exception");
			e.printStackTrace();
		}
		
		
		return null;

	}

	private String parsePagePath(String pagePath) {
		String parsedPagePath = "";
		parsedPagePath = pagePath.trim();
		return parsedPagePath;
	}
}
