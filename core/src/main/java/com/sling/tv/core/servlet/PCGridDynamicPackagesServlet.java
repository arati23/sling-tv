package com.sling.tv.core.servlet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.IOUtils;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SlingServlet(paths = "/bin/dynamicPackages", methods = "GET")
public class PCGridDynamicPackagesServlet extends SlingSafeMethodsServlet {


    private static final long serialVersionUID = 1L;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        log.info("Entering doGet!!!");
        try {
            String planOne = request.getParameter("planId");
            String classification = "";
            if (request.getParameter("classificationOne") != null) {
                classification = request.getParameter("classificationOne");
            } else if (request.getParameter("classificationTwo") != null) {
                classification = request.getParameter("classificationTwo");
            } else if (request.getParameter("classificationThree") != null) {
                classification = request.getParameter("classificationThree");
            }

            String items = request.getParameter("items");
            String packageOne = null;
            String packageTwo = null;
            String packageThree = null;
            String packageFour = null;
            String packageFive = null;
            String packageSix = null;
            String packageSeven = null;
            String packageEight = null;
            String packageNine = null;

            String planTwo = null;
            String planThree = null;

            boolean packagesFlag = false;
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode responseJsonNew = objectMapper.createObjectNode();

            if (items != null) {
                packagesFlag = true;
                JsonNode planJson = objectMapper.readTree(items);

                packageOne = planJson.has("packageOne") ? planJson.get("packageOne").asText() : null;
                packageTwo = planJson.has("packageTwo") ? planJson.get("packageTwo").asText() : null;
                packageThree = planJson.has("packageThree") ? planJson.get("packageThree").asText() : null;
                packageFour = planJson.has("packageFour") ? planJson.get("packageFour").asText() : null;
                packageFive = planJson.has("packageFive") ? planJson.get("packageFive").asText() : null;
                packageSix = planJson.has("packageSix") ? planJson.get("packageSix").asText() : null;
                packageSeven = planJson.has("packageSeven") ? planJson.get("packageSeven").asText() : null;
                packageEight = planJson.has("packageEight") ? planJson.get("packageEight").asText() : null;
                packageNine = planJson.has("packageNine") ? planJson.get("packageNine").asText() : null;


                String classificationOne = planJson.has("classificationOne") ? planJson.get("classificationOne").asText() : null;
                String classificationTwo = planJson.has("classificationTwo") ? planJson.get("classificationTwo").asText() : null;
                String classificationThree = planJson.has("classificationThree") ? planJson.get("classificationThree").asText() : null;
                planOne = planJson.has("planOne") ? planJson.get("planOne").asText() : null;
                planTwo = planJson.has("planTwo") ? planJson.get("planTwo").asText() : null;
                planThree = planJson.has("planThree") ? planJson.get("planThree").asText() : null;


                JsonNode responseJsonOne = objectMapper.createObjectNode();
                JsonNode responseJsonTwo = objectMapper.createObjectNode();
                JsonNode responseJsonThree = objectMapper.createObjectNode();
                if (classificationOne != null) {
                    responseJsonOne = getClassificationPlanPackages(classificationOne, request, planOne, packageOne, packageTwo, packageThree, packagesFlag, "tab1");
                    responseJsonNew.set("packageOne", responseJsonOne.get("packageOne"));
                    responseJsonNew.set("packageTwo", responseJsonOne.get("packageTwo"));
                    responseJsonNew.set("packageThree", responseJsonOne.get("packageThree"));

                }
                if (classificationTwo != null) {
                    responseJsonTwo = getClassificationPlanPackages(classificationTwo, request, planTwo, packageFour, packageFive, packageSix, packagesFlag, "tab2");
                    responseJsonNew.set("packageFour", responseJsonTwo.get("packageFour"));
                    responseJsonNew.set("packageFive", responseJsonTwo.get("packageFive"));
                    responseJsonNew.set("packageSix", responseJsonTwo.get("packageSix"));
                }
                if (classificationThree != null) {
                    responseJsonThree = getClassificationPlanPackages(classificationThree, request, planThree, packageSeven, packageEight, packageNine, packagesFlag, "tab3");
                    responseJsonNew.set("packageSeven", responseJsonThree.get("packageSeven"));
                    responseJsonNew.set("packageEight", responseJsonThree.get("packageEight"));
                    responseJsonNew.set("packageNine", responseJsonThree.get("packageNine"));
                }
            } else {
                responseJsonNew = getClassificationPlanPackages(classification, request, planOne, packageOne, packageTwo, packageThree, packagesFlag, null);
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(responseJsonNew);
        } catch (Exception ex) {
            log.info("Error in doGet of PCGridDynamicPackagesServlet ::: ", ex);
        }
    }

