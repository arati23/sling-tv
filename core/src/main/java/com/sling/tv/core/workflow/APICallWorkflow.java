package com.sling.tv.core.workflow;


import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.sling.tv.core.services.ClassificationResponse;
import com.sling.tv.core.services.PackageBuilder;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;



@Component
@Service
@Properties({
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "API Call Workflow"),
        @Property(name = "process.label", value = "API Call Process")})
public class APICallWorkflow implements WorkflowProcess {


    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Reference
    ClassificationResponse classificationResponse;

    @Reference
    PackageBuilder packageBuilder;

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void execute(WorkItem item, WorkflowSession wfSession, MetaDataMap arg2)
            throws WorkflowException {
        LOG.info("Entering to execute");
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put(ResourceResolverFactory.SUBSERVICE, "MyService");
            ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
            classificationResponse.createClassificationJson(resourceResolver);
            packageBuilder.packageBuilderMethod(resourceResolver);
            Session session = resourceResolver.adaptTo(Session.class);
        } catch (LoginException e) {
            LOG.info("Error in Execute method" +e);
            e.printStackTrace();
        }
    }

}
