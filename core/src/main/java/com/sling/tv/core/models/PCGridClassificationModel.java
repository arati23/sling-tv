package com.sling.tv.core.models;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.iterators.TransformIterator;
import org.apache.commons.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class PCGridClassificationModel {

    private static Logger log = LoggerFactory.getLogger(PCGridClassificationModel.class);

    @Inject
    private SlingHttpServletRequest request;

    @Inject
    private ResourceResolver resourceResolver;

    @PostConstruct
    public final void init() {
        try {

            //Creating the Map instance to insert the Classifications
            final Map<String, String> classifications = new LinkedHashMap<String, String>();

            Session session = resourceResolver.adaptTo(Session.class);
            if (session != null) {
                String apiDataNodeName = "/content/sling-tv/api-data";
                if (session.nodeExists(apiDataNodeName) && session.getNode(apiDataNodeName) != null) {
                    Node apiData = session.getNode(apiDataNodeName);
                    if (apiData.hasNode("classifications.json/jcr:content")) {
                        Node classificationJcrContent = apiData.getNode("classifications.json/jcr:content");
                        InputStream classificationInputStream = classificationJcrContent.getProperty("jcr:data").getBinary().getStream();
                        String classificationInputStreamToString = IOUtils.toString(classificationInputStream);
                        classificationInputStream.close();
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode classification = objectMapper.readTree(classificationInputStreamToString);
                        Iterator<String> keys = classification.fieldNames();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            classifications.put(key, classification.get(key).asText());
                        }

                        @SuppressWarnings("unchecked")

                        //Creating the Datasource Object for populating the drop-down control.
                                DataSource dataSource = new SimpleDataSource(new TransformIterator(classifications.keySet().iterator(), new Transformer() {

                            @Override

                            //Transforms the input object into output object
                            public Object transform(Object classificationObject) {
                                String classification = (String) classificationObject;

                                //Allocating memory to Map
                                ValueMap classificationValueMap = new ValueMapDecorator(new HashMap<String, Object>());

                                //Populate the Map
                                classificationValueMap.put("value", classification);
                                classificationValueMap.put("text", classifications.get(classification));

                                return new ValueMapResource(resourceResolver, new ResourceMetadata(), "nt:unstructured", classificationValueMap);
                            }
                        }));

                        request.setAttribute(DataSource.class.getName(), dataSource);

                    }
                }

            }


        } catch (NullPointerException|RepositoryException ex) {
            log.error("Null Pointer or Repository  Exception occured in PC Classification Model :::: ", ex);
        } catch (Exception ex) {
            log.error("Exception occured in PCGridClassificationModel :::: ", ex);
        }
    }
}
