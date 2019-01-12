package com.sling.tv.core.services.impl;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.sling.tv.core.beans.RedirectsResultBean;
import com.sling.tv.core.services.RedirectsToolService;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.*;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

@Component(metatype = false)
@Service(value = RedirectsToolService.class)
public class RedirectsToolServiceImpl implements RedirectsToolService {

    @Reference
    QueryBuilder queryBuilder;

    @Reference
    SlingRepository slingRepository;

    @Reference
    ResourceResolverFactory resolverFactory;

    private ResourceResolver resolver = null;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(RedirectsToolServiceImpl.class);


    @Activate
    public void activate() throws LoginException {
        getResourceResolver();
    }


    private RedirectsResultBean getObjectFromHit(Hit hit) {
        LOGGER.info("in Redirects tools Service impl");
        Resource res;
        RedirectsResultBean resultBean = null;
        try {
            resultBean = new RedirectsResultBean();
            res = resolver.getResource(hit.getPath());
            if (res != null) {
                ValueMap redirectsResultMap = res.adaptTo(ValueMap.class);
                if (redirectsResultMap != null) {
                    resultBean.setSource(redirectsResultMap.get("source", StringUtils.EMPTY));
                    resultBean.setDestination(redirectsResultMap.get("destination", StringUtils.EMPTY));
                    resultBean.setLegacy(redirectsResultMap.get("legacy",StringUtils.EMPTY));
                    resultBean.setQuery(redirectsResultMap.get("query",StringUtils.EMPTY));
                    resultBean.setTemporary(redirectsResultMap.get("temporary", StringUtils.EMPTY));
                    resultBean.setCreated_Date(redirectsResultMap.get("created_date", StringUtils.EMPTY));

                }
            }
        } catch (RepositoryException e) {
            LOGGER.error(" Error in retreivig the Result Bean object: " + e);
        }

        return resultBean;
    }


    private Map<String, String> createPredicateMap(String offset, String limit) {
        Map<String, String> predicateMap = new HashMap<>();

        predicateMap.put("type", "nt:unstructured");
        predicateMap.put("path", "/content/redirects");
        predicateMap.put("orderby", "@jcr:created");
        predicateMap.put("orderby.sort", "desc");
        predicateMap.put("p.offset", offset);
        predicateMap.put("p.limit", limit);

        return predicateMap;

    }

    private void getResourceResolver() {


        Map<String, Object> serviceParams = new HashMap<>();
        serviceParams.put(ResourceResolverFactory.SUBSERVICE,"RedirectsService");

        try {
            resolver = resolverFactory.getServiceResourceResolver(serviceParams);
        } catch (LoginException e) {
            LOGGER.error("Error in getting resolver " + e);
        }
    }


    @Override
    public List<RedirectsResultBean> getResults(String offset, String limit) {
        Map<String, String> predicateMap = createPredicateMap(offset, limit);
        return getSearchResults(predicateMap);
    }


    private List<RedirectsResultBean> getSearchResults(
            Map<String, String> predicateMap) {
        List<RedirectsResultBean> results = null;


        Session session = resolver.adaptTo(Session.class);

        Query queryObj = this.queryBuilder.createQuery(
                PredicateGroup.create(predicateMap), session);
        LOGGER.info("Search Query" + queryObj.getPredicates().toString());

        SearchResult searchResults = queryObj.getResult();

        if (searchResults != null) {
            LOGGER.info("Total number of search matches are: "
                    + searchResults.getTotalMatches());
            final List<Hit> hitsList = searchResults.getHits();
            if (hitsList != null && !hitsList.isEmpty()) {
                results = new ArrayList<>();
                for (Hit hit : searchResults.getHits()) {

                    RedirectsResultBean resultBean = getObjectFromHit(hit);
                    LOGGER.info("Inside Hit :" + resultBean.getSource());
                    results.add(resultBean);

                }
            }

        }
        return results;
    }

}