package com.sling.tv.core.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.IOUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Session;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

@Component
@Service(value = PackageBuilder.class)
public class PackageBuilder {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Reference
    OsgiConfigService osgiConfigService;


    ClassificationResponse classificationResponse = new ClassificationResponse();

    public final void packageBuilderMethod(ResourceResolver resourceResolver) {
        log.info("Inside the package builder method of Package builder class ");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Session session = resourceResolver.adaptTo(Session.class);
            Node classificationJsonFile = session.getNode("/content/sling-tv/api-data/classifications.json/jcr:content");
            Node plansJsonFile = session.getNode("/content/sling-tv/api-data/plans.json/jcr:content");
            InputStream classificationsInputStream = classificationJsonFile.getProperty("jcr:data").getBinary().getStream();
            InputStream plansInputStream = plansJsonFile.getProperty("jcr:data").getBinary().getStream();
            String classificationsDataString = IOUtils.toString(classificationsInputStream, StandardCharsets.UTF_8);
            String plansDataString = IOUtils.toString(plansInputStream, StandardCharsets.UTF_8);
            Node apiNode = session.getNode("/content/sling-tv/api-data");
            if (classificationJsonFile != null && plansJsonFile != null) {
                JsonNode classificationFileJcrContent = objectMapper.readTree(classificationsDataString);
                JsonNode plansFileJcrContent = objectMapper.readTree(plansDataString);
                Iterator<String> classificationKeys = classificationFileJcrContent.fieldNames();
                while (classificationKeys.hasNext()) {
                    String api = osgiConfigService.getSlingPCServiceURL();
                    String url = api + "/catalog/classifications/";
                    String classificationKey = classificationKeys.next();
                    if (!classificationKey.isEmpty()) {
                        if (!apiNode.hasNode(classificationKey)) {
                            apiNode.addNode(classificationKey, "nt:folder");
                        }
                        if (apiNode.hasNode(classificationKey)) {
                            Node classificationNode = session.getNode("/content/sling-tv/api-data/" + classificationKey);
                            Iterator<String> planKeys = plansFileJcrContent.fieldNames();
                            String finalURL = "";
                            ArrayNode classificationPlan = objectMapper.createArrayNode();
                            while (planKeys.hasNext()) {
                                String planKey = planKeys.next();
                                if (!planKey.isEmpty()) {
                                    finalURL = url + classificationKey + "/plans/" + planKey + "/details?format=json";
                                    String packageDetails = classificationResponse.readResponseFromAPI(finalURL);
                                    ApiSchedulerThread apiSchedulerThread = new ApiSchedulerThread(packageDetails, classificationNode, classificationPlan);
                                    Thread thread = new Thread(apiSchedulerThread);
                                    thread.start();
                                }
                            }
                        }
                    }
                }
            }
            session.save();
        } catch (Exception ex) {
            log.info("Error in init ::: ", ex);
        }
    }

    class ApiSchedulerThread implements Runnable {
        private String apiJsonResponse;
        private Node classificationNode;
        private ArrayNode classificationPlan;

        public ApiSchedulerThread(String apiJsonResponse, Node classificationNode, ArrayNode classificationPlan) {

            this.apiJsonResponse = apiJsonResponse;
            this.classificationNode = classificationNode;
            this.classificationPlan = classificationPlan;

        }

        @Override
        public void run() {
            try {

                JsonNode packagesJsonResponse = convertToPackages(apiJsonResponse);
                classificationPlan.add(packagesJsonResponse);

                JsonNode planPackagesResponse = extractChannelsFromPackages(apiJsonResponse);
                for (int i = 0; i < planPackagesResponse.size(); i++) {
                    String getPlanId = planPackagesResponse.get("planId").asText();
                    if (!getPlanId.isEmpty()) {
                        if (!classificationNode.hasNode(getPlanId + "-packages.json")) {
                            Node planPackageNode = classificationNode.addNode(getPlanId + "-packages.json", "nt:file");
                            Node planPackageJcrNode = planPackageNode.addNode("jcr:content", "nt:resource");
                            planPackageJcrNode.setProperty("jcr:data", planPackagesResponse.toString());
                        }
                        if (classificationNode.hasNode(getPlanId + "-packages.json")) {
                            Node planPackageExistingNode = classificationNode.getNode(getPlanId + "-packages.json/jcr:content");
                            planPackageExistingNode.setProperty("jcr:data", planPackagesResponse.toString());
                        }
                    }
                }
                if (classificationNode.hasNode("packages.json")) {
                    Node classificationPackageNode = classificationNode.getNode("packages.json/jcr:content");
                    classificationPackageNode.setProperty("jcr:data", classificationPlan.toString());
                }
                if (!classificationNode.hasNode("packages.json")) {
                    Node classificationPackageNode = classificationNode.addNode("packages.json", "nt:file");
                    Node classificationPackageJcrNode = classificationPackageNode.addNode("jcr:content", "nt:resource");
                    classificationPackageJcrNode.setProperty("jcr:data", classificationPlan.toString());
                }
            } catch (Exception ex) {
                log.info("Error in Run method");
            }

        }
    }

    private JsonNode convertToPackages(String packageDetails) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode desiredJsonResponse = objectMapper.createObjectNode();

        try {
            JsonNode apiJsonResponse = objectMapper.readTree(packageDetails);
            desiredJsonResponse.put("planId", apiJsonResponse.get("identifier").asText());
            ArrayNode apiPackages = (ArrayNode) apiJsonResponse.get("base_linear_packages");
            ObjectNode items = objectMapper.createObjectNode();
            items.put("", "Select");
            for (int i = 0; i < apiPackages.size(); i++) {
                JsonNode apiPackageItem = apiPackages.get(i);
                items.put(apiPackageItem.get("identifier").asText(), apiPackageItem.get("name").asText());
            }
            desiredJsonResponse.putPOJO("packages", items);
        } catch (Exception ex) {
            log.info("Error in Convert to Packages Method", ex);
        }
        return desiredJsonResponse;
    }


    private JsonNode extractChannelsFromPackages(String extractChannels) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode planPackagesObject = objectMapper.createObjectNode();
        try {
            ArrayNode packagesArray = objectMapper.createArrayNode();
            JsonNode apiJsonResponse = objectMapper.readTree(extractChannels);
            planPackagesObject.put("planId", apiJsonResponse.get("identifier").asText());
            ArrayNode baseLinearPackages = (ArrayNode) apiJsonResponse.get("base_linear_packages");

            for (int i = 0; i < baseLinearPackages.size(); i++) {
                ObjectNode innerObjects = objectMapper.createObjectNode();
                JsonNode channelObjects = baseLinearPackages.get(i);
                ArrayNode channelArray = (ArrayNode) channelObjects.get("channels");
                innerObjects.put("package", channelObjects.get("identifier").asText());
                ArrayNode channels = objectMapper.createArrayNode();
                for (int j = 0; j < channelArray.size(); j++) {
                    JsonNode apiChannelsObject = channelArray.get(j);
                    ObjectNode channelItem = objectMapper.createObjectNode();
                    if(apiChannelsObject.has("name")){
                        channelItem.put("name", apiChannelsObject.get("name").asText());
                    }else{
                        log.error("error : didn't fine the key@name" +channelArray.toString());
                    }
                    channelItem.put("image_name", apiChannelsObject.get("call_sign").asText());
                    channels.add(channelItem);
                }
                innerObjects.putPOJO("channels", channels);
                packagesArray.add(innerObjects);
            }
            planPackagesObject.putPOJO("packages", packagesArray);
        } catch (Exception ex) {
            log.info("Error in Extract Channels From Package Method", ex);
        }
        return planPackagesObject;
    }


}


