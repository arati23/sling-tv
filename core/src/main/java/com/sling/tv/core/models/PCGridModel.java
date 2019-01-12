package com.sling.tv.core.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sling.tv.core.beans.PCGridBean;
import org.apache.commons.io.IOUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.Session;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class PCGridModel {

    /**
     * Default log.
     */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());


    @Inject
    private String pcGridID;

    @Inject
    private String classificationOne;

    @Inject
    private String classificationTwo;

    @Inject
    private String classificationThree;

    @Inject
    private String planOne;

    @Inject
    private String planTwo;

    @Inject
    private String planThree;

    @Inject
    private String planOnePackageOne;

    @Inject
    private String planOnePackageTwo;

    @Inject
    private String planOnePackageThree;

    @Inject
    private String planTwoPackageOne;

    @Inject
    private String planTwoPackageTwo;

    @Inject
    private String planTwoPackageThree;

    @Inject
    private String planThreePackageOne;

    @Inject
    private String planThreePackageTwo;

    @Inject
    private String planThreePackageThree;

    @Inject
    private String planOnePackageOneTitle;

    @Inject
    private String planOnePackageTwoTitle;

    @Inject
    private String planOnePackageThreeTitle;

    @Inject
    private String planTwoPackageOneTitle;

    @Inject
    private String planTwoPackageTwoTitle;

    @Inject
    private String planTwoPackageThreeTitle;

    @Inject
    private String planThreePackageOneTitle;

    @Inject
    private String planThreePackageTwoTitle;

    @Inject
    private String planThreePackageThreeTitle;

    @Inject
    private String planOnePackageOneSubTitle;

    @Inject
    private String planOnePackageTwoSubTitle;

    @Inject
    private String planOnePackageThreeSubTitle;

    @Inject
    private String planTwoPackageOneSubTitle;

    @Inject
    private String planTwoPackageTwoSubTitle;

    @Inject
    private String planTwoPackageThreeSubTitle;

    @Inject
    private String planThreePackageOneSubTitle;

    @Inject
    private String planThreePackageTwoSubTitle;

    @Inject
    private String planThreePackageThreeSubTitle;

    @Inject
    private String planOneTitle;

    @Inject
    private String planTwoTitle;

    @Inject
    private String planThreeTitle;

    @Inject

    private String planOneSubTitle;

    @Inject
    private String planTwoSubTitle;

    @Inject
    private String planThreeSubTitle;

    @Inject
    private String stickyTabs;

    @Inject
    private String stickyTabsMobile;

    @Inject
    private String defaultTab;

    @Inject
    public String channelLogoPath;


    public String getPcGridID() {
        return pcGridID;
    }

    public String getStickyTabs() {
        return stickyTabs;
    }

    public String getStickyTabsMobile() {
        return stickyTabsMobile;
    }

    public String getDefaultTab() {
        return defaultTab;
    }

    public String getClassificationOne() {
        return classificationOne;
    }

    public String getPlanOne() {
        return planOne;
    }

    public String getPlanOnePackageOne() {
        return planOnePackageOne;
    }

    public String getChannelLogoPath() {
        return channelLogoPath;
    }

    public String getPlanOneTitle() {
        return planOneTitle;
    }

    public String getPlanTwoTitle() {
        return planTwoTitle;
    }

    public String getPlanThreeTitle() {
        return planThreeTitle;
    }

    public String getPlanOneSubTitle() {
        return planOneSubTitle;
    }

    public String getPlanTwoSubTitle() {
        return planTwoSubTitle;
    }

    public String getPlanThreeSubTitle() {
        return planThreeSubTitle;
    }

    public String getPlanOnePackageOneTitle() {
        return planOnePackageOneTitle;
    }

    public String getPlanOnePackageTwoTitle() {
        return planOnePackageTwoTitle;
    }

    public String getPlanOnePackageThreeTitle() {
        return planOnePackageThreeTitle;
    }

    public String getPlanTwoPackageOneTitle() {
        return planTwoPackageOneTitle;
    }

    public String getPlanTwoPackageTwoTitle() {
        return planTwoPackageTwoTitle;
    }

    public String getPlanTwoPackageThreeTitle() {
        return planTwoPackageThreeTitle;
    }

    public String getPlanThreePackageOneTitle() {
        return planThreePackageOneTitle;
    }

    public String getPlanThreePackageTwoTitle() {
        return planThreePackageTwoTitle;
    }

    public String getPlanThreePackageThreeTitle() {
        return planThreePackageThreeTitle;
    }

    public String getPlanOnePackageOneSubTitle() {
        return planOnePackageOneSubTitle;
    }

    public String getPlanOnePackageTwoSubTitle() {
        return planOnePackageTwoSubTitle;
    }

    public String getPlanOnePackageThreeSubTitle() {
        return planOnePackageThreeSubTitle;
    }

    public String getPlanTwoPackageOneSubTitle() {
        return planTwoPackageOneSubTitle;
    }

    public String getPlanTwoPackageTwoSubTitle() {
        return planTwoPackageTwoSubTitle;
    }

    public String getPlanTwoPackageThreeSubTitle() {
        return planTwoPackageThreeSubTitle;
    }

    public String getPlanThreePackageOneSubTitle() {
        return planThreePackageOneSubTitle;
    }

    public String getPlanThreePackageTwoSubTitle() {
        return planThreePackageTwoSubTitle;
    }

    public String getPlanThreePackageThreeSubTitle() {
        return planThreePackageThreeSubTitle;
    }


    public String getClassificationTwo() {
        return classificationTwo;
    }

    public String getClassificationThree() {
        return classificationThree;
    }

    public String getPlanTwo() {
        return planTwo;
    }

    public String getPlanThree() {
        return planThree;
    }

    public String getPlanOnePackageTwo() {
        return planOnePackageTwo;
    }

    public String getPlanOnePackageThree() {
        return planOnePackageThree;
    }

    public String getPlanTwoPackageOne() {
        return planTwoPackageOne;
    }

    public String getPlanTwoPackageTwo() {
        return planTwoPackageTwo;
    }

    public String getPlanTwoPackageThree() {
        return planTwoPackageThree;
    }

    public String getPlanThreePackageOne() {
        return planThreePackageOne;
    }

    public String getPlanThreePackageTwo() {
        return planThreePackageTwo;
    }

    public String getPlanThreePackageThree() {
        return planThreePackageThree;
    }


    @Inject
    private ResourceResolver resourceResolver;

    private List<PCGridBean> pcGridBeans;


    @PostConstruct
    public final void init() {
        log.info("##### INVOKED INIT");
        try {
            if (classificationOne != null && planOne != null) {
                pcGridBeans = new ArrayList<PCGridBean>();
                Session session = resourceResolver.adaptTo(Session.class);
                if (session != null) {
                    String classificationPlanNodeName = "/content/sling-tv/api-data/" + classificationOne + "/" + planOne + "-packages.json/jcr:content";
                    if (session.nodeExists(classificationPlanNodeName) && session.getNode(classificationPlanNodeName) != null) {
                        Node packageNode = session.getNode(classificationPlanNodeName);
                        if (packageNode != null) {
                            InputStream inputStream = packageNode.getProperty("jcr:data").getBinary().getStream();
                            String packageNodeToString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                            inputStream.close();
                            ObjectMapper objectMapper = new ObjectMapper();
                            JsonNode packageJson = objectMapper.readTree(packageNodeToString);
                            ArrayNode packageArray = (ArrayNode) packageJson.get("packages");
                            for (int i = 0; i < packageArray.size(); i++) {
                                JsonNode packageJsonObj = packageArray.get(i);
                                if (planOnePackageOne.equals(packageJsonObj.get("package").asText())) {
                                    ArrayNode channels = (ArrayNode) packageJsonObj.get("channels");
                                    for (int j = 0; j < channels.size(); j++) {
                                        StringBuilder channelLogo = new StringBuilder(channelLogoPath + "/");
                                        JsonNode channel = channels.get(j);
                                        PCGridBean bean = new PCGridBean();
                                        String channelName = channel.get("image_name").asText();
                                        bean.setName(channelName);
                                        bean.setAltText(channel.get("name").asText());
                                        channelLogo.append(channelName);
                                        channelLogo.append(".svg");
                                        bean.setLogoPath(channelLogo.toString());
                                        pcGridBeans.add(bean);
                                    }
                                }
                            }
                        }
                    }

                }

            }

        } catch (NullPointerException ex) {
            log.info("Null pointer Exception found in PC Grid Model ", ex);
        } catch (Exception ex) {
            log.info("Exception in init ::: ", ex);
        }
    }

    public List<PCGridBean> getPcGridBeans() {
        return pcGridBeans;
    }
}