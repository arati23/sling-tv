/**
 * 
 */
package com.sling.tv.core.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.powermock.api.mockito.PowerMockito.when;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.powermock.api.mockito.PowerMockito;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.sling.tv.core.workflow.CDNFlushWorkflow;

@RunWith(MockitoJUnitRunner.class)
public class CDNFlushWorkflowServiceTest {

    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this);

    private MockServerClient mockServerClient;
    
	private CDNFlushWorkflow cdnFlushWorkflow = new CDNFlushWorkflow();

	private String uri;

	@Mock
	WorkItem item;

	@Mock
	WorkflowSession wfSession;

	@Mock
	MetaDataMap args;
	
	@Mock
	WorkflowData wfd;

	@Before
    public void setUp() throws Exception {
	   
   	int port =  mockServerRule.getPort().intValue();
   	uri = "http://localhost:" + Integer.toString(port) + "?x=5";

	   
    }

	@Test
	public void test_with_normal_paths() throws Exception {

		String payloadString = "/content";
		
		when(item.getWorkflowData()).thenReturn(wfd);
		when(wfd.getPayloadType()).thenReturn("JCR_PATH");
		when(wfd.getPayload()).thenReturn(payloadString);
		when(args.get("PATHS", "")).thenReturn("");
		when(args.get("DISCOVERY", "")).thenReturn("");
		when(args.get("URI", "not set")).thenReturn(uri);
		
		String paths = args.get("URI", "not set");
		
		cdnFlushWorkflow.execute(item, wfSession, args);
		mockServerClient.verify(request().withQueryStringParameter("path", payloadString));

	}

	@Test
	public void test_with_overriding_paths() throws Exception {
		
		String payloadString = "/discovery/content";

		when(item.getWorkflowData()).thenReturn(wfd);
		when(wfd.getPayloadType()).thenReturn("JCR_PATH");
		when(wfd.getPayload()).thenReturn(payloadString);
		when(args.get("PATHS", "")).thenReturn("");
		when(args.get("DISCOVERY", "")).thenReturn("/discovery");
		when(args.get("URI", "not set")).thenReturn(uri);

		cdnFlushWorkflow.execute(item, wfSession, args);
		mockServerClient.verify(request().withQueryStringParameter("path", payloadString.replace("/discovery", "")));
	}

//	@Test
//	public void test_with_no_paths() throws Exception {
//
//		String[] paths = new String[0];
//
//		// PowerMockito.when(mockHttpClient.execute(mockHttpGetRequest)).thenReturn(mockHttpResponse);
//		HttpResponse actualHttpResponse = (CloseableHttpResponse) cdnFlushService.sendRequest(uri, paths);
//		assertNull(actualHttpResponse);
//	}

}
