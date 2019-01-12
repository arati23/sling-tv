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


@SlingServlet(paths = "/bin/getDynamicChannels", methods = "GET")
public class PCGridChannelServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        log.info("Entering doGet!!!");
        try {
            String classification = request.getParameter("classification");
            String planId = request.getParameter("planId");
            String packageId = request.getParameter("packageId");
            String channelLogoPath = request.getParameter("channelLogoPath");
            ResourceResolver resourceResolver = request.getResourceResolver();
            Session session = resourceResolver.adaptTo(Session.class);
            if (classification != null && planId != null) {
                String classificationPlanNodeName = "/content/sling-tv/api-data/" + classification + "/" + planId + "-packages.json/jcr:content";
                if (session != null) {
                    Node packageJcrContentNode = session.getNode(classificationPlanNodeName);
                    InputStream inputStream = packageJcrContentNode.getProperty("jcr:data").getBinary().getStream();
                    String packageNodeToString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode packages = objectMapper.readTree(packageNodeToString);
                    ArrayNode packagesArray = (ArrayNode) packages.get("packages");
                    ArrayNode channelRespArray = objectMapper.createArrayNode();
                    for (int i = 0; i < packagesArray.size(); i++) {
                        JsonNode packageJson = packagesArray.get(i);
                        String packageIdInJson = packageJson.get("package").asText();
                        if (packageId.equals(packageIdInJson)) {
                            ArrayNode channelArray = (ArrayNode) packageJson.get("channels");
                            for (int j = 0; j < channelArray.size(); j++) {
                                JsonNode channelItem = channelArray.get(j);
                                ObjectNode channel = objectMapper.createObjectNode();
                                StringBuilder channelLogo = new StringBuilder(channelLogoPath + "/");
                                String channelName = channelItem.get("image_name").asText();
                                channel.put("name", channelName);
                                channel.put("altText", channelItem.get("name").asText());
                                channelLogo.append(channelName);
                                channelLogo.append(".svg");
                                channel.put("logo", channelLogo.toString());
                                channelRespArray.add(channel);
                            }
                            break;
                        }
                    }
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(channelRespArray.toString());
                }
            }
        } catch (Exception ex) {
            log.info("Error in doGet of PCGridChannelServlet ::: ", ex);
        }
    }
}