    public ObjectNode getClassificationPlanPackages(String classification, SlingHttpServletRequest request, String plan, String packageOne, String packageTwo, String packageThree, boolean packagesFlag, String tab) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode responseJson = objectMapper.createObjectNode();
        try {
            if (classification != null) {
                ResourceResolver resourceResolver = request.getResourceResolver();
                Session session = resourceResolver.adaptTo(Session.class);
                Node packageJcrContentNode = session.getNode("/content/sling-tv/api-data/" + classification + "/packages.json/jcr:content");
                InputStream inputStream = packageJcrContentNode.getProperty("jcr:data").getBinary().getStream();
                String packageContentNodeToString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                ArrayNode packagesArray = (ArrayNode) objectMapper.readTree(packageContentNodeToString);
                for (int i = 0; i < packagesArray.size(); i++) {
                    JsonNode classificationPlan = packagesArray.get(i);
                    if (classificationPlan.has("planId")) {
                        String planIdInJson = classificationPlan.get("planId").asText();
                        JsonNode allPackages = classificationPlan.path("packages");
                        @SuppressWarnings("unchecked")
                        Map<String, String> allPackagesMap = new ObjectMapper().convertValue(allPackages, HashMap.class);
                        JsonNode allPlansJson = objectMapper.createObjectNode();
                        if (planIdInJson.equals(plan) && !packagesFlag) {
                            responseJson = (ObjectNode) classificationPlan.get("packages");
                            break;
                        } else {
                            if (plan != null && planIdInJson.equals(plan) && packageOne != null) {
                                ObjectNode package1Json = objectMapper.createObjectNode();
                                if (null != allPackages.path(packageOne) && package1Json.size() == 0) {
                                    package1Json.put(packageOne, allPackagesMap.get(packageOne));
                                    for (Map.Entry<String, String> entry : allPackagesMap.entrySet()) {
                                        if (!allPlansJson.has(entry.getKey())) {
                                            package1Json.put(entry.getKey(), entry.getValue());
                                        }
                                    }
                                    if (tab.equals("tab1")) {
                                        addInResponseJson(tab, package1Json, responseJson, "packageOne");
                                    } else if (tab.equals("tab2")) {
                                        addInResponseJson(tab, package1Json, responseJson, "packageFour");
                                    } else if (tab.equals("tab3")) {
                                        addInResponseJson(tab, package1Json, responseJson, "packageSeven");
                                    }
                                }
                            }
                            if (plan != null && planIdInJson.equals(plan) && packageTwo != null) {
                                ObjectNode package2Json = objectMapper.createObjectNode();
                                if (allPackages.has(packageTwo) && package2Json.size() == 0) {
                                    package2Json.put(packageTwo, allPackagesMap.get(packageTwo));
                                    for (Map.Entry<String, String> entry : allPackagesMap.entrySet()) {
                                        if (!allPlansJson.has(entry.getKey())) {
                                            package2Json.put(entry.getKey(), entry.getValue());
                                        }
                                    }
                                    if (tab.equals("tab1")) {
                                        addInResponseJson(tab, package2Json, responseJson, "packageTwo");
                                    } else if (tab.equals("tab2")) {
                                        addInResponseJson(tab, package2Json, responseJson, "packageFive");
                                    } else if (tab.equals("tab3")) {
                                        addInResponseJson(tab, package2Json, responseJson, "packageEight");
                                    }
                                }
                            } else if (plan != null && planIdInJson.equals(plan) && packageTwo == null) {
                                ObjectNode package2Json = objectMapper.createObjectNode();
                                for (Map.Entry<String, String> entry : allPackagesMap.entrySet()) {
                                    if (!allPlansJson.has(entry.getKey())) {
                                        package2Json.put(entry.getKey(), entry.getValue());
                                    }
                                }
                                if (tab.equals("tab1")) {
                                    addInResponseJson(tab, package2Json, responseJson, "packageTwo");
                                } else if (tab.equals("tab2")) {
                                    addInResponseJson(tab, package2Json, responseJson, "packageFive");
                                } else if (tab.equals("tab3")) {
                                    addInResponseJson(tab, package2Json, responseJson, "packageEight");
                                }
                            }
                            if (plan != null && planIdInJson.equals(plan) && packageThree != null) {
                                ObjectNode package3Json = objectMapper.createObjectNode();
                                if (allPackages.has(packageThree) && package3Json.size() == 0) {
                                    package3Json.put(packageThree, allPackagesMap.get(packageThree));
                                    for (Map.Entry<String, String> entry : allPackagesMap.entrySet()) {
                                        if (!allPlansJson.has(entry.getKey())) {
                                            package3Json.put(entry.getKey(), entry.getValue());
                                        }
                                    }
                                    if (tab.equals("tab1")) {
                                        addInResponseJson(tab, package3Json, responseJson, "packageThree");
                                    } else if (tab.equals("tab2")) {
                                        addInResponseJson(tab, package3Json, responseJson, "packageSix");
                                    } else if (tab.equals("tab3")) {
                                        addInResponseJson(tab, package3Json, responseJson, "packageNine");
                                    }
                                }
                            } else if (plan != null && planIdInJson.equals(plan) && packageThree == null) {
                                ObjectNode package3Json = objectMapper.createObjectNode();
                                for (Map.Entry<String, String> entry : allPackagesMap.entrySet()) {
                                    if (!allPlansJson.has(entry.getKey())) {
                                        package3Json.put(entry.getKey(), entry.getValue());
                                    }
                                }
                                if (tab.equals("tab1")) {
                                    addInResponseJson(tab, package3Json, responseJson, "packageThree");
                                } else if (tab.equals("tab2")) {
                                    addInResponseJson(tab, package3Json, responseJson, "packageSix");
                                } else if (tab.equals("tab3")) {
                                    addInResponseJson(tab, package3Json, responseJson, "packageNine");
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            log.error("Error in getClassificationPlanPackages :::: ", ex);
        }
        return responseJson;
    }

    private JsonNode addInResponseJson(String tab, ObjectNode packagesJson, ObjectNode responseJson, String planPackage) {
        log.info("Entering addInResponseJson");
        try {
            if (tab.equals("tab1") && (planPackage.equals("packageOne") || planPackage.equals("packageTwo") || planPackage.equals("packageThree"))) {
                responseJson.putPOJO(planPackage, packagesJson);
            } else if (tab.equals("tab2") && (planPackage.equals("packageFour") || planPackage.equals("packageFive") || planPackage.equals("packageSix"))) {
                responseJson.putPOJO(planPackage, packagesJson);
            } else if (tab.equals("tab3") && (planPackage.equals("packageSeven") || planPackage.equals("packageEight") || planPackage.equals("packageNine"))) {
                responseJson.putPOJO(planPackage, packagesJson);
            }
        } catch (Exception ex) {
            log.error("Error in addInResponseJson ::: ", ex);
        }
        return responseJson;
    }
}