package com.sling.tv.core.servlets;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.io.PrintWriter;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.servlethelpers.MockSlingHttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import com.sling.tv.core.servlet.PCGridChannelServlet;

/**
 * 
 */

/**
 * @author Nithya Nair
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PCGridChannelServletTest {
	
	@InjectMocks
	public PCGridChannelServlet mockPcGridChannelServlet =  new PCGridChannelServlet();
	
	@Mock
	private SlingHttpServletRequest mockRequest;
	
	@Mock
	private SlingHttpServletResponse mockResponse;
	
	@Mock
	private ResourceResolver mockResourceResolver;
	
	
	@Mock
	private Session mockJcrSession;
	
	@Mock
	private Node mockNode;
	
	@Mock
	private Property mockProperty;
	
	@Mock
	private Binary mockBinary;
	
	@Mock
	private PrintWriter mockPrintWriter;
	
	
	
	@Before
	public void setUp(){
	mockRequest = PowerMockito.mock(SlingHttpServletRequest.class);
	mockResponse = PowerMockito.mock(SlingHttpServletResponse.class);
	mockResourceResolver = PowerMockito.mock(ResourceResolver.class);
	mockJcrSession = PowerMockito.mock(Session.class);
	mockNode = PowerMockito.mock(Node.class);
	mockProperty=PowerMockito.mock(Property.class);
	mockBinary=PowerMockito.mock(Binary.class);
	mockPrintWriter=PowerMockito.mock(PrintWriter.class);
	}
	
	@Test
	public void testDoGet() throws Exception{
		MockSlingHttpServletResponse response = new MockSlingHttpServletResponse();
		PowerMockito.when(mockRequest.getResourceResolver()).thenReturn(mockResourceResolver);
		PowerMockito.when(mockRequest.getParameter("classification")).thenReturn("malayalam");
		PowerMockito.when(mockRequest.getParameter("planId")).thenReturn("monthly");
		PowerMockito.when(mockRequest.getParameter("packageId")).thenReturn("sling-mss");
		PowerMockito.when(mockRequest.getParameter("channelLogoPath")).thenReturn("/content/dam/sling-tv");
		PowerMockito.when(mockResourceResolver.adaptTo(Session.class)).thenReturn(mockJcrSession);
		PowerMockito.when(mockJcrSession.getNode(Matchers.anyString())).thenReturn(mockNode);
		ClassLoader classLoader = getClass().getClassLoader();
    	InputStream inputStream = classLoader.getResourceAsStream("pcgridchannelservlet.json");
		PowerMockito.when(mockNode.getProperty("jcr:data")).thenReturn(mockProperty);
		PowerMockito.when(mockProperty.getBinary()).thenReturn(mockBinary);
		PowerMockito.when(mockBinary.getStream()).thenReturn(inputStream);
		PowerMockito.when(mockResponse.getWriter()).thenReturn(mockPrintWriter);
		Whitebox.invokeMethod(mockPcGridChannelServlet,"doGet",mockRequest,response);
		String strResponse= response.getOutputAsString();
		assertNotNull(strResponse);
	}
	
}
