/**
 * 
 */
package com.sling.tv.core.workflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.io.PrintWriter;

import javax.jcr.Session;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit.SlingContext;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.service.component.ComponentContext;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.sling.tv.core.servlet.MatchInfoServlet;	


@RunWith(MockitoJUnitRunner.class)
public class CDNFlushWorkflowTest {
	
	 @InjectMocks
    public MatchInfoServlet matchInfoServlet = new MatchInfoServlet();
    
    @Mock
    private SlingHttpServletRequest mockRequest;
    
    @Mock
    private SlingHttpServletResponse mockResponse;
    
    @Mock
    private ResourceResolver mockResourceResolver;
    
    @Mock
    ComponentContext componentContext;
    
    @Mock
    private Session mockJcrSession;
    
    @Mock
    private Asset mockAsset;
    
    @Mock
    private Resource mockResource;
     
    @Mock
    private Rendition mockRendition;
    
    @Mock
    PrintWriter mockPrintWriter;
    
    @Rule
    public final SlingContext context = new SlingContext(ResourceResolverType.JCR_MOCK);
        
   @Before
    public void setUp() {
    	mockRequest=PowerMockito.mock(SlingHttpServletRequest.class);
    	mockResponse=PowerMockito.mock(SlingHttpServletResponse.class);
    	mockJcrSession=PowerMockito.mock(Session.class);
    	mockResourceResolver=PowerMockito.mock(ResourceResolver.class);
    	mockAsset=PowerMockito.mock(Asset.class);
      	mockResource=PowerMockito.mock(Resource.class);
      	mockRendition=PowerMockito.mock(Rendition.class);
      	mockPrintWriter=PowerMockito.mock(PrintWriter.class);
    }
    
    @Test
    public void testDOGetWithJson() throws Exception{
    	
    	MockSlingHttpServletRequest request = new MockSlingHttpServletRequest(mockResourceResolver, context.bundleContext());
    	MockSlingHttpServletResponse response = new MockSlingHttpServletResponse();
    	ClassLoader classLoader = getClass().getClassLoader();
    	InputStream inputS = classLoader.getResourceAsStream("game-finder.xlsx"); //The resource is there under resource folder
    	PowerMockito.when(mockRequest.getResourceResolver()).thenReturn(mockResourceResolver);
    	PowerMockito.when(mockRequest.getParameter("filePath")).thenReturn("/content/dam/sling-tv/game-fn/game-finder.xlsx");//The dummy path, nothing to have it physically
    	//PowerMockito.when(mockResourceResolver.adaptTo(Session.class)).thenReturn(mockJcrSession);
    	PowerMockito.when(mockResourceResolver.getResource(Matchers.anyString())).thenReturn(mockResource);
    	PowerMockito.when(mockResource.adaptTo(Asset.class)).thenReturn(mockAsset);
    	PowerMockito.when(mockAsset.getOriginal()).thenReturn(mockRendition);
    	PowerMockito.when(mockResponse.getWriter()).thenReturn(mockPrintWriter);
    	PowerMockito.when(mockRendition.getStream()).thenReturn(inputS);
        Whitebox.invokeMethod(matchInfoServlet, "doGet",request,response);
    	//matchInfoServlet.doGet(request, response); // this step also can be used or use the Whitebox.invokeMethod
        JSONObject jsonResp=new JSONObject(response.getOutputAsString());
        assertNotNull(jsonResp);
        assertEquals(822, jsonResp.getInt("count"));
        JSONArray jsonGamesAry = jsonResp.getJSONArray("games");
        assertNotNull(jsonGamesAry);
        JSONObject gameObj=jsonGamesAry.getJSONObject(0);
        assertEquals("2018-08-08 00:00:00", gameObj.getString("game_datetime"));
        assertEquals("NBC", gameObj.getString("network"));
        assertEquals("call sign test", gameObj.getString("call_sign"));
        assertEquals("Chicago,Baltimore", gameObj.getString("display_data_desktop"));
        assertEquals("Chicago,Baltimore", gameObj.getString("display_data_mobile"));
        
       
    }
    
    @Test
    public void testDOGetWithException() throws Exception{
    	
    	//this method is the test the scenario when it throws exception
    	
    	MockSlingHttpServletRequest request = new MockSlingHttpServletRequest(mockResourceResolver, context.bundleContext());
    	MockSlingHttpServletResponse response = new MockSlingHttpServletResponse();
    	Whitebox.invokeMethod(matchInfoServlet, "doGet",request,response);
       	
    }
}
