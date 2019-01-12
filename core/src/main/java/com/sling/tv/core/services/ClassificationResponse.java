package com.sling.tv.core.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Session;


@Component
@Service(value = ClassificationResponse.class)
public class ClassificationResponse {

    private static Logger log = LoggerFactory.getLogger(ClassificationResponse.class);


    @Reference
    private OsgiConfigService osgiConfigService;


   // private Session session;

    public void createClassificationJson(ResourceResolver resourceResolver) {
        try {
            log.info("Entering createClassificationJson:::");
            String api = osgiConfigService.getSlingPCServiceURL();

            String originalClassification = readResponseFromAPI(api + "/catalog/classifications.json");
            Session session = resourceResolver.adaptTo(Session.class);
            Node apiData = session.getNode("/content/sling-tv/api-data");


            //Classifications
            if (apiData.hasNode("classification-original.json")) {
                Node originalClassificationResponse = apiData.getNode("classification-original.json/jcr:content");
                originalClassificationResponse.setProperty("jcr:data", originalClassification);
            }
            if (!apiData.hasNode("classification-original.json")) {
                Node originalClassificationResponse = apiData.addNode("classification-original.json", "nt:file");
                Node jcrContentOriginalClassificationNode = originalClassificationResponse.addNode("jcr:content", "nt:resource");
                jcrContentOriginalClassificationNode.setProperty("jcr:data", originalClassification);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode classificationsArray = (ArrayNode) objectMapper.readTree(originalClassification);
            ObjectNode classifications = objectMapper.createObjectNode();
            classifications.put("", "Please Select a Classification");
            for (int i = 0; i < classificationsArray.size(); i++) {
                JsonNode classification = classificationsArray.get(i);
                classifications.put(classification.get("identifier").asText(), classification.get("name").asText());
            }
            if (apiData.hasNode("classifications.json")) {
                Node classificationsResponse = apiData.getNode("classifications.json/jcr:content");
                classificationsResponse.setProperty("jcr:data", classifications.toString());
            }
            if (!apiData.hasNode("classifications.json")) {
                Node classificationsResponse = apiData.addNode("classifications.json", "nt:file");
                Node jcrContentClassificationNode = classificationsResponse.addNode("jcr:content", "nt:resource");
                jcrContentClassificationNode.setProperty("jcr:data", classifications.toString());
            }

            // Plans
            String originalPlans = readResponseFromAPI(api + "/catalog/plans.json");
            if (apiData.hasNode("plans-original.json")) {
                Node originalPlansResponse = apiData.getNode("plans-original.json/jcr:content");
                originalPlansResponse.setProperty("jcr:data", originalPlans);
            }
            if (!apiData.hasNode("plans-original.json")) {
                Node originalPlansResponse = apiData.addNode("plans-original.json", "nt:file");
                Node jcrContentOriginalPlansNode = originalPlansResponse.addNode("jcr:content", "nt:resource");
                jcrContentOriginalPlansNode.setProperty("jcr:data", originalPlans);
            }

            ArrayNode plansArray = (ArrayNode) objectMapper.readTree(originalPlans);
            ObjectNode plans = objectMapper.createObjectNode();

            plans.put("", "Please Select a Plan");
            for (int j = 0; j < plansArray.size(); j++) {
                JsonNode plan = plansArray.get(j);
                if (!plan.get("plan_type").asText().equals("grandfathered")) {
                    plans.put(plan.get("identifier").asText(), plan.get("name").asText());
                }
            }
            if (apiData.hasNode("plans.json")) {
                Node plansResponse = apiData.getNode("plans.json/jcr:content");
                plansResponse.setProperty("jcr:data", plans.toString());
            }
            if (!apiData.hasNode("plans.json")) {
                Node plansResponse = apiData.addNode("plans.json", "nt:file");
                Node jcrContentPlansNode = plansResponse.addNode("jcr:content", "nt:resource");
                jcrContentPlansNode.setProperty("jcr:data", plans.toString());
            }
            session.save();
        } catch (Exception ex) {
            log.info("Error in createClassificationJson :::: ", ex);
        }
    }


    // Method to read the APIs
    public String readResponseFromAPI(String serviceApi) {
        log.info("entering readResponse method::::");
        String responseApi = null;
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serviceApi);
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            responseApi = EntityUtils.toString(entity);
            response.getStatusLine().getStatusCode();
        } catch (Exception ex) {
            log.info("Error in Read Response Method :::", ex);
            ex.printStackTrace();
        }
        return responseApi;
    }

}