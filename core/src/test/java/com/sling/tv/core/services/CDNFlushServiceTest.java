/**
 * 
 */
package com.sling.tv.core.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;	


@RunWith(MockitoJUnitRunner.class)
public class CDNFlushServiceTest {
	
    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this);

    private MockServerClient mockServerClient;
    
    private CDNFlushService cdnFlushService = new CDNFlushService();
	
    private String uri;
    
   @Before
    public void setUp() throws Exception {
	   
   	int port =  mockServerRule.getPort().intValue();
   	uri = "http://localhost:" + Integer.toString(port);
	   
	   
       mockServerClient.when(
               request().withMethod("GET").withPath("/anon")
       ).respond(
               response().withStatusCode(200).withBody("OK")
       );
       mockServerClient.when(
               request().withMethod("GET").withPath("/anonJson")
       ).respond(
               response().withStatusCode(200).withBody("{ 'foo' : 'bar' }")
       );
	   
    }
    
    @Test
    public void test_with_multi_paths() throws Exception{
    	
    	String[] paths = {"/content", "/content/sling-tv"};
    	int port =  mockServerRule.getPort().intValue();
    	
    	//PowerMockito.when(mockHttpClient.execute(mockHttpGetRequest)).thenReturn(mockHttpResponse);
    	HttpResponse actualHttpResponse = (CloseableHttpResponse) cdnFlushService.sendRequest(uri, paths);
    	assertNotNull(actualHttpResponse);
    }
    
    @Test
    public void test_with_null_paths() throws Exception{
    	
    	String[] paths = null;
    	
    	//PowerMockito.when(mockHttpClient.execute(mockHttpGetRequest)).thenReturn(mockHttpResponse);
    	HttpResponse actualHttpResponse = (CloseableHttpResponse) cdnFlushService.sendRequest(uri, paths);
    	assertNull(actualHttpResponse);
    }
    
    @Test
    public void test_with_no_paths() throws Exception{
    	
    	String[] paths = new String[0];
    	
    	//PowerMockito.when(mockHttpClient.execute(mockHttpGetRequest)).thenReturn(mockHttpResponse);
    	HttpResponse actualHttpResponse = (CloseableHttpResponse) cdnFlushService.sendRequest(uri, paths);
    	assertNull(actualHttpResponse);
    }
    
}
