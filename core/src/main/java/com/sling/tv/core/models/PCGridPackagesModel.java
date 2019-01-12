package com.sling.tv.core.models;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.iterators.TransformIterator;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class PCGridPackagesModel {

    private static Logger log = LoggerFactory.getLogger(PCGridPackagesModel.class);


    @Inject
    private ResourceResolver resourceResolver;

    @Inject
    private SlingHttpServletRequest request;


    @PostConstruct
    public final void init() {

        try {

            //Creating the Map instance to insert the packages
            final Map<String, String> packages = new LinkedHashMap<String, String>();

            @SuppressWarnings("unchecked")

            //Creating the Datasource Object for populating the drop-down control.
                    DataSource dataSource = new SimpleDataSource(new TransformIterator(packages.keySet().iterator(), new Transformer() {

                @Override

                //Transforms the input object into output object
                public Object transform(Object packageObject) {
                    String packag = (String) packageObject;

                    //Allocating memory to Map
                    ValueMap packageValueMap = new ValueMapDecorator(new HashMap<String, Object>());

                    //Populate the Map
                    packageValueMap.put("value", packag);
                    packageValueMap.put("text", packages.get(packag));

                    return new ValueMapResource(resourceResolver, new ResourceMetadata(), "nt:unstructured", packageValueMap);
                }
            }));

            request.setAttribute(DataSource.class.getName(), dataSource);

        } catch (NullPointerException ex) {
            log.error("Null Pointer Exception occured in PC Grid Package Model :::: ", ex);
        } catch (Exception ex) {
            log.info("Exception occured in PCGridPackageModel", ex);
        }
    }

}
