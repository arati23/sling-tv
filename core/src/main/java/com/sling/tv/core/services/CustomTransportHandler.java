package com.sling.tv.core.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.AgentConfig;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.ReplicationLog;
import com.day.cq.replication.ReplicationResult;
import com.day.cq.replication.ReplicationTransaction;
import com.day.cq.replication.TransportContext;
import com.day.cq.replication.TransportHandler;

@Service(TransportHandler.class)
@Component(label = "My Transport Handler", immediate = true, enabled = true)
@Property(name = "Cloudfront purge end point", description = "This is the cloudfront purge end point URI which the service will hit")

public class CustomTransportHandler implements TransportHandler{
	private static final Logger log = LoggerFactory.getLogger(CustomTransportHandler.class);
	
	
	@Reference
	OsgiConfigService osgiConfigService;

	@Override
	public boolean canHandle(AgentConfig config) {
		// TODO Auto-generated method stub
		String URI = config.getTransportURI();
		String cdnURIProto = osgiConfigService.getSlingCDNPurgeURIProtocol();
		return URI.startsWith(cdnURIProto);
	}

	@Override
	public ReplicationResult deliver(TransportContext ctx, ReplicationTransaction tx) throws ReplicationException {
		// TODO Auto-generated method stub
		
        final ReplicationActionType replicationType = tx.getAction().getType();

        if (replicationType == ReplicationActionType.TEST) {
            return doTest(ctx, tx);
        } else if (replicationType == ReplicationActionType.ACTIVATE ||
                replicationType == ReplicationActionType.DEACTIVATE) {
            return doActivate(ctx, tx);
        } else {
            throw new ReplicationException("Replication action type " + replicationType + " not supported.");
        }		
		
	}
	
	private ReplicationResult doActivate(TransportContext ctx, ReplicationTransaction tx) {
		// TODO Auto-generated method stub
		
        final ReplicationLog tlog = tx.getLog();
        final HttpResponse response = sendRequest(ctx, tx);


        if (response != null) {
            final int statusCode = response.getStatusLine().getStatusCode();
            
            tlog.info(responseParsedToString(response));
            tlog.info("---------------------------------------");

            if (statusCode == HttpStatus.SC_OK) {
                String  paths[] = tx.getAction().getPaths();
                for(String s: paths)
                {
                	tlog.info("Sending request for path " + s);
                }
                tlog.info("Status OK");
                tlog.info("---------------------------------------");
                return ReplicationResult.OK;
            }
        }

        return new ReplicationResult(false, 0, "Replication test failed");
	}

	private String responseParsedToString(HttpResponse response) {
        BufferedReader br;
        String output="";
        String myJSON="" ;
		try {
			br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

	            while ((output = br.readLine()) != null) {
	                myJSON = myJSON + output;
	            }			
		} catch (UnsupportedOperationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return myJSON;

		
	}
	
	private ReplicationResult doTest(TransportContext ctx, ReplicationTransaction tx) {
		// TODO Auto-generated method stub
		
        final ReplicationLog tlog = tx.getLog();
        final HttpResponse response = sendRequest(ctx, tx);

        if (response != null) {
            final int statusCode = response.getStatusLine().getStatusCode();
            
            tlog.info(responseParsedToString(response));
            tlog.info("---------------------------------------");

            if (statusCode == HttpStatus.SC_OK) {
                tlog.info("Status OK");
                tlog.info("---------------------------------------");
                return ReplicationResult.OK;
            }
        }

        return new ReplicationResult(false, 0, "Replication test failed");
	}
	
	private String parsePagePath(String pagePath)
	{
		String parsedPagePath = "";
		
		parsedPagePath = pagePath.replace(osgiConfigService.getSlingRootPagePath(), "/");
		return parsedPagePath;
	}

	private HttpResponse sendRequest(TransportContext ctx, ReplicationTransaction tx) {
		// TODO Auto-generated method stub
		
		log.info("inside send request");
		final ReplicationLog tlog = tx.getLog();
		try {
	        log.info("inside try");
	        
	        String URIProtocol = osgiConfigService.getSlingCDNPurgeURIProtocol();
			CloseableHttpClient  httpClient = HttpClientBuilder.create().build();
			String URI = ctx.getConfig().getTransportURI();
			String parsedURI = URI.replace(URIProtocol, "https");
			String pagePaths[] = tx.getAction().getPaths();
			if (pagePaths.length > 0)
			{
				parsedURI = parsedURI + "&path=" + parsePagePath(pagePaths[0]);
				tlog.info("trying to purge using this URI " + parsedURI);
				HttpGet getRequest = new HttpGet(parsedURI);
				return httpClient.execute(getRequest);
			}else {
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
		}
		return null;	

}
}
