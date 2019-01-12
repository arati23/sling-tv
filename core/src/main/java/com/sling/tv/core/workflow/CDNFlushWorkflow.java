package com.sling.tv.core.workflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.http.HttpResponse;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.sling.tv.core.services.CDNFlushService;

@Component
@Service
@Properties({ @Property(name = Constants.SERVICE_DESCRIPTION, value = "CDN Flush Agent Workflow"),
		@Property(name = "process.label", value = "CDN Flush Agent Workflow") })

public class CDNFlushWorkflow implements WorkflowProcess {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute(WorkItem item, WorkflowSession wfSession, MetaDataMap args) throws WorkflowException {
		WorkflowData wfd = item.getWorkflowData();
		String workflowPayLoadDataType = wfd.getPayloadType();
		boolean isJCRPATH = workflowPayLoadDataType.equals("JCR_PATH");
		String paths[] = null;
		if (isJCRPATH) {
			String flushPath = (String) wfd.getPayload();
			String uri = args.get("URI", "not set");
			String customPrefix = args.get("DISCOVERY", "");
			String advancedFlushPaths = args.get("PATHS", "");
			
			if (!advancedFlushPaths.isEmpty())
			{
				paths = advancedFlushPaths.split("\n");
				
			}else if (!customPrefix.isEmpty() && flushPath.startsWith(customPrefix))
			{
				flushPath = parseFlushPath(flushPath, customPrefix);
				paths = new String[1];
				paths[0] = flushPath;
			}else {
				paths = new String[1];
				paths[0] = flushPath;
			}
			
			CDNFlushService cdnFlush = new CDNFlushService();
			
			HttpResponse httpResponse = cdnFlush.sendRequest(uri, paths);
			String response = parseHttpResponse(httpResponse);
			LOG.info("Response is {}", response);

		}

	}

	private String parseFlushPath(String flushPath, String customPrefix) {
		// TODO Auto-generated method stub
		
		return flushPath.replace(customPrefix, "");
	}

	private String parseHttpResponse(HttpResponse httpResponse) {
		BufferedReader br;
		String output = "";
		String responseString = "";
		try {
			br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));

			while ((output = br.readLine()) != null) {
				responseString = responseString + output;
			}
		} catch (UnsupportedOperationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseString;

	}
}
