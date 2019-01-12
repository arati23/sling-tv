/**
 * 
 */
package com.sling.tv.core.servlets;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Session;

import org.apache.commons.io.IOUtils;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sling.tv.core.servlet.PCGridDynamicPackagesServlet;

/**
 * @author Nithya Nair
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PCGridDynamicPackagesServletTest {
	
	@InjectMocks 
	PCGridDynamicPackagesServlet mockPCGridDynamicPkgsSrvlt = new PCGridDynamicPackagesServlet();
	
	@Mock
	private SlingHttpServletRequest mockRequest;
	
	@Mock
	private SlingHttpServletResponse mockResponse;
	
	@Mock
	private PrintWriter mockPrintWriter;
	
	@Mock
	private JsonNode mockJsonNode;
	
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
	private ArrayNode mockArrayNode ;
	
	@Mock
	private ObjectMapper mockObjectMapper ;
	
	
	@Before
	public void setUp(){
	
		mockRequest = PowerMockito.mock(SlingHttpServletRequest.class);
		mockResponse = PowerMockito.mock(SlingHttpServletResponse.class);
		mockPrintWriter=PowerMockito.mock(PrintWriter.class);
		mockResourceResolver = PowerMockito.mock(ResourceResolver.class);
		mockJcrSession = PowerMockito.mock(Session.class);
		mockNode = PowerMockito.mock(Node.class);
		mockProperty=PowerMockito.mock(Property.class);
		mockBinary=PowerMockito.mock(Binary.class);
		mockObjectMapper=PowerMockito.mock(ObjectMapper.class);
		mockArrayNode=PowerMockito.mock(ArrayNode.class);
		
	}
	
	@Test
	public void testDoGet() throws Exception{
		MockSlingHttpServletResponse response = new MockSlingHttpServletResponse();
		PowerMockito.when(mockRequest.getParameter("planId")).thenReturn("one-week-promo");
		PowerMockito.when(mockRequest.getParameter("classificationOne")).thenReturn("us");
		ClassLoader classLoader = getClass().getClassLoader();
	    InputStream inputS = classLoader.getResourceAsStream("pcgridtest.json");
		String jsonToString = IOUtils.toString(inputS, StandardCharsets.UTF_8);
		PowerMockito.when(mockRequest.getParameter("items")).thenReturn(jsonToString);
		PowerMockito.when(mockResponse.getWriter()).thenReturn(mockPrintWriter);
		PowerMockito.when(mockRequest.getResourceResolver()).thenReturn(mockResourceResolver);
		PowerMockito.when(mockResourceResolver.adaptTo(Session.class)).thenReturn(mockJcrSession);
		PowerMockito.when(mockJcrSession.getNode(Matchers.anyString())).thenReturn(mockNode);
	    InputStream inputStream =classLoader.getResourceAsStream("pcgriddynamictest.json");
		PowerMockito.when(mockNode.getProperty("jcr:data")).thenReturn(mockProperty);
		PowerMockito.when(mockProperty.getBinary()).thenReturn(mockBinary);
		PowerMockito.when(mockBinary.getStream()).thenReturn(inputStream);
		Whitebox.invokeMethod(mockPCGridDynamicPkgsSrvlt,"doGet",mockRequest,response);
		String strResponse= response.getOutputAsString();
		assertNotNull(strResponse);
	}
	

}
